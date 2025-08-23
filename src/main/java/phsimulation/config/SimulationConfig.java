package main.java.phsimulation.config;

public final class SimulationConfig{
    private final int worldMapHeight;
    private final int worldMapWidth;

    private final int predatorSpeed;
    private final int predatorMaxHp;
    private final int predatorAttackPower;

    private final int herbivoreSpeed;
    private final int herbivoreMaxHp;
    private final int herbivoreHpRestoredPerGrass;

    private final int predatorCount;
    private final int herbivoreCount;
    private final int grassCount;
    private final int treeCount;
    private final int rockCount;

    private final double grassMinThreshold;
    private final double herbivoreMinThreshold;

    private SimulationConfig(Builder builder) {
        this.worldMapHeight = builder.worldMapHeight;
        this.worldMapWidth = builder.worldMapWidth;

        this.predatorSpeed = builder.predatorSpeed;
        this.predatorMaxHp = builder.predatorMaxHp;
        this.predatorAttackPower = builder.predatorAttackPower;

        this.herbivoreSpeed = builder.herbivoreSpeed;
        this.herbivoreMaxHp = builder.herbivoreMaxHp;
        this.herbivoreHpRestoredPerGrass = builder.herbivoreHpRestoredPerGrass;

        this.predatorCount = builder.predatorCount;
        this.herbivoreCount = builder.herbivoreCount;
        this.grassCount = builder.grassCount;
        this.treeCount = builder.treeCount;
        this.rockCount = builder.rockCount;

        this.grassMinThreshold = builder.grassMinThreshold;
        this.herbivoreMinThreshold = builder.herbivoreMinThreshold;
    }

    public static class Builder {
        private int worldMapHeight;
        private int worldMapWidth;

        private int predatorSpeed;
        private int predatorMaxHp;
        private int predatorAttackPower;

        private int herbivoreSpeed;
        private int herbivoreMaxHp;
        private int herbivoreHpRestoredPerGrass;

        private int predatorCount;
        private int herbivoreCount;
        private int grassCount;
        private int treeCount;
        private int rockCount;

        private double grassMinThreshold;
        private double herbivoreMinThreshold;

        public static class WorldMapBuilder {
            private final Builder builder;
            private int height;
            private int width;

            private WorldMapBuilder(Builder builder) {
                this.builder = builder;
            }

            public WorldMapBuilder height(int height) {
                this.height = height;
                return this;
            }

            public WorldMapBuilder width(int width) {
                this.width = width;
                return this;
            }

            public Builder done() {
                builder.worldMapHeight = height;
                builder.worldMapWidth = width;
                return builder;
            }
        }

        public WorldMapBuilder worldMap() {
            return new WorldMapBuilder(this);
        }

        public static class PredatorBuilder {
            private final Builder builder;
            private int speed;
            private int maxHp;
            private int attackPower;

            private PredatorBuilder(Builder builder) {
                this.builder = builder;
            }

            public PredatorBuilder speed(int speed) {
                this.speed = speed;
                return this;
            }

            public PredatorBuilder maxHp(int maxHp) {
                this.maxHp = maxHp;
                return this;
            }

            public PredatorBuilder attackPower(int attackPower) {
                this.attackPower = attackPower;
                return this;
            }

            public Builder done() {
                builder.predatorSpeed = speed;
                builder.predatorMaxHp = maxHp;
                builder.predatorAttackPower = attackPower;
                return builder;
            }
        }

        public PredatorBuilder predator() {
            return new PredatorBuilder(this);
        }

        public static class HerbivoreBuilder {
            private final Builder builder;
            private int speed;
            private int maxHp;
            private int hpRestoredPerGrass;

            private HerbivoreBuilder(Builder builder) {
                this.builder = builder;
            }

            public HerbivoreBuilder speed(int speed) {
                this.speed = speed;
                return this;
            }

            public HerbivoreBuilder maxHp(int maxHp) {
                this.maxHp = maxHp;
                return this;
            }

