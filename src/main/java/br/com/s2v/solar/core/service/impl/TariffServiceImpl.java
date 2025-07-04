package br.com.s2v.solar.core.service.impl;

import br.com.s2v.solar.core.dto.request.TariffRequest;
import br.com.s2v.solar.core.service.TariffService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@AllArgsConstructor
@Service
public class TariffServiceImpl implements TariffService {

    @Override
    public ResponseEntity<BigDecimal> calculate(TariffRequest tariff) {
        var icmsFactor = BigDecimal.ONE.subtract(tariff.getIcms());
        var pisCofinsFactor = BigDecimal.ONE.subtract(tariff.getPis().add(tariff.getCofins()));
        var denominator = icmsFactor.multiply(pisCofinsFactor);

        var teWithTax = tariff.getTeNoTax().divide(denominator, RoundingMode.HALF_UP);
        var tusdWithTax = tariff.getTusdNoTax().divide(denominator, RoundingMode.HALF_UP);
        var finalTariff = teWithTax.add(tusdWithTax).setScale(2, RoundingMode.HALF_UP);

        return ResponseEntity.ok(finalTariff);
    }
}
