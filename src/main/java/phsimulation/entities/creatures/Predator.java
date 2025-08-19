package main.java.phsimulation.entities.creatures;

import main.java.phsimulation.WorldMap;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.coordinate.DiagonalDirection;
import main.java.phsimulation.coordinate.Direction;
import main.java.phsimulation.entities.Entity;

import java.util.Optional;

public final  class Predator extends Creature {
    private final int attackPower;

    public Predator(int speed, int maxHp, int attackPower) {
        super(speed, maxHp, Herbivore.class);
        this.attackPower = attackPower;
    }

    @Override
    protected Optional<Coordinate> findTargetNearby(WorldMap worldMap, Coordinate currentPos) {
        Optional<Coordinate> result = super.findTargetNearby(worldMap, currentPos);
        if (result.isPresent()) {
            return result;
        }

        for (Direction direction : DiagonalDirection.values()) {
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
    }

    @Override
    protected void interactWithTarget(WorldMap worldMap, Coordinate currentPos, Coordinate targetPos) {
        Entity entity = worldMap.getEntity(targetPos).orElse(null);

        if (entity instanceof Herbivore herbivore) {
            herbivore.takeDamage(attackPower);
            if(herbivore.getHp() <= 0) {
                worldMap.removeEntity(targetPos);
            }

            hp += attackPower;
            if (hp > maxHp) {
                hp = maxHp;
            }
        }
    }
}
