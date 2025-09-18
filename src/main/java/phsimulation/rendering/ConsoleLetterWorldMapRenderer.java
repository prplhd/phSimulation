package main.java.phsimulation.rendering;

import main.java.phsimulation.WorldMap;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.entities.Entity;
import main.java.phsimulation.entities.creatures.Herbivore;

import java.util.Optional;

public class ConsoleLetterWorldMapRenderer implements WorldMapRenderer {
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[0;100m";
    private static final String ANSI_DARK_MAROON_BACKGROUND = "\u001B[48;2;128;0;0m";
    private static final String ANSI_DARK_YELLOW_BACKGROUND = "\u001B[48;2;150;150;0m";
    private static final String ANSI_RESET = "\u001B[0m";

    private static final String ANSI_PREDATOR_COLOR = "\u001B[38;2;255;80;0m";
    private static final String ANSI_HERBIVORE_COLOR = "\u001B[38;2;0;255;220m";
    private static final String ANSI_GRASS_COLOR = "\u001B[38;2;0;200;0m";


    private final double healthCriticalThreshold;
    private final double healthWarningThreshold;

    public ConsoleLetterWorldMapRenderer(double healthCriticalThreshold, double healthWarningThreshold) {
        this.healthCriticalThreshold = healthCriticalThreshold;
        this.healthWarningThreshold = healthWarningThreshold;
    }

    @Override
    public void render(WorldMap worldMap) {
        for (int y = worldMap.getHeight() - 1; y >= 0 ; y--) {
            for (int x = 0; x < worldMap.getWidth(); x++) {
                Coordinate coordinate = new Coordinate(x, y);
                worldMap.ensureInBounds(coordinate);
                System.out.print(getBackgroundColor(worldMap, coordinate) + getSprite(worldMap, coordinate) + ANSI_RESET);
            }
            System.out.println();
        }
        System.out.println();
    }

    private String getBackgroundColor(WorldMap worldMap, Coordinate coordinate) {
        Entity entity = worldMap.getEntity(coordinate).orElse(null);

        if (entity instanceof Herbivore herbivore) {
            double hpRatio = (double) herbivore.getHp() / herbivore.getMaxHp();

            if (hpRatio <= healthWarningThreshold && hpRatio >= healthCriticalThreshold) {
                return ANSI_DARK_YELLOW_BACKGROUND;
            }

            if (hpRatio < healthCriticalThreshold) {
                return ANSI_DARK_MAROON_BACKGROUND;
            }
        }

        return ANSI_BLACK_BACKGROUND;
    }

    private String getSprite(WorldMap worldMap, Coordinate coordinate) {
        Optional<Entity> optional = worldMap.getEntity(coordinate);

        if (optional.isEmpty()) {
            return ConsoleLetterWorldMapRenderer.SpriteType.EMPTY.getSprite();
        }

        Entity entity = optional.get();
        String name = entity.getClass().getSimpleName().toUpperCase();
        return ConsoleLetterWorldMapRenderer.SpriteType.valueOf(name).getSprite();
    }

    private enum SpriteType {
        EMPTY(" . "),
        PREDATOR(ANSI_PREDATOR_COLOR + " P " + ANSI_RESET),
        HERBIVORE(ANSI_HERBIVORE_COLOR + " H " + ANSI_RESET),
        GRASS(ANSI_GRASS_COLOR + " G " + ANSI_RESET),
        ROCK(" R "),
        TREE(" T ");

        private final String sprite;

        SpriteType(String sprite) {
            this.sprite = sprite;
        }

        public String getSprite() {
            return sprite;
        }
    }
}
