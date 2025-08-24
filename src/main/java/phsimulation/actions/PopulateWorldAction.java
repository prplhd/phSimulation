package main.java.phsimulation.actions;

import main.java.phsimulation.WorldMap;
import main.java.phsimulation.config.SimulationConfig;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.entities.Entity;
import main.java.phsimulation.entities.EntityType;
import main.java.phsimulation.entities.factory.EntityFactory;

import java.util.*;

public final class PopulateWorldAction implements Action {
    private final SimulationConfig cfg;
    private final List<Coordinate> freeCoordinates = new ArrayList<>();
    private final EntityFactory entityFactory;

    public PopulateWorldAction(SimulationConfig cfg) {
        this.cfg = cfg;
        this.entityFactory = new EntityFactory(cfg);
    }

    @Override
    public void execute(WorldMap worldMap) {
        generateAllCoordinates(worldMap);
        Collections.shuffle(freeCoordinates);
        int index = 0;

        Map<EntityType, Integer> entitySpawnCounts = new EnumMap<>(EntityType.class);
        entitySpawnCounts.put(EntityType.PREDATOR, cfg.getPredatorCount());
        entitySpawnCounts.put(EntityType.HERBIVORE, cfg.getHerbivoreCount());
        entitySpawnCounts.put(EntityType.GRASS, cfg.getGrassCount());
        entitySpawnCounts.put(EntityType.TREE, cfg.getTreeCount());
        entitySpawnCounts.put(EntityType.ROCK, cfg.getRockCount());

        for (EntityType type : EntityType.values()) {
            for (int i = 0; i < entitySpawnCounts.get(type); i++) {
                Entity entity = entityFactory.create(type);
                worldMap.setEntity(freeCoordinates.get(index), entity);
                index++;
            }
        }

    }

    private void generateAllCoordinates(WorldMap worldMap) {
        int height = cfg.getWorldMapHeight();
        int width = cfg.getWorldMapWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinate coordinate = new Coordinate(x, y);
                worldMap.ensureInBounds(coordinate);
                freeCoordinates.add(coordinate);
            }
        }
    }
}
