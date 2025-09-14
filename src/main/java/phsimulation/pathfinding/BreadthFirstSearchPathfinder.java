package main.java.phsimulation.pathfinding;

import main.java.phsimulation.coordinate.AxisDirection;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.coordinate.Direction;
import main.java.phsimulation.WorldMap;
import main.java.phsimulation.entities.Entity;
import main.java.phsimulation.exceptions.InvalidCoordinateException;

import java.util.*;

public class BreadthFirstSearchPathfinder implements Pathfinder {

    public List<Coordinate> findPath(WorldMap worldMap, Coordinate start, Class<? extends Entity> target) {
        Queue<Coordinate> frontier = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();
        Map<Coordinate, Coordinate> cameFrom = new HashMap<>();

        worldMap.ensureInBounds(start);
        frontier.add(start);
        visited.add(start);

        while (!frontier.isEmpty()) {
            Coordinate currentPos = frontier.remove();
            Entity entity = worldMap.getEntity(currentPos).orElse(null);

            if (target.isInstance(entity)) {
                List<Coordinate> path = new ArrayList<>();
                while (!currentPos.equals(start)) {
                    path.add(currentPos);
                    currentPos = cameFrom.get(currentPos);
                }
                Collections.reverse(path);
                return path;
            }

            Direction direction = new AxisDirection();
            for (Coordinate shiftCoordinate : direction.get()) {
                Coordinate neighbourPos = currentPos.shift(shiftCoordinate);

                if (isAvailable(worldMap, neighbourPos, visited, target)) {
                    frontier.add(neighbourPos);
                    visited.add(neighbourPos);
                    cameFrom.put(neighbourPos, currentPos);
                }
            }
        }

        return List.of();
    }

    private boolean isAvailable(WorldMap worldMap, Coordinate neighbourPos, Set<Coordinate> visited, Class<? extends Entity> target) {
        try {
            Entity entity = worldMap.getEntity(neighbourPos).orElse(null);
            boolean isEmptyOrTarget = (entity == null) || target.isInstance(entity);
            boolean isVisited = visited.contains(neighbourPos);

            return !isVisited && isEmptyOrTarget;
        } catch (InvalidCoordinateException ignored) {
            return false;
        }
    }
}
