package main.java.phsimulation.entities;

import main.java.phsimulation.entities.creatures.Herbivore;
import main.java.phsimulation.entities.creatures.Predator;
import main.java.phsimulation.entities.terrain.Grass;
import main.java.phsimulation.entities.terrain.Rock;
import main.java.phsimulation.entities.terrain.Tree;

public final class EntityFactory {
    public Entity create(EntityType type) {
        return switch (type) {
            case PREDATOR -> new Predator(2, 100, 25);
            case HERBIVORE -> new Herbivore(1, 60, 10);
            case GRASS -> new Grass();
            case ROCK -> new Rock();
            case TREE -> new Tree();
        };
    }
}
