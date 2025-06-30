package br.com.s2v.solar.core.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    private String clientName;
    private String clientState;
    private String clientCity;
    private String clientAddress;
    private List<BillRequest> bills;
    private BigDecimal modulesNumber;
    private BigDecimal modulesPower;
    private BigDecimal labor;
    private BigDecimal kitValue;
    private BigDecimal powerInput;
    private BigDecimal taxRate;
    private BigDecimal materialAc;
    private BigDecimal travel;
    private BigDecimal commission;
    private BigDecimal extraCosts;
    private BigDecimal profitMargin;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BillRequest {
        private Long uc;
        private String ucType; //TODO: enum GERADORA ou BENEFICIÁRIA
        private String supplyType; //TODO: enum MONOFÁSICO, BIFÁSICO ou TRIFÁSICO
        private BigDecimal tariff;
        private BigDecimal nighttimeConsumption;
        private BigDecimal publicLighting;
        private ConsumptionRequest detailedConsumption;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ConsumptionRequest {
        private BigDecimal jan;
        private BigDecimal feb;
        private BigDecimal mar;
        private BigDecimal apr;
        private BigDecimal may;
        private BigDecimal jun;
        private BigDecimal jul;
        private BigDecimal aug;
        private BigDecimal sep;
        private BigDecimal oct;
        private BigDecimal nov;
        private BigDecimal dec;
    }
}
