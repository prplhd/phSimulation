package main.java.phsimulation.coordinate;

public interface Direction {
    int dx();
    int dy();

    default Coordinate apply(Coordinate coordinate) {
        return new Coordinate(coordinate.x() + dx(), coordinate.y() + dy());
    }
}
