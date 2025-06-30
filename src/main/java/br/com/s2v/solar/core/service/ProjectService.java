package br.com.s2v.solar.core.service;

import br.com.s2v.solar.core.dto.request.ProjectRequest;
import br.com.s2v.solar.core.dto.response.ProjectResponse;
import org.springframework.http.ResponseEntity;

public interface ProjectService {

    ResponseEntity<ProjectResponse> createProject(ProjectRequest createProject);

    //    ResponseEntity<Project> getById(Long projectId);
    //
    //    ResponseEntity<List<Project>> getAll();
    //
    //    ResponseEntity<Void> delete(Long projectId);
    //
    //    ResponseEntity<Project> update(Long projectId, Project project);
}
