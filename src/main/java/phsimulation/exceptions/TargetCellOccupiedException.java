package main.java.phsimulation.exceptions;

import main.java.phsimulation.coordinate.Coordinate;

public class TargetCellOccupiedException extends SimulationException {
    public TargetCellOccupiedException(Coordinate coordinate) {
        super("Target %s is already occupied".formatted(coordinate));
    }
}
