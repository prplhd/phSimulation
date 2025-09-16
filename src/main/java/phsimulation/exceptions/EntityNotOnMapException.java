package main.java.phsimulation.exceptions;

import main.java.phsimulation.entities.Entity;

public class EntityNotOnMapException extends SimulationException {
    public EntityNotOnMapException(Entity entity) {
        super("Entity %s is not on the map"
                .formatted(entity.getClass().getSimpleName()));
    }
}
