package br.com.s2v.solar.persistence.repository.jpa;

import br.com.s2v.solar.persistence.model.Project;
import br.com.s2v.solar.persistence.repository.ProjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<Project, Long>, ProjectRepository {
}
