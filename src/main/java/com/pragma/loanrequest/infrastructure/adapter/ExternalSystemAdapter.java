package com.pragma.loanrequest.infrastructure.adapter;

import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.domain.port.out.ExternalSystemPort;
import com.pragma.loanrequest.infrastructure.exception.LoanRequestException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ExternalSystemAdapter implements ExternalSystemPort {

    private static final Logger log = LoggerFactory.getLogger(ExternalSystemAdapter.class);
    private static final String RISK_BUREAU_CIRCUIT = "riskBureauCircuit";
    private static final String BANKING_CORE_CIRCUIT = "bankingCoreCircuit";

    @Override
    @CircuitBreaker(name = RISK_BUREAU_CIRCUIT, fallbackMethod = "riskBureauFallback")
    public RiskBureauResult checkRiskBureau(LoanRequest loanRequest) {
        log.info("Consultando buró de riesgos para customerId: {}", loanRequest.getCustomerId());
        
        if (loanRequest.getCustomerId() == null || loanRequest.getCustomerId().isBlank()) {
            throw new LoanRequestException("ID de cliente inválido para consulta al buró de riesgos", "INVALID_CUSTOMER_ID", 400);
        }
        
        simulateExternalCall(200);
        
        boolean hasHighRisk = evaluateRiskLevel(loanRequest);
        int score = calculateRiskScore(loanRequest);
        
        log.info("Resultado del buró de riesgos - CustomerId: {}, Score: {}, HighRisk: {}", 
                loanRequest.getCustomerId(), score, hasHighRisk);
        
        return new RiskBureauResult(!hasHighRisk, score, "SUCCESS");
    }

    @Override
    @CircuitBreaker(name = BANKING_CORE_CIRCUIT, fallbackMethod = "bankingCoreFallback")
    public BankingCoreResult checkBankingCore(LoanRequest loanRequest) {
        log.info("Consultando core bancario para customerId: {} con monto: {}", 
                loanRequest.getCustomerId(), loanRequest.getAmount());
        
        validateBankingCoreInput(loanRequest);
        
        simulateExternalCall(300);
        
        BigDecimal availableCredit = calculateAvailableCredit(loanRequest);
        boolean hasSufficientFunds = loanRequest.getAmount().compareTo(availableCredit.multiply(BigDecimal.valueOf(0.5))) <= 0;
        String accountStatus = determineAccountStatus(loanRequest.getCustomerId());
        
        log.info("Resultado del core bancario - CustomerId: {}, AvailableCredit: {}, HasSufficient: {}", 
                loanRequest.getCustomerId(), availableCredit, hasSufficientFunds);
        
        return new BankingCoreResult(hasSufficientFunds, availableCredit, accountStatus, "SUCCESS");
    }

    private boolean evaluateRiskLevel(LoanRequest loanRequest) {
        BigDecimal amount = loanRequest.getAmount();
        if (amount.compareTo(BigDecimal.valueOf(50000)) > 0) {
            return true;
        }
        
        Integer term = loanRequest.getTermMonths();
        if (term != null && term > 36) {
            return true;
        }
        
        return false;
    }

    private int calculateRiskScore(LoanRequest loanRequest) {
        int baseScore = 700;
        BigDecimal amount = loanRequest.getAmount();
        
        if (amount.compareTo(BigDecimal.valueOf(10000)) > 0) {
            baseScore -= 50;
        }
        if (amount.compareTo(BigDecimal.valueOf(30000)) > 0) {
            baseScore -= 50;
        }
        
        Integer term = loanRequest.getTermMonths();
        if (term != null && term > 24) {
            baseScore -= 30;
        }
        
        return Math.max(baseScore, 300);
    }

    private void validateBankingCoreInput(LoanRequest loanRequest) {
        if (loanRequest.getCustomerId() == null || loanRequest.getCustomerId().isBlank()) {
            throw new LoanRequestException("ID de cliente requerido para consulta al core bancario", "INVALID_CUSTOMER_ID", 400);
        }
        if (loanRequest.getAmount() == null || loanRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new LoanRequestException("Monto válido requerido para consulta al core bancario", "INVALID_AMOUNT", 400);
        }
    }

    private BigDecimal calculateAvailableCredit(LoanRequest loanRequest) {
        String customerId = loanRequest.getCustomerId();
        int hash = customerId.hashCode();
        BigDecimal baseAmount = BigDecimal.valueOf(Math.abs(hash % 100000) + 10000);
        return baseAmount;
    }

    private String determineAccountStatus(String customerId) {
        int hash = customerId.hashCode();
        int statusCode = Math.abs(hash % 3);
        return switch (statusCode) {
            case 0 -> "ACTIVE";
            case 1 -> "RESTRICTED";
            default -> "INACTIVE";
        };
    }

    private void simulateExternalCall(int durationMs) {
        try {
            Thread.sleep(durationMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Llamada externa interrumpida");
        }
    }

    private RiskBureauResult riskBureauFallback(LoanRequest loanRequest, Throwable t) {
        log.error("Fallback activado para buró de riesgos: {}", t.getMessage());
        return new RiskBureauResult(true, 650, "FALLBACK");
    }

    private BankingCoreResult bankingCoreFallback(LoanRequest loanRequest, Throwable t) {
        log.error("Fallback activado para core bancario: {}", t.getMessage());
        return new BankingCoreResult(true, BigDecimal.valueOf(100000), "UNKNOWN", "FALLBACK");
    }

    public record RiskBureauResult(boolean approved, int riskScore, String status) {}
    public record BankingCoreResult(boolean hasSufficientFunds, BigDecimal availableCredit, String accountStatus, String status) {}
}