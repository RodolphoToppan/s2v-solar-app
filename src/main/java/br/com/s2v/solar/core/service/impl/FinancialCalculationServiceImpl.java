package br.com.s2v.solar.core.service.impl;

import br.com.s2v.solar.core.dto.request.FinancialCalculationRequest;
import br.com.s2v.solar.core.dto.response.FinancialCalculationResponse;
import br.com.s2v.solar.core.service.FinancialCalculationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@AllArgsConstructor
@Service
public class FinancialCalculationServiceImpl implements FinancialCalculationService {
    private static final int MONTHS_IN_YEAR = 12;
    private static final int SCALE_TO_ROUND = 2;
    private static final int SCALE_FOR_HIGH_PRECISION = 6;
    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    @Override
    public ResponseEntity<FinancialCalculationResponse> calculate(FinancialCalculationRequest request) {
        var consumption = request.getConsumption();
        var publicLighting = request.getPublicLighting();

        var totalTariffWithTax = calculateTotalTariffWithTax(request);

        var monthlyInvoiceNoSolar = calculateBillWithoutSolar(totalTariffWithTax, consumption, publicLighting);

        var totalTariffWithDiscount = calculateTotalTariffWithDiscount(request);
        var availability = request.getPowerSupply().getAvailability();
        var nightConsumption = calculateNightConsumption(request);
        var compensatedConsumption = calculateCompensatedConsumption(
                consumption,
                nightConsumption,
                availability
        );
        var taxValue = calculateTaxValue(
                totalTariffWithTax, availability, nightConsumption,
                consumption, totalTariffWithDiscount, compensatedConsumption
        );
        var monthlyInvoiceSolar = taxValue.add(publicLighting);

        var monthlySavings = monthlyInvoiceNoSolar.subtract(monthlyInvoiceSolar);

        var annualInvoiceNoSolar = monthlyInvoiceNoSolar.multiply(BigDecimal.valueOf(MONTHS_IN_YEAR));
        var annualInvoiceSolar = monthlyInvoiceSolar.multiply(BigDecimal.valueOf(MONTHS_IN_YEAR));
        var annualSavings = monthlySavings.multiply(BigDecimal.valueOf(MONTHS_IN_YEAR));

        return ResponseEntity.ok(
                new FinancialCalculationResponse(
                        roundToTwoDecimalPlaces(monthlyInvoiceNoSolar),
                        roundToTwoDecimalPlaces(monthlyInvoiceSolar),
                        roundToTwoDecimalPlaces(monthlySavings),
                        roundToTwoDecimalPlaces(annualInvoiceNoSolar),
                        roundToTwoDecimalPlaces(annualInvoiceSolar),
                        roundToTwoDecimalPlaces(annualSavings)
                )
        );
    }

    private BigDecimal roundToTwoDecimalPlaces(BigDecimal value) {
        return value.setScale(SCALE_TO_ROUND, RoundingMode.HALF_EVEN);
    }

    private BigDecimal calculateDenominatorIcmsPart(BigDecimal icmsPercentage) {
        return BigDecimal.ONE.subtract(icmsPercentage);
    }

    private BigDecimal calculateDenominatorPisAndCofinsPart(
            BigDecimal pisPercentage,
            BigDecimal cofinsPercentage
    ) {
        return BigDecimal.ONE.subtract(pisPercentage.add(cofinsPercentage));
    }

    private BigDecimal calculateDenominator(BigDecimal icmsPart, BigDecimal pisAndCofinsPart) {
        return icmsPart.multiply(pisAndCofinsPart);
    }

    private BigDecimal calculateTeWithTaxOrDiscount(
            BigDecimal teWithoutTax,
            BigDecimal icmsPercentage,
            BigDecimal pisPercentage,
            BigDecimal cofinsPercentage
    ) {
        var denominatorIcmsPart = calculateDenominatorIcmsPart(icmsPercentage);
        var denominatorPisAndCofinsPart = calculateDenominatorPisAndCofinsPart(pisPercentage, cofinsPercentage);
        var denominator = calculateDenominator(denominatorIcmsPart, denominatorPisAndCofinsPart);
        return teWithoutTax.divide(denominator, SCALE_FOR_HIGH_PRECISION, RoundingMode.HALF_UP);
    }


