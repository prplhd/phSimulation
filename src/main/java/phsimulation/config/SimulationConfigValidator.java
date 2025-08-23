package main.java.phsimulation.config;

import main.java.phsimulation.exceptions.InvalidSimulationConfigException;

import java.util.ArrayList;
import java.util.List;

public final class SimulationConfigValidator {
    private SimulationConfigValidator() {}

    public static void validate(SimulationConfig cfg) {
        List<String> errors = new ArrayList<>();

        if (cfg.getWorldMapHeight() <=0 || cfg.getWorldMapWidth() <=0) {
            errors.add("Height and width of the map must be greater than 0");
        }

        if (cfg.getPredatorMaxHp() <= 0 || cfg.getHerbivoreMaxHp() <= 0) {
            errors.add("Max HP must be greater than 0");
        }

        if (cfg.getPredatorSpeed() <= 0 || cfg.getHerbivoreSpeed() <= 0) {
            errors.add("Speed must be greater than 0");
        }

        if (cfg.getPredatorAttackPower() <= 0) {
            errors.add("AttackPower must be greater than 0");
        }

        if (cfg.getHerbivoreHpRestoredPerGrass() <= 0) {
            errors.add("HP restored per grass must be greater than 0");
        }

        if (cfg.getPredatorCount() < 0 || cfg.getHerbivoreCount() < 0 || cfg.getGrassCount() < 0 || cfg.getTreeCount() < 0 || cfg.getRockCount() < 0) {
            errors.add("Entity counts must be greater than or equal to 0");
        }

        if (cfg.getGrassMinThreshold() < 0.0 || cfg.getGrassMinThreshold() > 1.0 || cfg.getHerbivoreMinThreshold() < 0 || cfg.getHerbivoreMinThreshold() > 1.0) {
            errors.add("Thresholds must be in [0.0, 1.0]");
        }

        int area = cfg.getWorldMapHeight() * cfg.getWorldMapWidth();
        int entitiesCount = cfg.getPredatorCount() + cfg.getHerbivoreCount() + cfg.getGrassCount() + cfg.getTreeCount() + cfg.getRockCount();

        if (area < entitiesCount) {
            errors.add("The count of creatures (%d) exceeds the capacity of the map (%d).".formatted(entitiesCount, area));
        }

        if(!errors.isEmpty()) {
            throw new InvalidSimulationConfigException(errors);
        }

    }
}
