package br.com.s2v.solar.core.service;

import br.com.s2v.solar.core.dto.request.FinancialCalculationRequest;
import br.com.s2v.solar.core.dto.response.FinancialCalculationResponse;
import org.springframework.http.ResponseEntity;

public interface FinancialCalculationService {
    ResponseEntity<FinancialCalculationResponse> calculate(FinancialCalculationRequest request);
}
