package com.pragma.loanrequest.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class LoanRequestException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus httpStatus;

    public LoanRequestException(String message, String errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public LoanRequestException(String message, String errorCode, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static LoanRequestException alreadyExists(String idempotencyKey) {
        return new LoanRequestException(
                "Ya existe una solicitud de préstamo con la clave de idempotencia: " + idempotencyKey,
                "DUPLICATE_IDEMPOTENCY_KEY",
                HttpStatus.CONFLICT
        );
    }

    public static LoanRequestException notFound(String id) {
        return new LoanRequestException(
                "No se encontró la solicitud de préstamo con ID: " + id,
                "LOAN_REQUEST_NOT_FOUND",
                HttpStatus.NOT_FOUND
        );
    }

    public static LoanRequestException invalidAmount(BigDecimal amount) {
        return new LoanRequestException(
                "El monto solicitado debe ser mayor a cero. Valor recibido: " + amount,
                "INVALID_AMOUNT",
                HttpStatus.BAD_REQUEST
        );
    }

    public static LoanRequestException invalidTerm(Integer termMonths) {
        return new LoanRequestException(
                "El plazo debe estar entre 1 y 360 meses. Valor recibido: " + termMonths,
                "INVALID_TERM",
                HttpStatus.BAD_REQUEST
        );
    }

    public static LoanRequestException invalidCustomerId(String customerId) {
        return new LoanRequestException(
                "El ID del cliente es obligatorio y no puede estar vacío. Valor recibido: " + customerId,
                "INVALID_CUSTOMER_ID",
                HttpStatus.BAD_REQUEST
        );
    }

    public static LoanRequestException externalServiceFailure(String serviceName, String message) {
        return new LoanRequestException(
                "Error en el servicio externo " + serviceName + ": " + message,
                "EXTERNAL_SERVICE_FAILURE",
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }

    public static LoanRequestException circuitBreakerOpen(String serviceName) {
        return new LoanRequestException(
                "El circuito está abierto para el servicio: " + serviceName + ". Por favor, intente más tarde.",
                "CIRCUIT_BREAKER_OPEN",
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }

    public static LoanRequestException validationError(String message) {
        return new LoanRequestException(
                message,
                "VALIDATION_ERROR",
                HttpStatus.BAD_REQUEST
        );
    }

    public static LoanRequestException internalError(String message) {
        return new LoanRequestException(
                message,
                "INTERNAL_ERROR",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}