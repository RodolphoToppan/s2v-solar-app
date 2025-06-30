package br.com.s2v.irradiation.persistence.repository;

import br.com.s2v.common.persistence.repository.BaseRepository;
import br.com.s2v.irradiation.persistence.model.State;

public interface StateRepository extends BaseRepository<State> {
    State findByStateName(String stateName);
}
