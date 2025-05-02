package br.com.s2v.irradiation.api.controller;

import br.com.s2v.irradiation.core.dto.response.CityIrradiation;
import br.com.s2v.irradiation.core.service.IrradiationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/irradiation")
@AllArgsConstructor
public class IrradiationController {
    private final IrradiationService irradiationService;

    @Operation(description = "Get irradiation data by city and state", responses = {
            @ApiResponse(responseCode = "200", description = "Irradiation data retrieved successfully", content =
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CityIrradiation.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content =
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "City or state not found", content =
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/city/{cityName}/state/{stateAbbreviation}")
    public ResponseEntity<CityIrradiation> getIrradiationByCity(@PathVariable String cityName, @PathVariable String stateAbbreviation) {
        CityIrradiation cityIrradiation = irradiationService.getIrradiationByCity(cityName, stateAbbreviation);
        return ResponseEntity.ok(cityIrradiation);
    }
}
