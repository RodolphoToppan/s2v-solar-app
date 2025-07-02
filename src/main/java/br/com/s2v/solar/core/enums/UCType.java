package br.com.s2v.solar.core.enums;

public enum UCType {
    GERADORA(false),
    BENEFICIARIA(true);

    private final boolean fullNightConsumption;

    UCType(boolean fullNightConsumption) {
        this.fullNightConsumption = fullNightConsumption;
    }

    public boolean isFullNightConsumption() {
        return fullNightConsumption;
    }
}
