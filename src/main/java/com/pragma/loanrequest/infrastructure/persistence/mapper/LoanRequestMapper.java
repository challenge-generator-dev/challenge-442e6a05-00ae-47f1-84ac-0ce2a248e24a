package com.pragma.loanrequest.infrastructure.persistence.mapper;

import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.infrastructure.persistence.entity.LoanRequestEntity;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class LoanRequestMapper {

    public LoanRequest toDomain(LoanRequestEntity entity) {
        if (entity == null) {
            return null;
        }
        return new LoanRequest(
            UUID.fromString(entity.getId()),
            entity.getCustomerId(),
            entity.getAmount(),
            entity.getTermMonths(),
            entity.getInterestRate(),
            entity.getRequestDate(),
            entity.getStatus(),
            entity.getIdempotencyKey(),
            entity.getProductType(),
            entity.getChannel()
        );
    }

    public LoanRequestEntity toEntity(LoanRequest domain) {
        if (domain == null) {
            return null;
        }
        LoanRequestEntity entity = new LoanRequestEntity();
        entity.setId(domain.getId().toString());
        entity.setCustomerId(domain.getCustomerId());
        entity.setAmount(domain.getAmount());
        entity.setTermMonths(domain.getTermMonths());
        entity.setInterestRate(domain.getInterestRate());
        entity.setRequestDate(domain.getRequestDate());
        entity.setStatus(domain.getStatus());
        entity.setIdempotencyKey(domain.getIdempotencyKey());
        entity.setProductType(domain.getProductType());
        entity.setChannel(domain.getChannel());
        return entity;
    }
}