    private BigDecimal calculateTotalTariffWithTax(FinancialCalculationRequest request) {
        var teWithoutTax = request.getEnergyTariff().getTeWithoutTax();
        var tusdWithoutTax = request.getEnergyTariff().getTusdWithoutTax();
        var icmsPercentage = request.getEnergyTariff().getIcms();
        var pisPercentage = request.getEnergyTariff().getPis();
        var cofinsPercentage = request.getEnergyTariff().getCofins();

        var denominatorIcmsPart = calculateDenominatorIcmsPart(icmsPercentage);
        var denominatorPisAndCofinsPart = calculateDenominatorPisAndCofinsPart(pisPercentage, cofinsPercentage);
        var denominator = calculateDenominator(denominatorIcmsPart, denominatorPisAndCofinsPart);
        var teWithTax = calculateTeWithTaxOrDiscount(
                teWithoutTax,
                icmsPercentage,
                pisPercentage,
                cofinsPercentage
        );
        var tusdWithTax = tusdWithoutTax.divide(denominator, SCALE_FOR_HIGH_PRECISION, RoundingMode.HALF_UP);

        return teWithTax.add(tusdWithTax);
    }

    private BigDecimal calculateTotalTariffWithDiscount(FinancialCalculationRequest request) {
        var teWithDiscount = calculateTEWithDiscount(request);
        var tusdWithDiscount = calculateTUSDWithDiscount(request);
        return teWithDiscount.add(tusdWithDiscount);
    }

    private BigDecimal calculateTEWithDiscount(FinancialCalculationRequest request) {
        return calculateTeWithTaxOrDiscount(
                request.getEnergyTariff().getTeWithoutTax(),
                request.getEnergyTariff().getIcms(),
                request.getEnergyTariff().getPis(),
                request.getEnergyTariff().getCofins()
        );
    }

    private BigDecimal calculateTUSDWithDiscount(FinancialCalculationRequest request) {
        var tusdWithoutTax = request.getEnergyTariff().getTusdWithoutTax();
        var wireB = request.getEnergyTariff().getWireB();
        var escalation = request.getEnergyTariff().getEscalation();
        var pisPercentage = request.getEnergyTariff().getPis();
        var cofinsPercentage = request.getEnergyTariff().getCofins();

        var denominator = calculateDenominatorPisAndCofinsPart(pisPercentage, cofinsPercentage);
        var nominator = tusdWithoutTax.divide(denominator, SCALE_FOR_HIGH_PRECISION, RoundingMode.HALF_UP);
        var wireBPaid = wireB.multiply(escalation);

        return nominator.subtract(wireBPaid);
    }

    private BigDecimal calculateBillWithoutSolar(
            BigDecimal totalTariffWithTax,
            BigDecimal consumption,
            BigDecimal publicLighting
    ) {
        return totalTariffWithTax
                .multiply(consumption)
                .add(publicLighting);
    }

    private BigDecimal calculateNightConsumption(FinancialCalculationRequest request) {
        if (request.getUcType().isFullNightConsumption()) {
            return BigDecimal.ONE;
        }
        return request.getNightConsumptionPercentage();
    }

    private BigDecimal calculateCompensatedConsumption(
            BigDecimal consumption,
            BigDecimal nightConsumption,
            BigDecimal availability
    ) {
        return consumption
                .multiply(nightConsumption)
                .subtract(availability);
    }

    private BigDecimal calculateTaxValue(
            BigDecimal totalTariffWithTax,
            BigDecimal availability,
            BigDecimal nightConsumption,
            BigDecimal consumption,
            BigDecimal totalTariffWithDiscount,
            BigDecimal compensatedConsumption
    ) {
        BigDecimal withAvailability = totalTariffWithTax.multiply(availability);
        BigDecimal withConsumption = totalTariffWithTax
                .multiply(nightConsumption)
                .multiply(consumption)
                .subtract(totalTariffWithDiscount.multiply(compensatedConsumption));

        return withAvailability.max(withConsumption);
    }
}
