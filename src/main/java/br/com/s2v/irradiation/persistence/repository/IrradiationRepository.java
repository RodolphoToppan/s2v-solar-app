package br.com.s2v.irradiation.persistence.repository;

import br.com.s2v.irradiation.persistence.model.CityIrradiation;

public interface IrradiationRepository extends BaseRepository<CityIrradiation> {
    CityIrradiation findByCityId(Long cityId);
}
