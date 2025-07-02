package br.com.s2v.solar.api.controller;

import br.com.s2v.solar.core.dto.request.FinancialCalculationRequest;
import br.com.s2v.solar.core.dto.response.FinancialCalculationResponse;
import br.com.s2v.solar.core.service.FinancialCalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financial-calculation")
@AllArgsConstructor
public class FinancialCalculationController {

    private final FinancialCalculationService financialCalculationService;

    @PostMapping()
    @Operation(description = "Calcula os valores financeiros com base nos dados fornecidos",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cálculo realizado com sucesso",
                            content = @Content(schema = @Schema(implementation = FinancialCalculationResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<FinancialCalculationResponse> calculate(@RequestBody FinancialCalculationRequest request) {
        return financialCalculationService.calculate(request);
    }
}