            public HerbivoreBuilder hpRestoredPerGrass(int hpRestoredPerGrass) {
                this.hpRestoredPerGrass = hpRestoredPerGrass;
                return this;
            }

            public Builder done() {
                builder.herbivoreSpeed = speed;
                builder.herbivoreMaxHp = maxHp;
                builder.herbivoreHpRestoredPerGrass = hpRestoredPerGrass;
                return builder;
            }
        }

        public HerbivoreBuilder herbivore() {
            return new HerbivoreBuilder(this);
        }

        public static class EntitiesCountBuilder {
            private final Builder builder;
            private int predatorCount;
            private int herbivoreCount;
            private int grassCount;
            private int treeCount;
            private int rockCount;

            private EntitiesCountBuilder(Builder builder) {
                this.builder = builder;
            }

            public EntitiesCountBuilder predatorCount(int predatorCount) {
                this.predatorCount = predatorCount;
                return this;
            }

            public EntitiesCountBuilder herbivoreCount(int herbivoreCount) {
                this.herbivoreCount = herbivoreCount;
                return this;
            }

            public EntitiesCountBuilder grassCount(int grassCount) {
                this.grassCount = grassCount;
                return this;
            }

            public EntitiesCountBuilder treeCount(int treeCount) {
                this.treeCount = treeCount;
                return this;
            }

            public EntitiesCountBuilder rockCount(int rockCount) {
                this.rockCount = rockCount;
                return this;
            }

            public Builder done() {
                builder.predatorCount = predatorCount;
                builder.herbivoreCount = herbivoreCount;
                builder.grassCount = grassCount;
                builder.treeCount = treeCount;
                builder.rockCount = rockCount;
                return builder;
            }
        }

        public EntitiesCountBuilder entitiesCount() {
            return new EntitiesCountBuilder(this);
        }

        public static class MinThresholdsBuilder {
            private final Builder builder;
            private double grassMinThreshold;
            private double herbivoreMinThreshold;

            private MinThresholdsBuilder(Builder builder) {
                this.builder = builder;
            }

            public MinThresholdsBuilder grassMinThreshold(double grassMinThreshold) {
                this.grassMinThreshold = grassMinThreshold;
                return this;
            }

            public MinThresholdsBuilder herbivoreMinThreshold(double herbivoreMinThreshold) {
                this.herbivoreMinThreshold = herbivoreMinThreshold;
                return this;
            }

            public Builder done() {
                builder.grassMinThreshold = grassMinThreshold;
                builder.herbivoreMinThreshold = herbivoreMinThreshold;
                return builder;
            }
        }

        public MinThresholdsBuilder minThresholds() {
            return new MinThresholdsBuilder(this);
        }

        public SimulationConfig build() {
            SimulationConfig cfg = new SimulationConfig(this);
            SimulationConfigValidator.validate(cfg);
            return cfg;
        }
    }

    public int getWorldMapHeight() {
        return worldMapHeight;
    }

    public int getWorldMapWidth() {
        return worldMapWidth;
    }

    public int getPredatorSpeed() {
        return predatorSpeed;
    }

    public int getPredatorMaxHp() {
        return predatorMaxHp;
    }

    public int getPredatorAttackPower() {
        return predatorAttackPower;
    }

    public int getHerbivoreSpeed() {
        return herbivoreSpeed;
    }

    public int getHerbivoreMaxHp() {
        return herbivoreMaxHp;
    }

    public int getHerbivoreHpRestoredPerGrass() {
        return herbivoreHpRestoredPerGrass;
    }

    public int getPredatorCount() {
        return predatorCount;
    }

    public int getHerbivoreCount() {
        return herbivoreCount;
    }

    public int getGrassCount() {
        return grassCount;
    }

    public int getTreeCount() {
        return treeCount;
    }

    public int getRockCount() {
        return rockCount;
    }

    public double getGrassMinThreshold() {
        return grassMinThreshold;
    }

    public double getHerbivoreMinThreshold() {
        return herbivoreMinThreshold;
    }
}
