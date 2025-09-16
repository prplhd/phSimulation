package main.java.phsimulation.entities.creatures;

import main.java.phsimulation.WorldMap;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.coordinate.Direction;
import main.java.phsimulation.coordinate.EightWayDirection;
import main.java.phsimulation.entities.Entity;

public final  class Predator extends Creature {
    private static final Direction INTERACTION_DIRECTION = new EightWayDirection();
    private final int attackPower;

    public Predator(int speed, int maxHp, int attackPower) {
        super(speed, maxHp, Herbivore.class, INTERACTION_DIRECTION);
        this.attackPower = attackPower;
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
