package main.java.phsimulation.exceptions;

import main.java.phsimulation.entities.Entity;

public class UnknownEntityException extends SimulationException {
    public UnknownEntityException(Entity entity) {
        super("""
              
              Unknown entity type: %s
              Please add handling for this type in the switch-case statement.
              """.formatted(entity.getClass().getName()));
    }
}
