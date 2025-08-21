package main.java.phsimulation.exceptions;

public class UnknownEntityException extends SimulationException {
    public UnknownEntityException(String entityType) {
        super("""
              
              Unknown entity type: %s
              Please add handling for this type in the switch-case statement.
              """.formatted(entityType));
    }
}
