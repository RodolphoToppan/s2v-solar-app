package br.com.s2v.solar.api.controller;

import br.com.s2v.solar.core.dto.request.TariffRequest;
import br.com.s2v.solar.core.service.TariffService;
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

import java.math.BigDecimal;

@RestController
@RequestMapping("/tariff")
@AllArgsConstructor
public class TariffController {
    private final TariffService tariffService;

    @PostMapping()
    @Operation(
            description = "Cria uma tarifa com os dados fornecidos",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tarifa criada com sucesso",
                            content = @Content(schema = @Schema(implementation = BigDecimal.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    public ResponseEntity<BigDecimal> calculateTariff(@RequestBody TariffRequest tariff) {
        return tariffService.calculate(tariff);
    }
}
