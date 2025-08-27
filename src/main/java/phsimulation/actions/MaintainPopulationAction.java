package main.java.phsimulation.actions;

import main.java.phsimulation.WorldMap;
import main.java.phsimulation.config.SimulationConfig;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.entities.Entity;
import main.java.phsimulation.entities.EntityType;
import main.java.phsimulation.entities.creatures.Herbivore;
import main.java.phsimulation.entities.factory.EntityFactory;
import main.java.phsimulation.entities.terrain.Grass;

import java.util.*;

public final class MaintainPopulationAction implements Action{
    private final SimulationConfig cfg;
    private final EntityFactory entityFactory;

    public MaintainPopulationAction(SimulationConfig cfg) {
        this.cfg = cfg;
        this.entityFactory = new EntityFactory(cfg);
    }

    @Override
    public void execute(WorldMap worldMap) {
        int currentHerbivoreCount = 0;
        int currentGrassCount = 0;

        for (Entity entity : worldMap.getEntitiesCopy().values()) {
            if (entity instanceof Herbivore) {
                currentHerbivoreCount++;
            }
            if (entity instanceof Grass) {
                currentGrassCount++;
            }
        }

        if (currentHerbivoreCount < cfg.getHerbivoreMinCount()) {
            List<Coordinate> freeCoordinates = generateFreeCoordinates(worldMap);
            Collections.shuffle(freeCoordinates);

            int neededHerbivoreCount = cfg.getHerbivoreCount() - currentHerbivoreCount;
            for (int i = 0; i < neededHerbivoreCount; i++) {
                Entity entity = entityFactory.create(EntityType.HERBIVORE);
                worldMap.setEntity(freeCoordinates.get(i), entity);
            }
        }

        if (currentGrassCount < cfg.getGrassMinCount()) {
            List<Coordinate> freeCoordinates = generateFreeCoordinates(worldMap);
            Collections.shuffle(freeCoordinates);

            int neededGrassCount = cfg.getGrassCount() - currentGrassCount;
            for (int i = 0; i < neededGrassCount; i++) {
                Entity entity = entityFactory.create(EntityType.GRASS);
                worldMap.setEntity(freeCoordinates.get(i), entity);
            }
        }
    }

    private List<Coordinate> generateFreeCoordinates(WorldMap worldMap) {
        int height = worldMap.getHeight();
        int width = worldMap.getWidth();

        List<Coordinate> freeCoordinates = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinate coordinate = new Coordinate(x, y);
                worldMap.ensureInBounds(coordinate);

                if(worldMap.getEntity(coordinate).isEmpty()) {
                    freeCoordinates.add(coordinate);
                }
            }
        }
        return freeCoordinates;
    }
}
