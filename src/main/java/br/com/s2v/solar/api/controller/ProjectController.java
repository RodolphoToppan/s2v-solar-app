package br.com.s2v.solar.api.controller;

import br.com.s2v.solar.core.dto.request.ProjectRequest;
import br.com.s2v.solar.core.dto.response.ProjectResponse;
import br.com.s2v.solar.core.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping()
    @Operation(description = "Cria um projeto com os dados fornecidos", responses = {
            @ApiResponse(responseCode = "201", description = "Projeto criado com sucesso",
                    content = @Content(schema = @Schema(implementation = ProjectResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest project) {
        return projectService.createProject(project);
    }

    //    @GetMapping()
    //    public ResponseEntity<List<Project>> getProjectList() {
    //        return projectService.getAll();
    //    }
}
