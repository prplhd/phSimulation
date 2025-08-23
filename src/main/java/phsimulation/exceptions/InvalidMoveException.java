package main.java.phsimulation.exceptions;

public class InvalidMoveException extends SimulationException{
    public InvalidMoveException(String message) {
        super("\n" + message);
    }
}
