package br.com.s2v.solar.core.enums;

import java.math.BigDecimal;

public enum PowerSupplyType {
    MONOFASICA(BigDecimal.valueOf(30)),
    BIFASICA(BigDecimal.valueOf(50)),
    TRIFASICA(BigDecimal.valueOf(100));

    private final BigDecimal availability;

    PowerSupplyType(BigDecimal availability) {
        this.availability = availability;
    }

    public BigDecimal getAvailability() {
        return availability;
    }
}
