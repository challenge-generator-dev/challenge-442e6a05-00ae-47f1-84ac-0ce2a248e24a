package com.pragma.loanrequest.application.service;

import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.domain.port.out.LoanRequestPersistencePort;
import com.pragma.loanrequest.domain.port.out.ExternalSystemPort;
import com.pragma.loanrequest.domain.port.out.TracingPort;
import com.pragma.loanrequest.infrastructure.exception.LoanRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitarios para LoanRequestService")
class LoanRequestServiceTest {

    @Mock
    private LoanRequestPersistencePort persistencePort;

    @Mock
    private ExternalSystemPort externalSystemPort;

    @Mock
    private TracingPort tracingPort;

    @InjectMocks
    private LoanRequestService loanRequestService;

    private LoanRequest loanRequest;
    private UUID loanRequestId;
    private String customerId;
    private String idempotencyKey;

    @BeforeEach
    void setUp() {
        loanRequestId = UUID.randomUUID();
        customerId = "CUST-001";
        idempotencyKey = UUID.randomUUID().toString();
        
        loanRequest = new LoanRequest(
                loanRequestId,
                customerId,
                new BigDecimal("10000.00"),
                12,
                new BigDecimal("0.15"),
                LocalDate.now(),
                "PENDING",
                idempotencyKey,
                "PERSONAL_LOAN",
                "DIGITAL"
        );
    }

    @Test
    @DisplayName("createLoanRequest - Crea solicitud exitosamente cuando no existe clave de idempotencia")
    void createLoanRequest_WhenIdempotencyKeyNotExists_CreatesSuccessfully() {
        when(persistencePort.existsByIdempotencyKey(idempotencyKey)).thenReturn(false);
        when(persistencePort.save(any(LoanRequest.class))).thenReturn(loanRequest);
        doNothing().when(tracingPort).recordOperation(anyString(), anyString(), any());
        doNothing().when(externalSystemPort).notifyExternalSystem(any(LoanRequest.class));

        LoanRequest result = loanRequestService.createLoanRequest(loanRequest);

        assertNotNull(result);
        assertEquals(customerId, result.getCustomerId());
        assertEquals(idempotencyKey, result.getIdempotencyKey());
        verify(persistencePort).save(any(LoanRequest.class));
        verify(tracingPort).recordOperation(anyString(), anyString(), any());
        verify(externalSystemPort).notifyExternalSystem(any(LoanRequest.class));
    }

    @Test
    @DisplayName("createLoanRequest - Retorna solicitud existente cuando la clave de idempotencia ya existe")
    void createLoanRequest_WhenIdempotencyKeyExists_ReturnsExisting() {
        when(persistencePort.existsByIdempotencyKey(idempotencyKey)).thenReturn(true);
        when(persistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.of(loanRequest));

        LoanRequest result = loanRequestService.createLoanRequest(loanRequest);

        assertNotNull(result);
        assertEquals(idempotencyKey, result.getIdempotencyKey());
        verify(persistencePort, never()).save(any(LoanRequest.class));
    }

    @Test
    @DisplayName("getLoanRequestById - Retorna solicitud cuando existe")
    void getLoanRequestById_WhenExists_ReturnsLoanRequest() {
        when(persistencePort.findById(loanRequestId)).thenReturn(Optional.of(loanRequest));

        LoanRequest result = loanRequestService.getLoanRequestById(loanRequestId);

        assertNotNull(result);
        assertEquals(loanRequestId, result.getId());
        verify(persistencePort).findById(loanRequestId);
    }

    @Test
    @DisplayName("getLoanRequestById - Lanza excepción cuando no existe")
    void getLoanRequestById_WhenNotExists_ThrowsException() {
        when(persistencePort.findById(loanRequestId)).thenReturn(Optional.empty());

        assertThrows(LoanRequestException.class, 
                () -> loanRequestService.getLoanRequestById(loanRequestId));
        verify(persistencePort).findById(loanRequestId);
    }

    @Test
    @DisplayName("validateLoanRequest - Valida correctamente una solicitud con datos válidos")
    void validateLoanRequest_WithValidData_ValidatesSuccessfully() {
        doNothing().when(persistencePort).save(any(LoanRequest.class));

        assertDoesNotThrow(() -> loanRequestService.validateLoanRequest(loanRequest));
    }

    @Test
    @DisplayName("validateLoanRequest - Lanza excepción cuando el monto es menor o igual a cero")
    void validateLoanRequest_WithInvalidAmount_ThrowsException() {
        LoanRequest invalidRequest = new LoanRequest(
                UUID.randomUUID(),
                customerId,
                BigDecimal.ZERO,
                12,
                new BigDecimal("0.15"),
                LocalDate.now(),
                "PENDING",
                UUID.randomUUID().toString(),
                "PERSONAL_LOAN",
                "DIGITAL"
        );

        assertThrows(LoanRequestException.class, 
                () -> loanRequestService.validateLoanRequest(invalidRequest));
    }

    @Test
    @DisplayName("validateLoanRequest - Lanza excepción cuando el plazo es menor a 1 mes")
    void validateLoanRequest_WithInvalidTerm_ThrowsException() {
        LoanRequest invalidRequest = new LoanRequest(
                UUID.randomUUID(),
                customerId,
                new BigDecimal("10000.00"),
                0,
                new BigDecimal("0.15"),
                LocalDate.now(),
                "PENDING",
                UUID.randomUUID().toString(),
                "PERSONAL_LOAN",
                "DIGITAL"
        );

        assertThrows(LoanRequestException.class, 
                () -> loanRequestService.validateLoanRequest(invalidRequest));
    }

    @Test
    @DisplayName("createLoanRequest - Registra trazabilidad al crear solicitud")
    void createLoanRequest_RecordsTracingOnCreation() {
        when(persistencePort.existsByIdempotencyKey(idempotencyKey)).thenReturn(false);
        when(persistencePort.save(any(LoanRequest.class))).thenReturn(loanRequest);
        doNothing().when(tracingPort).recordOperation(anyString(), anyString(), any());

        loanRequestService.createLoanRequest(loanRequest);

        verify(tracingPort, times(1)).recordOperation(
                eq("CREATE_LOAN_REQUEST"),
                anyString(),
                any());
    }
}