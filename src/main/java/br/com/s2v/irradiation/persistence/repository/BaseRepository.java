package br.com.s2v.irradiation.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    T save(T model);

    Optional<T> findById(Long id);

    List<T> findAll();

    void deleteById(Long id);

    Page<T> findAll(Pageable pageable);

    boolean existsById(Long id);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities);
}
