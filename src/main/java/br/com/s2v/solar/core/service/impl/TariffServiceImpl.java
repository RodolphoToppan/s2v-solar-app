package br.com.s2v.solar.core.service.impl;

import br.com.s2v.solar.core.dto.request.TariffRequest;
import br.com.s2v.solar.core.service.TariffService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@AllArgsConstructor
@Service
public class TariffServiceImpl implements TariffService {

    @Override
    public ResponseEntity<BigDecimal> calculate(TariffRequest tariff) {
        return null;
    }
}
