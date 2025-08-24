package main.java.phsimulation.config;

public final class SimulationPreset {
    private SimulationPreset(){}

    public static SimulationConfig getForSize(WorldSize size) {
        return switch (size){
            case SMALL -> new SimulationConfig.Builder()
                    .worldMap()
                        .height(10)
                        .width(10)
                        .done()
                    .predator()
                        .speed(2)
                        .maxHp(100)
                        .attackPower(25)
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
                    .build();

            case MEDIUM -> new SimulationConfig.Builder()
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
                    .build();

            case LARGE -> new SimulationConfig.Builder()
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
                    .build();
        };
    }

    public enum WorldSize {
        SMALL, MEDIUM, LARGE;
    }
}
