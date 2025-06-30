package br.com.s2v.solar.core.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinancialCalculationRequest {
    //TODO: provavelmente terá um identificador do cliente que deverá ser enviado do front
    private BigDecimal escalation;
    private BigDecimal bWireValue;
}
