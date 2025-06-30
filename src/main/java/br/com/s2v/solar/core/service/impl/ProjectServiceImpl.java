package br.com.s2v.solar.core.service.impl;

import br.com.s2v.irradiation.core.service.IrradiationService;
import br.com.s2v.solar.core.dto.request.ProjectRequest;
import br.com.s2v.solar.core.dto.response.ProjectResponse;
import br.com.s2v.solar.core.service.ProjectService;
import br.com.s2v.solar.core.service.SizingService;
import br.com.s2v.solar.persistence.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final SizingService sizingService;
    private final IrradiationService irradiationService;
    private final ProjectRepository projectRepository;

    @Override
    public ResponseEntity<ProjectResponse> createProject(ProjectRequest project) {
        var projectResponse = new ProjectResponse();

        if (project.getBills() == null) {
            log.error("Bad Request: Invalid project data");
            return ResponseEntity.badRequest().build();
        }

        if (project.getLabor() == null || BigDecimal.ZERO.compareTo(project.getLabor()) == 0) {
            project.setLabor(sizingService.calculateLabor(project.getModulesNumber()));
        }

        var systemPowerInKwp = sizingService.calculateSystemPower(
                project.getModulesNumber(),
                project.getModulesPower()
        );
        if (systemPowerInKwp == null || BigDecimal.ZERO.compareTo(systemPowerInKwp) == 0) {
            log.error("Bad Request: System power in Wp is zero for project");
            return ResponseEntity.badRequest().build();
        }

        var powerInWp = systemPowerInKwp.multiply(BigDecimal.valueOf(1000));
        var fixedCosts = sizingService.calculateFixedCosts(
                project.getKitValue(),
                project.getLabor(),
                project.getPowerInput(),
                project.getMaterialAc(),
                project.getTravel(),
                project.getExtraCosts()
        );
        var pricePerWp = sizingService.calculatePricePerWp(
                powerInWp,
                fixedCosts,
                project.getKitValue(),
                project.getCommission(),
                project.getTaxRate(),
                project.getProfitMargin()
        );
        var salePrice = pricePerWp.multiply(powerInWp);
        var commissionValue = sizingService.calculateCommission(project.getCommission(), salePrice);
        var totalTax = sizingService.calculateTotalTax(project.getTaxRate(), salePrice, project.getKitValue());
        var totalCosts = sizingService.calculateTotalCosts(fixedCosts, commissionValue, totalTax);
        var margin = sizingService.calculateMargin(salePrice, totalCosts);

        projectResponse.setPhotovoltaicPower(systemPowerInKwp);
        projectResponse.setPricePerWp(pricePerWp);
        projectResponse.setSalePrice(salePrice);
        projectResponse.setCommissionValue(commissionValue);
        projectResponse.setTotalTax(totalTax);
        projectResponse.setTotalCost(totalCosts);
        projectResponse.setMargin(margin);

        return ResponseEntity.ok(projectResponse);
    }
}
