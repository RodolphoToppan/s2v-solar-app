package br.com.s2v.solar.persistence.repository.jpa;

import br.com.s2v.solar.persistence.model.MonthGeneration;
import br.com.s2v.solar.persistence.repository.MonthGenerationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthGenerationJpaRepository extends JpaRepository<MonthGeneration, Long>, MonthGenerationRepository {
}
