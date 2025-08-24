package main.java.phsimulation.entities.factory;

import main.java.phsimulation.config.SimulationConfig;
import main.java.phsimulation.entities.Entity;
import main.java.phsimulation.entities.EntityType;
import main.java.phsimulation.entities.creatures.Herbivore;
import main.java.phsimulation.entities.creatures.Predator;
import main.java.phsimulation.entities.terrain.Grass;
import main.java.phsimulation.entities.terrain.Rock;
import main.java.phsimulation.entities.terrain.Tree;

import javax.lang.model.UnknownEntityException;

public final class EntityFactory {
    private SimulationConfig cfg;

    public EntityFactory(SimulationConfig cfg) {
        this.cfg = cfg;
    }

    public Entity create(EntityType type) {
        return switch (type) {
            case PREDATOR -> new Predator(cfg.getPredatorSpeed(), cfg.getPredatorMaxHp(), cfg.getPredatorAttackPower());
            case HERBIVORE -> new Herbivore(cfg.getHerbivoreSpeed(), cfg.getHerbivoreMaxHp(), cfg.getHerbivoreHpRestoredPerGrass());
            case GRASS -> new Grass();
            case ROCK -> new Rock();
            case TREE -> new Tree();
        };
    }
}
