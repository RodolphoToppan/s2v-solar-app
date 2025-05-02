package br.com.s2v.irradiation.persistence.repository.jpa;

import br.com.s2v.irradiation.persistence.model.State;
import br.com.s2v.irradiation.persistence.repository.StateRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateJpaRepository extends JpaRepository<State, Long>, StateRepository {
}
