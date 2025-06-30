package br.com.s2v.solar.persistence.repository.jpa;

import br.com.s2v.solar.persistence.model.Bill;
import br.com.s2v.solar.persistence.repository.BillRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillJpaRepository extends JpaRepository<Bill, Long>, BillRepository {
}
