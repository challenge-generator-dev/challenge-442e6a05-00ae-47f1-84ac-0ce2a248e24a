package com.pragma.loanrequest.infrastructure.tracing;

import com.pragma.loanrequest.domain.port.out.TracingPort;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TracingService implements TracingPort {

    private static final Logger logger = LoggerFactory.getLogger(TracingService.class);
    private static final String SERVICE_NAME = "loanrequest-service";

    private final Map<String, TraceRecord> traceStore;

    public TracingService() {
        this.traceStore = new HashMap<>();
    }

    @Override
    public void recordOperation(String operationName, String requestId, String status, Map<String, Object> metadata) {
        String traceId = requestId != null ? requestId : UUID.randomUUID().toString();
        
        TraceRecord record = new TraceRecord(
            traceId,
            operationName,
            SERVICE_NAME,
            LocalDateTime.now(),
            status,
            metadata
        );
        
        traceStore.put(traceId, record);
        logger.info("Tracing recorded: operation={}, traceId={}, status={}", 
            operationName, traceId, status);
    }

    @Override
    public void recordSuccess(String operationName, String requestId, Map<String, Object> metadata) {
        recordOperation(operationName, requestId, "SUCCESS", metadata);
    }

    @Override
    public void recordError(String operationName, String requestId, String errorMessage, Map<String, Object> metadata) {
        Map<String, Object> errorMetadata = new HashMap<>();
        if (metadata != null) {
            errorMetadata.putAll(metadata);
        }
        errorMetadata.put("errorMessage", errorMessage);
        recordOperation(operationName, requestId, "ERROR", errorMetadata);
    }

    @Override
    public Map<String, Object> getTrace(String traceId) {
        TraceRecord record = traceStore.get(traceId);
        if (record == null) {
            return Map.of();
        }
        return record.toMap();
    }

    private static class TraceRecord {
        private final String traceId;
        private final String operationName;
        private final String serviceName;
        private final LocalDateTime timestamp;
        private final String status;
        private final Map<String, Object> metadata;

        public TraceRecord(String traceId, String operationName, String serviceName,
                          LocalDateTime timestamp, String status, Map<String, Object> metadata) {
            this.traceId = traceId;
            this.operationName = operationName;
            this.serviceName = serviceName;
            this.timestamp = timestamp;
            this.status = status;
            this.metadata = metadata != null ? metadata : Map.of();
        }

        public Map<String, Object> toMap() {
            Map<String, Object> map = new HashMap<>();
            map.put("traceId", traceId);
            map.put("operationName", operationName);
            map.put("serviceName", serviceName);
            map.put("timestamp", timestamp.toString());
            map.put("status", status);
            map.put("metadata", metadata);
            return map;
        }
    }
}