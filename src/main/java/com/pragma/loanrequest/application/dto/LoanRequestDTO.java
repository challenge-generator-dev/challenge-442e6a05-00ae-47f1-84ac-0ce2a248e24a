package com.pragma.loanrequest.application.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LoanRequestDTO(
    UUID id,
    
    @NotBlank(message = "El ID del cliente es obligatorio")
    @Size(min = 5, max = 50, message = "El ID del cliente debe tener entre 5 y 50 caracteres")
    String customerId,
    
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "100.00", message = "El monto mínimo es 100")
    @DecimalMax(value = "1000000.00", message = "El monto máximo es 1,000,000")
    BigDecimal amount,
    
    @NotNull(message = "El plazo en meses es obligatorio")
    @Min(value = 1, message = "El plazo mínimo es 1 mes")
    @Max(value = 360, message = "El plazo máximo es 360 meses")
    Integer termMonths,
    
    @NotNull(message = "La tasa de interés es obligatoria")
    @DecimalMin(value = "0.01", message = "La tasa mínima es 0.01%")
    @DecimalMax(value = "99.99", message = "La tasa máxima es 99.99%")
    BigDecimal interestRate,
    
    LocalDate requestDate,
    
    @NotBlank(message = "La clave de idempotencia es obligatoria")
    @Size(max = 100, message = "La clave de idempotencia no puede exceder 100 caracteres")
    String idempotencyKey,
    
    @NotBlank(message = "El tipo de producto es obligatorio")
    @Size(max = 50, message = "El tipo de producto no puede exceder 50 caracteres")
    String productType,
    
    @NotBlank(message = "El canal es obligatorio")
    @Size(max = 50, message = "El canal no puede exceder 50 caracteres")
    String channel,
    
    String status
) {
    public LoanRequestDTO {
        if (requestDate == null) {
            requestDate = LocalDate.now();
        }
        if (status == null || status.isBlank()) {
            status = "PENDING";
        }
    }
    
    public static LoanRequestDTO createNew(String customerId, BigDecimal amount, 
            Integer termMonths, BigDecimal interestRate, String idempotencyKey,
            String productType, String channel) {
        return new LoanRequestDTO(
            null,
            customerId,
            amount,
            termMonths,
            interestRate,
            LocalDate.now(),
            idempotencyKey,
            productType,
            channel,
            "PENDING"
        );
    }
    
    public boolean isValidForProcessing() {
        return customerId != null && !customerId.isBlank()
            && amount != null && amount.compareTo(BigDecimal.ZERO) > 0
            && termMonths != null && termMonths > 0
            && interestRate != null && interestRate.compareTo(BigDecimal.ZERO) > 0
            && idempotencyKey != null && !idempotencyKey.isBlank()
            && productType != null && !productType.isBlank()
            && channel != null && !channel.isBlank();
    }
    
    public String toLogString() {
        return String.format("LoanRequestDTO[id=%s, customerId=%s, amount=%s, termMonths=%s, " +
                "productType=%s, channel=%s, status=%s]", 
            id != null ? id.toString().substring(0, 8) : "null",
            customerId,
            amount,
            termMonths,
            productType,
            channel,
            status);
    }
}