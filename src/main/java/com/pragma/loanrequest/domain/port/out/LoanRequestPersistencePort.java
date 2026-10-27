package com.pragma.loanrequest.domain.port.out;

import com.pragma.loanrequest.domain.model.LoanRequest;
import java.util.Optional;
import java.util.UUID;

public interface LoanRequestPersistencePort {
    /**
     * Guarda una solicitud de préstamo en el sistema de persistencia.
     * @param loanRequest La solicitud a guardar
     * @return La solicitud guardada
     */
    LoanRequest save(LoanRequest loanRequest);

    /**
     * Busca una solicitud de préstamo por su ID.
     * @param id El ID de la solicitud
     * @return Optional con la solicitud si existe, vacío en caso contrario
     */
    Optional<LoanRequest> findById(UUID id);

    /**
     * Verifica si ya existe una solicitud con la misma clave de idempotencia.
     * @param idempotencyKey La clave de idempotencia
     * @return true si existe una solicitud con esa clave, false en caso contrario
     */
    boolean existsByIdempotencyKey(String idempotencyKey);

    /**
     * Busca una solicitud por su clave de idempotencia.
     * @param idempotencyKey La clave de idempotencia
     * @return Optional con la solicitud si existe, vacío en caso contrario
     */
    Optional<LoanRequest> findByIdempotencyKey(String idempotencyKey);
}