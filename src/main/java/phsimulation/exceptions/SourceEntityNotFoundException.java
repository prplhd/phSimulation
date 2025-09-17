package main.java.phsimulation.exceptions;

import main.java.phsimulation.coordinate.Coordinate;

public class SourceEntityNotFoundException extends SimulationException {
    public SourceEntityNotFoundException(Coordinate coordinate) {
        super("No expected entity found at %s for movement".formatted(coordinate));
    }
}
