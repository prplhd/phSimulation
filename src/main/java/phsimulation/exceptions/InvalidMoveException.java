package main.java.phsimulation.exceptions;

import main.java.phsimulation.coordinate.Coordinate;

public class InvalidMoveException extends SimulationException{
    private InvalidMoveException(String message) {
        super(message);
    }

    public static InvalidMoveException missingSourceEntity(Coordinate coordinate) {
        return new InvalidMoveException("""
                
                No expected entity found at %s for movement
                """.formatted(coordinate));
    }

    public static InvalidMoveException targetOccupied(Coordinate coordinate) {
        return new InvalidMoveException("""
                
                Target cell %s is already occupied
                """.formatted(coordinate));
    }
}
