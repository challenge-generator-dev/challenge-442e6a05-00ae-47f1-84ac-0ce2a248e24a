package com.pragma.loanrequest.domain.port.out;

import com.pragma.loanrequest.domain.model.LoanRequest;
import java.util.UUID;

public interface ExternalSystemPort {
    
    boolean checkRiskBureau(String customerId, LoanRequest loanRequest);
    
    boolean checkBankingCore(String customerId, LoanRequest loanRequest);
    
    RiskBureauResponse getRiskAssessment(String customerId);
    
    BankingCoreResponse getBankingProfile(String customerId);
    
    record RiskBureauResponse(
        String customerId,
        String riskScore,
        String riskLevel,
        boolean hasOutstandingDebts,
        int creditHistoryYears
    ) {}
    
    record BankingCoreResponse(
        String customerId,
        BigDecimal accountBalance,
        String accountStatus,
        int monthlyIncome,
        boolean hasActiveLoans
    ) {
        public BankingCoreResponse {
            if (accountBalance == null) {
                accountBalance = BigDecimal.ZERO;
            }
        }
    }
}