package main.java.phsimulation.entities.creatures;

import main.java.phsimulation.WorldMap;
import main.java.phsimulation.coordinate.AxisDirection;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.coordinate.Direction;
import main.java.phsimulation.entities.terrain.Grass;

public final class Herbivore extends Creature {
    private static final Direction INTERACTION_DIRECTION = new AxisDirection();

    public Herbivore(int speed, int maxHp, int hpRestoredPerInteraction) {
        super(speed, maxHp, hpRestoredPerInteraction, Grass.class, INTERACTION_DIRECTION);
    }

    @Override
    protected void interactWithTarget(WorldMap worldMap, Coordinate targetPos) {
        worldMap.removeEntity(targetPos);
        heal();
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }
}
