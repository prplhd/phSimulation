package main.java.phsimulation.coordinate;

public record Coordinate(int x, int y) {
    public Coordinate shift(Coordinate other) {
        return new Coordinate(x + other.x(), y + other.y());
    }
}
