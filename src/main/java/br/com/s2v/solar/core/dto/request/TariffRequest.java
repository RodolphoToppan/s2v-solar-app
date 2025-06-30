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
public class TariffRequest {
    private BigDecimal teNoTax;
    private BigDecimal tusdNoTax;
    private BigDecimal pis;
    private BigDecimal cofins;
    private BigDecimal icms;
}
