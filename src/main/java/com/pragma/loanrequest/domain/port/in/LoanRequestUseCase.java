package com.pragma.loanrequest.domain.port.in;

import com.pragma.loanrequest.domain.model.LoanRequest;
import java.util.UUID;

public interface LoanRequestUseCase {
    /**
     * Crea una nueva solicitud de préstamo con garantía de idempotencia.
     * @param loanRequest La solicitud de préstamo a crear
     * @return La solicitud de préstamo creada
     * @throws IllegalArgumentException Si la solicitud es inválida
     * @throws IllegalStateException Si ya existe una solicitud con la misma clave de idempotencia
     */
    LoanRequest createLoanRequest(LoanRequest loanRequest);

    /**
     * Obtiene una solicitud de préstamo por su ID.
     * @param id El ID de la solicitud
     * @return La solicitud de préstamo encontrada
     * @throws java.util.NoSuchElementException Si no se encuentra la solicitud
     */
    LoanRequest getLoanRequestById(UUID id);

    /**
     * Valida una solicitud de préstamo antes de procesarla.
     * @param loanRequest La solicitud a validar
     * @throws IllegalArgumentException Si la solicitud no pasa las validaciones de negocio
     */
    void validateLoanRequest(LoanRequest loanRequest);
}