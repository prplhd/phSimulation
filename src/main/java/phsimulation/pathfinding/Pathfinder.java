package main.java.phsimulation.pathfinding;

import main.java.phsimulation.coordinate.Coordinate;
import java.util.List;

public interface Pathfinder {
    public List<Coordinate> findPath(Coordinate start);
}
