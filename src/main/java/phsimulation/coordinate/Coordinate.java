package main.java.phsimulation.coordinate;

public record Coordinate(int x, int y) {
    @Override
    public String toString() {
        return "[x=%d, y=%d]".formatted(x, y);
    }
}
