package com.pragma.loanrequest.infrastructure.adapter;

import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.domain.port.out.LoanRequestPersistencePort;
import com.pragma.loanrequest.infrastructure.persistence.entity.LoanRequestEntity;
import com.pragma.loanrequest.infrastructure.persistence.mapper.LoanRequestMapper;
import com.pragma.loanrequest.infrastructure.persistence.repository.LoanRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class LoanRequestJpaAdapter implements LoanRequestPersistencePort {

    private static final Logger log = LoggerFactory.getLogger(LoanRequestJpaAdapter.class);

    private final LoanRequestRepository loanRequestRepository;
    private final LoanRequestMapper loanRequestMapper;

    public LoanRequestJpaAdapter(
            LoanRequestRepository loanRequestRepository,
            LoanRequestMapper loanRequestMapper
    ) {
        this.loanRequestRepository = loanRequestRepository;
        this.loanRequestMapper = loanRequestMapper;
    }

    @Override
    public LoanRequest save(LoanRequest loanRequest) {
        try {
            LoanRequestEntity entity = loanRequestMapper.toEntity(loanRequest);
            LoanRequestEntity savedEntity = loanRequestRepository.save(entity);
            log.debug("Loan request persisted: id={}, customerId={}",
                    savedEntity.getId(), savedEntity.getCustomerId());
            return loanRequestMapper.toDomain(savedEntity);
        } catch (Exception e) {
            log.error("Error persisting loan request: customerId={}, error={}",
                    loanRequest.getCustomerId(), e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<LoanRequest> findById(UUID id) {
        try {
            Optional<LoanRequestEntity> entityOpt = loanRequestRepository.findById(id);
            if (entityOpt.isEmpty()) {
                log.debug("Loan request not found: id={}", id);
                return Optional.empty();
            }
            log.debug("Loan request found: id={}", id);
            return Optional.of(loanRequestMapper.toDomain(entityOpt.get()));
        } catch (Exception e) {
            log.error("Error finding loan request by id: id={}, error={}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean existsByIdempotencyKey(String idempotencyKey) {
        try {
            boolean exists = loanRequestRepository.existsByIdempotencyKey(idempotencyKey);
            log.debug("Idempotency key check: key={}, exists={}", idempotencyKey, exists);
            return exists;
        } catch (Exception e) {
            log.error("Error checking idempotency key: key={}, error={}", idempotencyKey, e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<LoanRequest> findByIdempotencyKey(String idempotencyKey) {
        try {
            Optional<LoanRequestEntity> entityOpt = loanRequestRepository.findByIdempotencyKey(idempotencyKey);
            if (entityOpt.isEmpty()) {
                log.debug("Loan request not found by idempotency key: key={}", idempotencyKey);
                return Optional.empty();
            }
            log.debug("Loan request found by idempotency key: key={}", idempotencyKey);
            return Optional.of(loanRequestMapper.toDomain(entityOpt.get()));
        } catch (Exception e) {
            log.error("Error finding loan request by idempotency key: key={}, error={}",
                    idempotencyKey, e.getMessage());
            throw e;
        }
    }
}