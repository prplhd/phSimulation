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
    private final List<Coordinate> freeCoordinates = new ArrayList<>();
    private final EntityFactory entityFactory;

    public MaintainPopulationAction(SimulationConfig cfg) {
        this.cfg = cfg;
        this.entityFactory = new EntityFactory(cfg);
    }

    @Override
    public void execute(WorldMap worldMap) {
        int currentHerbivoreCount = 0;
        int currentGrassCount = 0;

        for (Coordinate coordinate : worldMap.getEntitiesCopy().keySet()) {
            Entity entity = worldMap.getEntity(coordinate).orElse(null);

            if (entity != null) {
                if (entity instanceof Herbivore) {
                    currentHerbivoreCount++;
                }
                if (entity instanceof Grass) {
                    currentGrassCount++;
                }
            }
        }

        if (currentHerbivoreCount < cfg.getHerbivoreMinCount()) {
            generateFreeCoordinates(worldMap);
            Collections.shuffle(freeCoordinates);

            int herbivoreCount = cfg.getHerbivoreCount();
            for (int i = 0; i < herbivoreCount; i++) {
                Entity entity = entityFactory.create(EntityType.HERBIVORE);
                worldMap.setEntity(freeCoordinates.get(i), entity);
            }
        }

        if (currentGrassCount < cfg.getGrassMinCount()) {
            generateFreeCoordinates(worldMap);
            Collections.shuffle(freeCoordinates);

            int grassCount = cfg.getGrassCount();
            for (int i = 0; i < grassCount; i++) {
                Entity entity = entityFactory.create(EntityType.GRASS);
                worldMap.setEntity(freeCoordinates.get(i), entity);
            }
        }
    }

    private void generateFreeCoordinates(WorldMap worldMap) {
        int height = cfg.getWorldMapHeight();
        int width = cfg.getWorldMapWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinate coordinate = new Coordinate(x, y);
                worldMap.ensureInBounds(coordinate);

                if(worldMap.getEntity(coordinate).isEmpty()) {
                    freeCoordinates.add(new Coordinate(x, y));
                }
            }
        }
    }
}
