package com.pragma.loanrequest.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.loanrequest.application.dto.LoanRequestDTO;
import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.domain.port.in.LoanRequestUseCase;
import com.pragma.loanrequest.infrastructure.exception.GlobalExceptionHandler;
import com.pragma.loanrequest.infrastructure.exception.LoanRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanRequestController.class)
@Import(GlobalExceptionHandler.class)
@DisplayName("Tests de integración para LoanRequestController")
class LoanRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoanRequestUseCase loanRequestUseCase;

    private LoanRequestDTO loanRequestDTO;
    private LoanRequest loanRequest;
    private UUID loanRequestId;
    private String idempotencyKey;

    @BeforeEach
    void setUp() {
        loanRequestId = UUID.randomUUID();
        idempotencyKey = UUID.randomUUID().toString();

        loanRequestDTO = new LoanRequestDTO();
        loanRequestDTO.setCustomerId("CUST-001");
        loanRequestDTO.setAmount(new BigDecimal("10000.00"));
        loanRequestDTO.setTermMonths(12);
        loanRequestDTO.setInterestRate(new BigDecimal("0.15"));
        loanRequestDTO.setProductType("PERSONAL_LOAN");
        loanRequestDTO.setChannel("DIGITAL");
        loanRequestDTO.setIdempotencyKey(idempotencyKey);

        loanRequest = new LoanRequest(
                loanRequestId,
                loanRequestDTO.getCustomerId(),
                loanRequestDTO.getAmount(),
                loanRequestDTO.getTermMonths(),
                loanRequestDTO.getInterestRate(),
                LocalDate.now(),
                "PENDING",
                idempotencyKey,
                loanRequestDTO.getProductType(),
                loanRequestDTO.getChannel()
        );
    }

    @Test
    @DisplayName("POST /api/v1/loan-requests - Crea solicitud exitosamente")
    void createLoanRequest_WhenValidRequest_ReturnsCreated() throws Exception {
        when(loanRequestUseCase.createLoanRequest(any(LoanRequest.class))).thenReturn(loanRequest);

        mockMvc.perform(post("/api/v1/loan-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(loanRequestId.toString()))
                .andExpect(jsonPath("$.customerId").value("CUST-001"))
                .andExpect(jsonPath("$.status").value("PENDING"));

        verify(loanRequestUseCase, times(1)).createLoanRequest(any(LoanRequest.class));
    }

    @Test
    @DisplayName("POST /api/v1/loan-requests - Retorna 400 cuando el monto es inválido")
    void createLoanRequest_WhenInvalidAmount_ReturnsBadRequest() throws Exception {
        loanRequestDTO.setAmount(BigDecimal.ZERO);

        mockMvc.perform(post("/api/v1/loan-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequestDTO)))
                .andExpect(status().isBadRequest());

        verify(loanRequestUseCase, never()).createLoanRequest(any(LoanRequest.class));
    }

    @Test
    @DisplayName("POST /api/v1/loan-requests - Retorna 400 cuando el plazo es inválido")
    void createLoanRequest_WhenInvalidTerm_ReturnsBadRequest() throws Exception {
        loanRequestDTO.setTermMonths(0);

        mockMvc.perform(post("/api/v1/loan-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequestDTO)))
                .andExpect(status().isBadRequest());

        verify(loanRequestUseCase, never()).createLoanRequest(any(LoanRequest.class));
    }

    @Test
    @DisplayName("POST /api/v1/loan-requests - Retorna 400 cuando el customerId está vacío")
    void createLoanRequest_WhenEmptyCustomerId_ReturnsBadRequest() throws Exception {
        loanRequestDTO.setCustomerId("");

        mockMvc.perform(post("/api/v1/loan-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequestDTO)))
                .andExpect(status().isBadRequest());

        verify(loanRequestUseCase, never()).createLoanRequest(any(LoanRequest.class));
    }

    @Test
    @DisplayName("GET /api/v1/loan-requests/{id} - Retorna solicitud cuando existe")
    void getLoanRequestById_WhenExists_ReturnsOk() throws Exception {
        when(loanRequestUseCase.getLoanRequestById(loanRequestId)).thenReturn(loanRequest);

        mockMvc.perform(get("/api/v1/loan-requests/{id}", loanRequestId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(loanRequestId.toString()))
                .andExpect(jsonPath("$.customerId").value("CUST-001"))
                .andExpect(jsonPath("$.amount").value(10000.00));

        verify(loanRequestUseCase, times(1)).getLoanRequestById(loanRequestId);
    }

    @Test
    @DisplayName("GET /api/v1/loan-requests/{id} - Retorna 404 cuando no existe")
    void getLoanRequestById_WhenNotExists_ReturnsNotFound() throws Exception {
        when(loanRequestUseCase.getLoanRequestById(loanRequestId))
                .thenThrow(new LoanRequestException("Solicitud no encontrada", "NOT_FOUND"));

        mockMvc.perform(get("/api/v1/loan-requests/{id}", loanRequestId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(loanRequestUseCase, times(1)).getLoanRequestById(loanRequestId);
    }

    @Test
    @DisplayName("GET /api/v1/loan-requests/{id} - Retorna 400 cuando el ID es inválido")
    void getLoanRequestById_WhenInvalidId_ReturnsBadRequest() throws Exception {
        String invalidId = "invalid-uuid";

        mockMvc.perform(get("/api/v1/loan-requests/{id}", invalidId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(loanRequestUseCase, never()).getLoanRequestById(any(UUID.class));
    }

    @Test
    @DisplayName("POST /api/v1/loan-requests - Retorna 500 cuando hay error interno")
    void createLoanRequest_WhenInternalError_ReturnsInternalServerError() throws Exception {
        when(loanRequestUseCase.createLoanRequest(any(LoanRequest.class)))
                .thenThrow(new RuntimeException("Error interno del servidor"));

        mockMvc.perform(post("/api/v1/loan-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequestDTO)))
                .andExpect(status().isInternalServerError());

        verify(loanRequestUseCase, times(1)).createLoanRequest(any(LoanRequest.class));
    }

    @Test
    @DisplayName("POST /api/v1/loan-requests - Valida idempotencyKey en la solicitud")
    void createLoanRequest_WithIdempotencyKey_ProcessesIdempotently() throws Exception {
        when(loanRequestUseCase.createLoanRequest(any(LoanRequest.class))).thenReturn(loanRequest);

        mockMvc.perform(post("/api/v1/loan-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequestDTO)))
                .andExpect(status().isCreated());

        verify(loanRequestUseCase).createLoanRequest(argThat(
                request -> idempotencyKey.equals(request.getIdempotencyKey())));
    }
}