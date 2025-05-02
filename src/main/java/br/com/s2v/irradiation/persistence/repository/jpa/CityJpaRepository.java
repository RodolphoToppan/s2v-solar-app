package br.com.s2v.irradiation.persistence.repository.jpa;

import br.com.s2v.irradiation.persistence.model.City;
import br.com.s2v.irradiation.persistence.repository.CityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityJpaRepository extends JpaRepository<City, Long>, CityRepository {
}
