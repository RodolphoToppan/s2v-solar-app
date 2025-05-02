package br.com.s2v.irradiation.core.service;


import br.com.s2v.irradiation.core.dto.response.CityIrradiation;

public interface IrradiationService {

    CityIrradiation getIrradiationByCity(String cityName, String stateAbbreviation);
}
