package main.java.phsimulation.pathfinding;

import main.java.phsimulation.WorldMap;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.entities.Entity;

import java.util.List;

public interface Pathfinder {
    List<Coordinate> findPath(WorldMap worldMap, Coordinate start, Class<? extends Entity> target);
}
