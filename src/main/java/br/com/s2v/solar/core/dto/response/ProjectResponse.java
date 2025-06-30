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
public class ProjectResponse {
    private BigDecimal photovoltaicPower;
    private BigDecimal pricePerWp;
    private BigDecimal commissionValue;
    private BigDecimal totalTax;
    private BigDecimal totalCost;
    private BigDecimal margin;
    private BigDecimal salePrice;
}
