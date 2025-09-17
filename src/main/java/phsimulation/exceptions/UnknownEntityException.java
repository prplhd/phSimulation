package main.java.phsimulation.exceptions;

public class UnknownEntityException extends SimulationException {
    public UnknownEntityException(String entityType) {
        super("Unknown entity type: %s.".formatted(entityType));
    }
}
