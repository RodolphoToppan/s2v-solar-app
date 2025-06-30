package br.com.s2v.irradiation.persistence.repository;

import br.com.s2v.common.persistence.repository.BaseRepository;
import br.com.s2v.irradiation.persistence.model.City;

public interface CityRepository extends BaseRepository<City> {
    City findByCityNameAndStateId(String cityName, Long stateId);

}
