package main.java.phsimulation.coordinate;

import java.util.List;

public class AxisDirection implements Direction {
    private static final List<Coordinate> SHIFT_COORDINATES = List.of(
            new Coordinate(0, 1),
            new Coordinate(1, 0),
            new Coordinate(0, -1),
            new Coordinate(-1, 0)
    );

    @Override
    public List<Coordinate> get() {
        return SHIFT_COORDINATES;
    }
}
