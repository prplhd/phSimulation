package main.java.phsimulation.entities.creatures;

import main.java.phsimulation.coordinate.AxisDirection;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.WorldMap;
import main.java.phsimulation.coordinate.Direction;
import main.java.phsimulation.entities.Entity;
import main.java.phsimulation.exceptions.EntityNotOnMapException;
import main.java.phsimulation.exceptions.InvalidMoveException;
import main.java.phsimulation.pathfinding.Pathfinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class Creature extends Entity {
    protected final int speed;
    protected final int maxHp;
    protected int hp;
    protected final Class<? extends Entity> target;
    protected final Direction interactionDirection;

    protected Creature(int speed, int maxHp, Class<? extends Entity> target, Direction interactionDirection) {
        this.speed = speed;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.target = target;
        this.interactionDirection = interactionDirection;
    }

    public void makeMove(WorldMap worldMap, Pathfinder pathfinder) {
        Coordinate currentPos = worldMap.getCoordinate(this)
                .orElseThrow(() -> new EntityNotOnMapException(this));

        Optional<Coordinate> targetNearby = findTargetNearby(worldMap, currentPos);
        if (targetNearby.isPresent()) {
            interactWithTarget(worldMap, currentPos, targetNearby.get());
            return;
        }

        moveToTarget(worldMap, currentPos, pathfinder);
    }

    protected void moveToTarget(WorldMap worldMap, Coordinate currentPos, Pathfinder pathfinder) {
        List<Coordinate> path = pathfinder.findPath(worldMap, currentPos, target);

        if (path.isEmpty()) {
            moveRandomly(worldMap, currentPos);
            return;
        }

        Coordinate newPos;
        if(path.size() <= speed) {
            /* Если значение скорости больше значения размера пути, то существо
               занимает клетку, находящуюся перед своей целью
             */
            newPos = path.get((path.size() - 1) - 1);
            moveTo(worldMap, currentPos, newPos);
            return;
        }

        newPos = path.get(speed - 1);
        moveTo(worldMap, currentPos, newPos);
    }

    protected void moveTo(WorldMap worldMap, Coordinate currentPos, Coordinate newPos) {
        Entity currentPosEntity = worldMap.getEntity(currentPos).orElse(null);
        if (currentPosEntity != this) {
            throw InvalidMoveException.missingSourceEntity(currentPos);
        }

        Entity newPosEntity = worldMap.getEntity(newPos).orElse(null);
        if (newPosEntity != null) {
            throw InvalidMoveException.targetOccupied(newPos);
        }

        worldMap.removeEntity(currentPos);
        worldMap.setEntity(newPos, this);
    }

    protected void moveRandomly(WorldMap worldMap, Coordinate currentPos) {
        List<Coordinate> availableCoordinates = new ArrayList<>();
        Direction direction = new AxisDirection();
        for (Coordinate shiftCoordinate : direction.get()) {
            Coordinate neighbourPos = currentPos.shift(shiftCoordinate);
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
        moveTo(worldMap, currentPos, availableCoordinates.get(randomIndex));
    }

    protected Optional<Coordinate> findTargetNearby(WorldMap worldMap, Coordinate currentPos) {
        for (Coordinate shiftCoordinate : interactionDirection.get()) {
            Coordinate neighbourPos = currentPos.shift(shiftCoordinate);

            if (!worldMap.isInBounds(neighbourPos)) {
                continue;
            }

            Entity entity = worldMap.getEntity(neighbourPos).orElse(null);
            if (target.isInstance(entity)) {
                return Optional.of(neighbourPos);
            }
        }

        return Optional.empty();
    }

    protected abstract void interactWithTarget(WorldMap worldMap, Coordinate currentPos, Coordinate targetPos);
}
