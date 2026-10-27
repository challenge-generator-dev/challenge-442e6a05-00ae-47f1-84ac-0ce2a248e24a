package com.pragma.loanrequest.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class LoanRequest {
    private final UUID id;
    private final String customerId;
    private final BigDecimal amount;
    private final Integer termMonths;
    private final BigDecimal interestRate;
    private final LocalDate requestDate;
    private final String status;
    private final String idempotencyKey;
    private final String productType;
    private final String channel;

    public LoanRequest(UUID id, String customerId, BigDecimal amount, Integer termMonths,
                      BigDecimal interestRate, LocalDate requestDate, String status,
                      String idempotencyKey, String productType, String channel) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.termMonths = termMonths;
        this.interestRate = interestRate;
        this.requestDate = requestDate;
        this.status = status;
        this.idempotencyKey = idempotencyKey;
        this.productType = productType;
        this.channel = channel;
        validate();
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public String getStatus() {
        return status;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getProductType() {
        return productType;
    }

    public String getChannel() {
        return channel;
    }

    private void validate() {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo o vacío");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        if (termMonths == null || termMonths <= 0) {
            throw new IllegalArgumentException("El plazo en meses debe ser mayor a cero");
        }
        if (interestRate == null || interestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La tasa de interés no puede ser negativa");
        }
        if (requestDate == null || requestDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de solicitud no puede ser nula o futura");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío");
        }
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("La clave de idempotencia no puede ser nula o vacía");
        }
        if (productType == null || productType.isBlank()) {
            throw new IllegalArgumentException("El tipo de producto no puede ser nulo o vacío");
        }
        if (channel == null || channel.isBlank()) {
            throw new IllegalArgumentException("El canal no puede ser nulo o vacío");
        }
    }

    public LoanRequest withStatus(String newStatus) {
        return new LoanRequest(
            this.id,
            this.customerId,
            this.amount,
            this.termMonths,
            this.interestRate,
            this.requestDate,
            newStatus,
            this.idempotencyKey,
            this.productType,
            this.channel
        );
    }
}