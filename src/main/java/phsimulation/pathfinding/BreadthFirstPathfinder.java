package main.java.phsimulation.pathfinding;

import main.java.phsimulation.coordinate.AxisDirection;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.coordinate.Direction;
import main.java.phsimulation.WorldMap;
import main.java.phsimulation.entities.Entity;
import main.java.phsimulation.exceptions.InvalidCoordinateException;

import java.util.*;

public class BreadthFirstPathfinder implements Pathfinder {

    private final WorldMap worldMap;
    private final Class<? extends Entity> target;

    public BreadthFirstPathfinder(WorldMap worldMap, Class<? extends Entity> target) {
        this.worldMap = worldMap;
        this.target = target;
    }

    public List<Coordinate> findPath(Coordinate start) {
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

            for (Direction direction : AxisDirection.values()) {
                Coordinate neighbourPos = direction.apply(currentPos);

                if (isAvailable(neighbourPos, visited)) {
                    frontier.add(neighbourPos);
                    visited.add(neighbourPos);
                    cameFrom.put(neighbourPos, currentPos);
                }
            }
        }

        return List.of();
    }

    private boolean isAvailable(Coordinate neighbourPos, Set<Coordinate> visited) {
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
