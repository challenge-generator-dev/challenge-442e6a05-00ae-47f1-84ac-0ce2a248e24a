package com.pragma.loanrequest.domain.port.out;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TracingPort {
    
    void recordOperation(TracingRecord record);
    
    List<TracingRecord> getOperationsByLoanRequestId(UUID loanRequestId);
    
    List<TracingRecord> getOperationsByCustomerId(String customerId);
    
    TracingRecord getOperationById(UUID operationId);
    
    record TracingRecord(
        UUID id,
        UUID loanRequestId,
        String customerId,
        String operationType,
        String operationDescription,
        LocalDateTime timestamp,
        String status,
        String errorMessage,
        long durationMs
    ) {
        public TracingRecord {
            if (id == null) {
                id = UUID.randomUUID();
            }
            if (timestamp == null) {
                timestamp = LocalDateTime.now();
            }
            if (status == null) {
                status = "SUCCESS";
            }
            if (durationMs < 0) {
                durationMs = 0;
            }
        }
        
        public static TracingRecord createSuccess(UUID loanRequestId, String customerId,
                String operationType, String operationDescription, long durationMs) {
            return new TracingRecord(null, loanRequestId, customerId, operationType,
                    operationDescription, null, "SUCCESS", null, durationMs);
        }
        
        public static TracingRecord createError(UUID loanRequestId, String customerId,
                String operationType, String operationDescription, String errorMessage, long durationMs) {
            return new TracingRecord(null, loanRequestId, customerId, operationType,
                    operationDescription, null, "ERROR", errorMessage, durationMs);
        }
    }
}