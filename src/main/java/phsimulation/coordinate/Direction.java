package main.java.phsimulation.coordinate;

public interface Direction {
    int dx();
    int dy();

    default Coordinate shift(Coordinate coordinate) {
        return new Coordinate(coordinate.x() + dx(), coordinate.y() + dy());
    }
}
