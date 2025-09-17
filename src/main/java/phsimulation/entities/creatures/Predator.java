package main.java.phsimulation.entities.creatures;

import main.java.phsimulation.WorldMap;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.coordinate.Direction;
import main.java.phsimulation.coordinate.EightWayDirection;
import main.java.phsimulation.entities.Entity;

public final class Predator extends Creature {
    private static final Direction INTERACTION_DIRECTION = new EightWayDirection();
    private final int attackDamage;

    public Predator(int speed, int maxHp, int attackDamage) {
        super(speed, maxHp, attackDamage, Herbivore.class, INTERACTION_DIRECTION);
        this.attackDamage = attackDamage;
    }

    @Override
    protected void interactWithTarget(WorldMap worldMap, Coordinate targetPos) {
        Entity entity = worldMap.getEntity(targetPos).orElse(null);

        if (entity instanceof Herbivore herbivore) {
            herbivore.takeDamage(attackDamage);
            if(herbivore.getHp() <= 0) {
                worldMap.removeEntity(targetPos);
            }

        heal();
        }
    }
}
