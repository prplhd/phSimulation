package main.java.phsimulation.exceptions;

public class UnknownWorldSizeException extends SimulationException {
    public UnknownWorldSizeException(int size) {
        super("Unknown world size key: %d.".formatted(size));
    }
}
