package main.java.phsimulation.exceptions;

import main.java.phsimulation.coordinate.Coordinate;

public class InvalidCoordinateException extends SimulationException {
    public InvalidCoordinateException(Coordinate coordinate, int width, int height) {
        super("Invalid coordinate received: %s. Please ensure x is within [0..%d] and y is within [0..%d]."
                .formatted(coordinate, width - 1, height - 1));
    }
}
