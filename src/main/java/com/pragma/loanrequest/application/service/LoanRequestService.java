package com.pragma.loanrequest.application.service;

import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.domain.port.in.LoanRequestUseCase;
import com.pragma.loanrequest.domain.port.out.LoanRequestPersistencePort;
import com.pragma.loanrequest.domain.port.out.TracingPort;
import com.pragma.loanrequest.infrastructure.exception.LoanRequestException;
import com.pragma.loanrequest.infrastructure.persistence.mapper.LoanRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanRequestService implements LoanRequestUseCase {

    private static final Logger log = LoggerFactory.getLogger(LoanRequestService.class);

    private final LoanRequestPersistencePort persistencePort;
    private final LoanRequestMapper loanRequestMapper;
    private final TracingPort tracingPort;

    public LoanRequestService(
            LoanRequestPersistencePort persistencePort,
            LoanRequestMapper loanRequestMapper,
            TracingPort tracingPort
    ) {
        this.persistencePort = persistencePort;
        this.loanRequestMapper = loanRequestMapper;
        this.tracingPort = tracingPort;
    }

    @Override
    @Transactional
    public LoanRequest createLoanRequest(LoanRequest loanRequest) {
        String traceId = UUID.randomUUID().toString();
        tracingPort.recordOperation("CREATE_LOAN_REQUEST", traceId, "START", loanRequest.getCustomerId());

        try {
            validateLoanRequest(loanRequest);

            if (persistencePort.existsByIdempotencyKey(loanRequest.getIdempotencyKey())) {
                log.warn("Duplicate loan request detected for idempotency key: {}", loanRequest.getIdempotencyKey());
                Optional<LoanRequest> existingRequest = persistencePort.findByIdempotencyKey(
                        loanRequest.getIdempotencyKey()
                );
                tracingPort.recordOperation("CREATE_LOAN_REQUEST", traceId, "DUPLICATE", loanRequest.getCustomerId());
                return existingRequest.orElseThrow(() -> new LoanRequestException("Idempotency key conflict"));
            }

            LoanRequest savedRequest = persistencePort.save(loanRequest);
            log.info("Loan request created successfully: id={}, customerId={}, traceId={}",
                    savedRequest.getId(), savedRequest.getCustomerId(), traceId);

            tracingPort.recordOperation("CREATE_LOAN_REQUEST", traceId, "SUCCESS", loanRequest.getCustomerId());
            return savedRequest;

        } catch (LoanRequestException e) {
            tracingPort.recordOperation("CREATE_LOAN_REQUEST", traceId, "VALIDATION_ERROR", loanRequest.getCustomerId());
            throw e;
        } catch (Exception e) {
            tracingPort.recordOperation("CREATE_LOAN_REQUEST", traceId, "ERROR", loanRequest.getCustomerId());
            log.error("Error creating loan request: traceId={}, error={}", traceId, e.getMessage());
            throw new LoanRequestException("Failed to create loan request: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public LoanRequest getLoanRequestById(UUID id) {
        String traceId = UUID.randomUUID().toString();
        tracingPort.recordOperation("GET_LOAN_REQUEST", traceId, "START", id.toString());

        try {
            Optional<LoanRequest> requestOpt = persistencePort.findById(id);

            if (requestOpt.isEmpty()) {
                tracingPort.recordOperation("GET_LOAN_REQUEST", traceId, "NOT_FOUND", id.toString());
                throw new LoanRequestException("Loan request not found with id: " + id);
            }

            LoanRequest request = requestOpt.get();
            tracingPort.recordOperation("GET_LOAN_REQUEST", traceId, "SUCCESS", id.toString());
            log.info("Loan request retrieved: id={}, traceId={}", id, traceId);
            return request;

        } catch (LoanRequestException e) {
            throw e;
        } catch (Exception e) {
            tracingPort.recordOperation("GET_LOAN_REQUEST", traceId, "ERROR", id.toString());
            log.error("Error retrieving loan request: id={}, traceId={}, error={}", id, traceId, e.getMessage());
            throw new LoanRequestException("Failed to retrieve loan request: " + e.getMessage());
        }
    }

    @Override
    public void validateLoanRequest(LoanRequest loanRequest) {
        if (loanRequest.getCustomerId() == null || loanRequest.getCustomerId().isBlank()) {
            throw new LoanRequestException("Customer ID is required");
        }

        if (loanRequest.getAmount() == null || loanRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new LoanRequestException("Loan amount must be greater than zero");
        }

        if (loanRequest.getAmount().compareTo(new BigDecimal("100000")) > 0) {
            throw new LoanRequestException("Loan amount exceeds maximum allowed limit of 100,000");
        }

        if (loanRequest.getTermMonths() == null || loanRequest.getTermMonths() <= 0) {
            throw new LoanRequestException("Term months must be greater than zero");
        }

        if (loanRequest.getTermMonths() > 60) {
            throw new LoanRequestException("Term months cannot exceed 60 months");
        }

        if (loanRequest.getInterestRate() == null || loanRequest.getInterestRate().compareTo(BigDecimal.ZERO) <= 0) {
            throw new LoanRequestException("Interest rate must be greater than zero");
        }

        if (loanRequest.getInterestRate().compareTo(new BigDecimal("0.5")) > 0) {
            throw new LoanRequestException("Interest rate exceeds maximum allowed rate of 50%");
        }

        if (loanRequest.getProductType() == null || loanRequest.getProductType().isBlank()) {
            throw new LoanRequestException("Product type is required");
        }

        if (loanRequest.getIdempotencyKey() == null || loanRequest.getIdempotencyKey().isBlank()) {
            throw new LoanRequestException("Idempotency key is required for request processing");
        }

        if (loanRequest.getRequestDate() == null) {
            throw new LoanRequestException("Request date is required");
        }

        if (loanRequest.getRequestDate().isAfter(LocalDate.now())) {
            throw new LoanRequestException("Request date cannot be in the future");
        }

        log.debug("Loan request validation passed for customerId: {}", loanRequest.getCustomerId());
    }
}