package main.java.phsimulation.entities.creatures;

import main.java.phsimulation.WorldMap;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.entities.terrain.Grass;

public class Herbivore extends Creature {
    private final int hpRestoredPerGrass;

    public Herbivore(int speed, int maxHp, int hpRestoredPerGrass) {
        super(speed, maxHp, Grass.class);
        this.hpRestoredPerGrass = hpRestoredPerGrass;
    }

    @Override
    protected void interactWithTarget(WorldMap worldMap, Coordinate currentPos, Coordinate targetPos) {
        worldMap.removeEntity(targetPos);
        hp += hpRestoredPerGrass;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public int getHp() {
        return hp;
    }
}
