package br.com.s2v.irradiation.persistence.repository.jpa;

import br.com.s2v.irradiation.persistence.model.CityIrradiation;
import br.com.s2v.irradiation.persistence.repository.IrradiationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IrradiationJpaRepository extends JpaRepository<CityIrradiation, Long>, IrradiationRepository {
}
