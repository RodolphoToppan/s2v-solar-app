package br.com.s2v.solar.core.dto.request;

import br.com.s2v.solar.core.dto.common.EnergyTariff;
import br.com.s2v.solar.core.enums.PowerSupplyType;
import br.com.s2v.solar.core.enums.UCType;
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
    private EnergyTariff energyTariff;
    private BigDecimal consumption;
    private BigDecimal publicLighting;
    private PowerSupplyType powerSupply;
    private UCType ucType;
    private BigDecimal nightConsumptionPercentage;
}
