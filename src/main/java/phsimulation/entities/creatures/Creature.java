package main.java.phsimulation.entities.creatures;

import main.java.phsimulation.coordinate.AxisDirection;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.WorldMap;
import main.java.phsimulation.coordinate.Direction;
import main.java.phsimulation.entities.Entity;
import main.java.phsimulation.pathfinding.BreadthFirstPathfinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class Creature extends Entity {
    protected final int speed;
    protected final int maxHp;
    protected int hp;
    protected final Class<? extends Entity> target;

    protected Creature(int speed, int maxHp, Class<? extends Entity> target) {
        this.speed = speed;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.target = target;
    }

    public void makeMove(WorldMap worldMap, Coordinate currentPos) {
        Optional<Coordinate> targetNearby = findTargetNearby(worldMap, currentPos);
        if (targetNearby.isPresent()) {
            interactWithTarget(worldMap, currentPos, targetNearby.get());
            return;
        }

        moveToTarget(worldMap, currentPos);
    }

    protected void moveToTarget(WorldMap worldMap, Coordinate currentPos) {
        BreadthFirstPathfinder breadthFirstPathfinder = new BreadthFirstPathfinder(worldMap, target);
        List<Coordinate> path = breadthFirstPathfinder.findPath(currentPos);

        if (path.isEmpty()) {
            moveRandomly(worldMap, currentPos);
            return;
        }

        Coordinate newPos;
        if(path.size() <= speed) {
            newPos = path.get(path.size() - 1 - 1);
            worldMap.moveEntity(currentPos, newPos, this);
            return;
        }

        newPos = path.get(speed - 1);
        worldMap.moveEntity(currentPos, newPos, this);
    }

    protected void moveRandomly(WorldMap worldMap, Coordinate currentPos) {
        List<Coordinate> availableCoordinates = new ArrayList<>();
        for (Direction direction: AxisDirection.values()) {
            Coordinate neighbourPos = direction.apply(currentPos);
            if (!worldMap.isInBounds(neighbourPos)) {
                continue;
            }

            Entity entity = worldMap.getEntity(neighbourPos).orElse(null);
            if (entity == null) {
                availableCoordinates.add(neighbourPos);
            }
        }

        if (availableCoordinates.isEmpty()) {
            return;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(availableCoordinates.size());
        worldMap.moveEntity(currentPos, availableCoordinates.get(randomIndex), this);
    }

    protected Optional<Coordinate> findTargetNearby(WorldMap worldMap, Coordinate currentPos) {
        for (Direction direction :  AxisDirection.values()) {
            Coordinate neighbourPos = direction.apply(currentPos);

            if (!worldMap.isInBounds(neighbourPos)) {
                continue;
            }

            Entity entity = worldMap.getEntity(neighbourPos).orElse(null);
            if (target.isInstance(entity)) {
                return Optional.of(neighbourPos);
            }
        }

        return Optional.empty();
    };

    protected abstract void interactWithTarget(WorldMap worldMap, Coordinate currentPos, Coordinate targetPos);
}
