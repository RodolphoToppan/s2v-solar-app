package br.com.s2v.solar.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinancialCalculationResponse {
    private BigDecimal monthlyInvoiceNoSolar;
    private BigDecimal monthlyInvoiceSolar;
    private BigDecimal monthlySavings;
    private BigDecimal annualInvoiceNoSolar;
    private BigDecimal annualInvoiceSolar;
    private BigDecimal annualSavings;
}
