package com.pragma.loanrequest.infrastructure.adapter;

import com.pragma.loanrequest.domain.port.out.TracingPort;
import com.pragma.loanrequest.infrastructure.tracing.TracingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TracingAdapter implements TracingPort {

    private static final Logger log = LoggerFactory.getLogger(TracingAdapter.class);
    private static final String SERVICE_NAME = "loan-request-service";
    
    private final TracingService tracingService;

    public TracingAdapter(TracingService tracingService) {
        this.tracingService = tracingService;
    }

    @Override
    public void recordOperation(TracingRecord record) {
        log.debug("Registrando operación de trazabilidad: operationType={}, entityId={}", 
                record.operationType(), record.entityId());
        
        Map<String, Object> details = new HashMap<>();
        details.put("service", SERVICE_NAME);
        details.put("timestamp", Instant.now().toString());
        
        if (record.customerId() != null) {
            details.put("customerId", record.customerId());
        }
        if (record.amount() != null) {
            details.put("amount", record.amount().toString());
        }
        if (record.metadata() != null) {
            details.putAll(record.metadata());
        }
        
        tracingService.record(
            record.operationType(),
            record.entityId() != null ? record.entityId().toString() : null,
            record.status(),
            details
        );
        
        log.info("Operación registrada: type={}, entityId={}, status={}", 
                record.operationType(), record.entityId(), record.status());
    }

    @Override
    public void recordSuccess(String operationType, UUID entityId, String customerId, Map<String, Object> metadata) {
        TracingRecord record = new TracingRecord(
            operationType,
            entityId,
            customerId,
            null,
            "SUCCESS",
            metadata
        );
        recordOperation(record);
    }

    @Override
    public void recordFailure(String operationType, UUID entityId, String customerId, String errorCode, Map<String, Object> metadata) {
        Map<String, Object> enrichedMetadata = metadata != null ? new HashMap<>(metadata) : new HashMap<>();
        enrichedMetadata.put("errorCode", errorCode);
        
        TracingRecord record = new TracingRecord(
            operationType,
            entityId,
            customerId,
            null,
            "FAILED",
            enrichedMetadata
        );
        recordOperation(record);
    }

    @Override
    public void recordExternalCall(String operationType, String externalSystem, UUID entityId, 
                                    String customerId, long durationMs, boolean success, Map<String, Object> metadata) {
        Map<String, Object> enrichedMetadata = metadata != null ? new HashMap<>(metadata) : new HashMap<>();
        enrichedMetadata.put("externalSystem", externalSystem);
        enrichedMetadata.put("durationMs", durationMs);
        enrichedMetadata.put("success", success);
        
        String status = success ? "SUCCESS" : "FAILED";
        TracingRecord record = new TracingRecord(
            operationType,
            entityId,
            customerId,
            null,
            status,
            enrichedMetadata
        );
        recordOperation(record);
        
        log.info("Llamada externa registrada: system={}, operation={}, duration={}ms, success={}", 
                externalSystem, operationType, durationMs, success);
    }

    public record TracingRecord(
        String operationType,
        UUID entityId,
        String customerId,
        java.math.BigDecimal amount,
        String status,
        Map<String, Object> metadata
    ) {}
}