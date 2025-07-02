package br.com.s2v.solar.core.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnergyTariff {
    private BigDecimal teWithoutTax;
    private BigDecimal tusdWithoutTax;
    private BigDecimal icms;
    private BigDecimal pis;
    private BigDecimal cofins;
    private BigDecimal wireB;
    private BigDecimal escalation;
}
