package br.com.s2v.irradiation.core.service.impl;

import br.com.s2v.irradiation.core.dto.response.CityIrradiation;
import br.com.s2v.irradiation.core.enums.StateName;
import br.com.s2v.irradiation.core.service.IrradiationService;
import br.com.s2v.irradiation.persistence.repository.CityRepository;
import br.com.s2v.irradiation.persistence.repository.IrradiationRepository;
import br.com.s2v.irradiation.persistence.repository.StateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class IrradiationServiceImpl implements IrradiationService {
    private final IrradiationRepository irradiationRepository;
    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    @Override
    public CityIrradiation getIrradiationByCity(String cityName, String stateAbbreviation) {
        var stateName = StateName.valueOf(stateAbbreviation).getFullName();
        var stateId = stateRepository.findByStateName(stateName).getId();
        var cityId = cityRepository.findByCityNameAndStateId(cityName, stateId).getId();
        var irradiation = irradiationRepository.findByCityId(cityId);

        return new CityIrradiation(
                cityName,
                stateName,
                irradiation.getAnnualIrradiation(),
                irradiation.getJan(),
                irradiation.getFeb(),
                irradiation.getMar(),
                irradiation.getApr(),
                irradiation.getMay(),
                irradiation.getJun(),
                irradiation.getJul(),
                irradiation.getAug(),
                irradiation.getSep(),
                irradiation.getOct(),
                irradiation.getNov(),
                irradiation.getDec()
        );
    }
}
