package com.pragma.loanrequest.infrastructure.controller;

import com.pragma.loanrequest.application.dto.LoanRequestDTO;
import com.pragma.loanrequest.application.service.LoanRequestService;
import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.infrastructure.exception.LoanRequestException;
import com.pragma.loanrequest.infrastructure.persistence.mapper.LoanRequestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loan-requests")
@Tag(name = "Gestión de Solicitudes de Préstamos", description = "API para crear, consultar y validar solicitudes de préstamos")
public class LoanRequestController {

    private static final Logger log = LoggerFactory.getLogger(LoanRequestController.class);
    
    private final LoanRequestService loanRequestService;
    private final LoanRequestMapper loanRequestMapper;

    public LoanRequestController(LoanRequestService loanRequestService, LoanRequestMapper loanRequestMapper) {
        this.loanRequestService = loanRequestService;
        this.loanRequestMapper = loanRequestMapper;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva solicitud de préstamo", 
               description = "Crea una nueva solicitud de préstamo con validación de idempotencia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Solicitud creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
        @ApiResponse(responseCode = "409", description = "Solicitud duplicada (mismo idempotencyKey)"),
        @ApiResponse(responseCode = "422", description = "Validación de negocio fallida"),
        @ApiResponse(responseCode = "503", description = "Sistema externo no disponible")
    })
    public ResponseEntity<LoanRequestDTO> createLoanRequest(
            @Valid @RequestBody LoanRequestDTO requestDTO,
            @RequestHeader(value = "X-Idempotency-Key", required = false) String idempotencyKey) {
        
        log.info("Recibida solicitud de préstamo para customerId: {}, amount: {}", 
                requestDTO.getCustomerId(), requestDTO.getAmount());
        
        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            log.debug("Key de idempotencia proporcionada: {}", idempotencyKey);
        }
        
        try {
            LoanRequest loanRequest = loanRequestMapper.toDomain(requestDTO);
            
            if (idempotencyKey != null && !idempotencyKey.isBlank()) {
                loanRequest = new LoanRequest(
                    UUID.randomUUID(),
                    loanRequest.getCustomerId(),
                    loanRequest.getAmount(),
                    loanRequest.getTermMonths(),
                    loanRequest.getInterestRate(),
                    loanRequest.getRequestDate(),
                    loanRequest.getStatus(),
                    idempotencyKey,
                    loanRequest.getProductType(),
                    loanRequest.getChannel()
                );
            }
            
            LoanRequest createdRequest = loanRequestService.createLoanRequest(loanRequest);
            LoanRequestDTO responseDTO = loanRequestMapper.toDTO(createdRequest);
            
            log.info("Solicitud de préstamo creada exitosamente con ID: {}", createdRequest.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
            
        } catch (LoanRequestException e) {
            log.error("Error al crear solicitud de préstamo: code={}, message={}", e.getCode(), e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al crear solicitud de préstamo: {}", e.getMessage(), e);
            throw new LoanRequestException("Error interno al procesar la solicitud", "INTERNAL_ERROR", 500);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una solicitud de préstamo por ID", 
               description = "Recupera los detalles de una solicitud de préstamo específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
        @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<LoanRequestDTO> getLoanRequestById(
            @Parameter(description = "ID único de la solicitud de préstamo", required = true)
            @PathVariable UUID id) {
        
        log.info("Consultando solicitud de préstamo con ID: {}", id);
        
        try {
            LoanRequest loanRequest = loanRequestService.getLoanRequestById(id);
            
            if (loanRequest == null) {
                log.warn("Solicitud de préstamo no encontrada con ID: {}", id);
                throw new LoanRequestException("Solicitud de préstamo no encontrada", "NOT_FOUND", 404);
            }
            
            LoanRequestDTO responseDTO = loanRequestMapper.toDTO(loanRequest);
            log.info("Solicitud de préstamo recuperada exitosamente: {}", id);
            return ResponseEntity.ok(responseDTO);
            
        } catch (LoanRequestException e) {
            log.error("Error al obtener solicitud: code={}, message={}", e.getCode(), e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al obtener solicitud: {}", e.getMessage(), e);
            throw new LoanRequestException("Error interno al recuperar la solicitud", "INTERNAL_ERROR", 500);
        }
    }

    @PostMapping("/validate")
    @Operation(summary = "Validar una solicitud de préstamo", 
               description = "Valida una solicitud de préstamo contra sistemas externos (buró de riesgos y core bancario)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Validación completada"),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
        @ApiResponse(responseCode = "422", description = "Validación de negocio fallida"),
        @ApiResponse(responseCode = "503", description = "Sistema externo no disponible")
    })
    public ResponseEntity<ValidationResponse> validateLoanRequest(
            @Valid @RequestBody LoanRequestDTO requestDTO) {
        
        log.info("Validando solicitud de préstamo para customerId: {}", requestDTO.getCustomerId());
        
        try {
            LoanRequest loanRequest = loanRequestMapper.toDomain(requestDTO);
            loanRequestService.validateLoanRequest(loanRequest);
            
            ValidationResponse response = new ValidationResponse(
                loanRequest.getId() != null ? loanRequest.getId().toString() : null,
                "APPROVED",
                "La solicitud ha sido validada exitosamente"
            );
            
            log.info("Validación de solicitud completada exitosamente");
            return ResponseEntity.ok(response);
            
        } catch (LoanRequestException e) {
            log.error("Error en validación de solicitud: code={}, message={}", e.getCode(), e.getMessage());
            
            ValidationResponse errorResponse = new ValidationResponse(
                null,
                "REJECTED",
                e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
            
        } catch (Exception e) {
            log.error("Error inesperado en validación: {}", e.getMessage(), e);
            throw new LoanRequestException("Error interno al validar la solicitud", "INTERNAL_ERROR", 500);
        }
    }

    @GetMapping
    @Operation(summary = "Listar todas las solicitudes de préstamos", 
               description = "Retorna una lista de todas las solicitudes de préstamos registradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de solicitudes recuperada exitosamente")
    })
    public ResponseEntity<List<LoanRequestDTO>> getAllLoanRequests() {
        log.info("Listando todas las solicitudes de préstamos");
        throw new LoanRequestException("Funcionalidad no implementada", "NOT_IMPLEMENTED", 501);
    }

    public record ValidationResponse(String requestId, String status, String message) {}
}