package br.com.s2v.solar.core.service;

import br.com.s2v.solar.core.dto.request.TariffRequest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface TariffService {

    ResponseEntity<BigDecimal> calculate(TariffRequest tariff);
}
