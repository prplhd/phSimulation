package main.java.phsimulation.config;

import main.java.phsimulation.exceptions.UnknownWorldSizeException;
import main.java.phsimulation.pathfinding.BreadthFirstSearchPathfinder;
import main.java.phsimulation.rendering.ConsoleEmojiWorldMapRenderer;
import main.java.phsimulation.rendering.ConsoleLetterWorldMapRenderer;

public final class SimulationFactory {
    public static final int SMALL_WORLD_KEY = 1;
    public static final int MEDIUM_WORLD_KEY = 2;
    public static final int LARGE_WORLD_KEY = 3;

    private SimulationFactory(){}

    public static SimulationConfig getForSize(int size) {
        return switch (size) {
            case SMALL_WORLD_KEY -> createSmallSimulationConfig();
            case MEDIUM_WORLD_KEY -> createMediumSimulationConfig();
            case LARGE_WORLD_KEY -> createLargeSimulationConfig();
            default -> throw new UnknownWorldSizeException(size);
        };
    }

    private static SimulationConfig createSmallSimulationConfig() {
        return new SimulationConfig.Builder()
                .worldMap()
                    .height(10)
                    .width(10)
                    .done()
                .predator()
                    .speed(2)
                    .maxHp(100)
                    .attackPower(22)
                    .done()
                .herbivore()
                    .speed(1)
                    .maxHp(60)
                    .hpRestoredPerGrass(20)
                    .done()
                .entitiesCount()
                    .predatorCount(1)
                    .herbivoreCount(3)
                    .grassCount(10)
                    .treeCount(6)
                    .rockCount(6)
                    .done()
                .minCounts()
                    .grassMinCount(3)
                    .herbivoreMinCount(1)
                    .done()
                .pathfinder(new BreadthFirstSearchPathfinder())
                .worldMapRenderer(new ConsoleLetterWorldMapRenderer(0.33, 0.66))
                .build();
    }

    private static SimulationConfig createMediumSimulationConfig() {
        return new SimulationConfig.Builder()
                .worldMap()
                    .height(15)
                    .width(15)
                    .done()
                .predator()
                    .speed(2)
                    .maxHp(100)
                    .attackPower(25)
                    .done()
                .herbivore()
                    .speed(1)
                    .maxHp(60)
                    .hpRestoredPerGrass(10)
                    .done()
                .entitiesCount()
                    .predatorCount(2)
                    .herbivoreCount(6)
                    .grassCount(20)
                    .treeCount(12)
                    .rockCount(12)
                    .done()
                .minCounts()
                    .grassMinCount(6)
                    .herbivoreMinCount(2)
                    .done()
                .pathfinder(new BreadthFirstSearchPathfinder())
                .worldMapRenderer(new ConsoleEmojiWorldMapRenderer(0.33, 0.66))
                .build();
    }

    private static SimulationConfig createLargeSimulationConfig() {
        return new SimulationConfig.Builder()
                .worldMap()
                    .height(18)
                    .width(18)
                    .done()
                .predator()
                    .speed(2)
                    .maxHp(100)
                    .attackPower(25)
                    .done()
                .herbivore()
                    .speed(1)
                    .maxHp(60)
                    .hpRestoredPerGrass(10)
                    .done()
                .entitiesCount()
                    .predatorCount(3)
                    .herbivoreCount(9)
                    .grassCount(30)
                    .treeCount(18)
                    .rockCount(18)
                    .done()
                .minCounts()
                    .grassMinCount(9)
                    .herbivoreMinCount(3)
                    .done()
                .pathfinder(new BreadthFirstSearchPathfinder())
                .worldMapRenderer(new ConsoleEmojiWorldMapRenderer(0.33, 0.66))
                .build();
    }
}
