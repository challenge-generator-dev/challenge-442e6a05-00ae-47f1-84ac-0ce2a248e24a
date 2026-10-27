package com.pragma.loanrequest.infrastructure.persistence.repository;

import com.pragma.loanrequest.infrastructure.persistence.entity.LoanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequestEntity, UUID> {

    boolean existsByIdempotencyKey(String idempotencyKey);

    Optional<LoanRequestEntity> findByIdempotencyKey(String idempotencyKey);

    @Query("SELECT lr FROM LoanRequestEntity lr WHERE lr.customerId = :customerId AND lr.status = :status")
    Optional<LoanRequestEntity> findByCustomerIdAndStatus(
            @Param("customerId") String customerId,
            @Param("status") String status
    );

    @Query("SELECT COUNT(lr) FROM LoanRequestEntity lr WHERE lr.customerId = :customerId AND lr.status IN ('APPROVED', 'ACTIVE')")
    Long countActiveLoansByCustomerId(@Param("customerId") String customerId);
}