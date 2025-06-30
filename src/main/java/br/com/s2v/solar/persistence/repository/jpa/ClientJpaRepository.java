package br.com.s2v.solar.persistence.repository.jpa;

import br.com.s2v.solar.persistence.model.Client;
import br.com.s2v.solar.persistence.repository.ClientRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientJpaRepository extends JpaRepository<Client, Long>, ClientRepository {
}
