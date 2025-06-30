package br.com.s2v.solar.core.service;

import br.com.s2v.solar.core.dto.common.BillList;
import br.com.s2v.solar.persistence.model.MonthGeneration;

import java.math.BigDecimal;

public interface SizingService {

    BigDecimal calculateAnnualGenerationAverage(String city, String state, BigDecimal power);

    MonthGeneration calculateMonthGeneration(String city, String state, BigDecimal power);

    BigDecimal calculateBillsConsumption(BillList billList);

    BigDecimal calculateNumberModules(
            BigDecimal billConsumption,
            BigDecimal modulesPower,
            BigDecimal annualIrradiationAverage
    );

    BigDecimal calculateSystemPower(BigDecimal numberModules, BigDecimal modulesPower);

    BigDecimal calculateLabor(BigDecimal modulesNumber);

    BigDecimal calculateFixedCosts(
            BigDecimal kitValue,
            BigDecimal laborValue,
            BigDecimal powerInput,
            BigDecimal materialAc,
            BigDecimal travel,
            BigDecimal extraCosts
    );

    BigDecimal calculateCommission(BigDecimal commissionPercentage, BigDecimal salePrice);

    BigDecimal calculateTotalTax(BigDecimal taxRate, BigDecimal salePrice, BigDecimal kitValue);

    BigDecimal calculateTotalCosts(BigDecimal fixedCosts, BigDecimal commissionValue, BigDecimal totalTax);

    BigDecimal calculateMargin(BigDecimal salePrice, BigDecimal totalCosts);

    BigDecimal calculatePricePerWp(
            BigDecimal systemPowerInWp,
            BigDecimal fixedCosts,
            BigDecimal kitValue,
            BigDecimal commissionPercentage,
            BigDecimal taxPercentage,
            BigDecimal desiredMargin
    );
}
