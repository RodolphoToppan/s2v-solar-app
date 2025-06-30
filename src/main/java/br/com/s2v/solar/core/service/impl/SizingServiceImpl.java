package br.com.s2v.solar.core.service.impl;

import br.com.s2v.irradiation.core.service.IrradiationService;
import br.com.s2v.solar.core.dto.common.BillList;
import br.com.s2v.solar.core.service.SizingService;
import br.com.s2v.solar.persistence.model.MonthGeneration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@AllArgsConstructor
@Service
public class SizingServiceImpl implements SizingService {
    private static final BigDecimal LABOR_VALUE_BY_MODULE = BigDecimal.valueOf(80);
    private static final BigDecimal MINIMUM_LABOR_VALUE = BigDecimal.valueOf(800);
    private static final int MINIMUM_NUMBER_OF_MODULES = 10;
    private final IrradiationService irradiationService;

    private BigDecimal calculateGeneration(BigDecimal power, BigDecimal irradiation, int days) {
        return power
                .multiply(BigDecimal.valueOf(0.75))
                .multiply(irradiation)
                .divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(days));
    }

    @Override
    public BigDecimal calculateAnnualGenerationAverage(String city, String state, BigDecimal power) {
        var irradiation = irradiationService.getIrradiationByCity(city, state);
        if (irradiation == null) {
            throw new NullPointerException("Irradiation data is null");
        }
        return calculateGeneration(power, irradiation.getAnnualIrradiation(), 365)
                .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
    }

    @Override
    public MonthGeneration calculateMonthGeneration(String city, String state, BigDecimal power) {
        var irradiation = irradiationService.getIrradiationByCity(city, state);
        if (irradiation == null) {
            throw new NullPointerException("Irradiation data is null");
        }

        return new MonthGeneration(
                1L,
                calculateGeneration(power, irradiation.getJan(), 31),
                calculateGeneration(power, irradiation.getFeb(), 28),
                calculateGeneration(power, irradiation.getMar(), 31),
                calculateGeneration(power, irradiation.getApr(), 30),
                calculateGeneration(power, irradiation.getMay(), 31),
                calculateGeneration(power, irradiation.getJun(), 30),
                calculateGeneration(power, irradiation.getJul(), 31),
                calculateGeneration(power, irradiation.getAug(), 31),
                calculateGeneration(power, irradiation.getSep(), 30),
                calculateGeneration(power, irradiation.getOct(), 31),
                calculateGeneration(power, irradiation.getNov(), 30),
                calculateGeneration(power, irradiation.getDec(), 31)
        );
    }

    @Override
    public BigDecimal calculateBillsConsumption(BillList billList) {
        return billList.getBills().stream()
                       .map(bill -> {
                           var c = bill.getConsumption();
                           if (c == null) return BigDecimal.ZERO;
                           return c.getJan()
                                   .add(c.getFeb())
                                   .add(c.getMar())
                                   .add(c.getApr())
                                   .add(c.getMay())
                                   .add(c.getJun())
                                   .add(c.getJul())
                                   .add(c.getAug())
                                   .add(c.getSep())
                                   .add(c.getOct())
                                   .add(c.getNov())
                                   .add(c.getDec());
                       })
                       .reduce(BigDecimal.ZERO, BigDecimal::add)
                       .setScale(2, RoundingMode.HALF_UP);
    }
    //    @Override
    //    public BigDecimal calculateBillsConsumption(BillList billList) {
    //        var billsConsumption = billList.getBills().stream()
    //                .filter(bill -> bill.getConsumption().compareTo(BigDecimal.ZERO) != 0)
    //                .map(Bill::getConsumption)
    //                .reduce(BigDecimal.ZERO, BigDecimal::add);
    //
    //        var calculatedConsumption = billList.stream()
    //                .filter(bill -> bill.getConsumption().compareTo(BigDecimal.ZERO) == 0)
    //                .map(bill -> bill.getAmount().divide(bill.getTariff(), 2, RoundingMode.HALF_UP))
    //                .reduce(BigDecimal.ZERO, BigDecimal::add);
    //
    //        return billsConsumption.add(calculatedConsumption).setScale(2, RoundingMode.HALF_UP);
    //    }

    @Override
    public BigDecimal calculateNumberModules(
            BigDecimal billConsumption,
            BigDecimal modulesPower,
            BigDecimal annualIrradiationAverage
    ) {
        return billConsumption.multiply(BigDecimal.valueOf(12))
                              .divide(
                                      BigDecimal.valueOf(0.75)
                                                .multiply(annualIrradiationAverage)
                                                .divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP)
                                                .multiply(BigDecimal.valueOf(365)),
                                      0, RoundingMode.CEILING
                              ).multiply(BigDecimal.valueOf(1000))
                              .divide(modulesPower, 0, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal calculateSystemPower(BigDecimal numberModules, BigDecimal modulesPower) {
        return numberModules.multiply(modulesPower)
                            .divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateLabor(BigDecimal modulesNumber) {
        if (modulesNumber == null || modulesNumber.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        return modulesNumber.compareTo(BigDecimal.valueOf(MINIMUM_NUMBER_OF_MODULES)) < 0
               ? MINIMUM_LABOR_VALUE
               : modulesNumber.multiply(LABOR_VALUE_BY_MODULE);
    }

    @Override
    public BigDecimal calculateFixedCosts(
            BigDecimal kitValue,
            BigDecimal laborValue,
            BigDecimal powerInput,
            BigDecimal materialAc,
            BigDecimal travel,
            BigDecimal extraCosts
    ) {
        return kitValue
                .add(laborValue)
                .add(powerInput)
                .add(materialAc)
                .add(travel)
                .add(extraCosts);
    }

    @Override
    public BigDecimal calculateCommission(BigDecimal commissionPercentage, BigDecimal salePrice) {
        return commissionPercentage
                .multiply(salePrice)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateTotalTax(BigDecimal taxRate, BigDecimal salePrice, BigDecimal kitValue) {
        return taxRate.multiply(salePrice.subtract(kitValue));
    }

    @Override
    public BigDecimal calculateTotalCosts(BigDecimal fixedCosts, BigDecimal commissionValue, BigDecimal totalTax) {
        return fixedCosts.add(commissionValue).add(totalTax);
    }

    @Override
    public BigDecimal calculateMargin(BigDecimal salePrice, BigDecimal totalCosts) {
        return salePrice.subtract(totalCosts)
                        .divide(salePrice, 4, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculatePricePerWp(
            BigDecimal systemPowerInWp,
            BigDecimal fixedCosts,
            BigDecimal kitValue,
            BigDecimal commissionPercentage,
            BigDecimal taxPercentage,
            BigDecimal desiredMargin
    ) {
        // Convertendo percentuais para decimais
        //        var commissionRate = commissionPercentage.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
        //        var taxRate = taxPercentage.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
        //        var marginRate = desiredMargin.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);

        if (systemPowerInWp.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal pricePerWp = BigDecimal.ONE;
        BigDecimal obtainedMargin;
        int maxIterations = 10000;
        int iterations = 0;

        while (true) {
            // Calcula o preço de venda e custos
            BigDecimal salePrice = pricePerWp.multiply(systemPowerInWp);
            BigDecimal commission = salePrice.multiply(commissionPercentage);
            BigDecimal tax = salePrice.subtract(kitValue).multiply(taxPercentage);
            BigDecimal totalCosts = fixedCosts.add(commission).add(tax);

            // Calcula a margem obtida
            obtainedMargin = salePrice.subtract(totalCosts).divide(salePrice, 6, RoundingMode.HALF_UP);

            // Calcula o erro (diferença entre margem obtida e desejada)
            BigDecimal error = obtainedMargin.subtract(desiredMargin);

            // Verifica se atingiu a precisão desejada ou máximo de iterações
            if (error.abs().compareTo(BigDecimal.valueOf(0.000001)) < 0 || iterations > maxIterations) {
                return pricePerWp;
            }

            // Ajusta o preço baseado no erro (error * 5)
            pricePerWp = pricePerWp.subtract(error.multiply(BigDecimal.valueOf(5)));
            iterations++;
        }
    }
}