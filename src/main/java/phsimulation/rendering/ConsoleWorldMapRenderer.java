package main.java.phsimulation.rendering;

import main.java.phsimulation.entities.creatures.Herbivore;
import main.java.phsimulation.exceptions.UnknownEntityException;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.WorldMap;
import main.java.phsimulation.entities.*;

import java.util.Optional;


public class ConsoleWorldMapRenderer implements WorldMapRenderer {
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[0;100m";
    private static final String ANSI_DARK_MAROON_BACKGROUND = "\u001B[48;2;128;0;0m";
    private static final String ANSI_DARK_YELLOW_BACKGROUND = "\u001B[48;2;150;150;0m";
    private static final String ANSI_RESET = "\u001B[0m";

    private static final double HEALTH_CRITICAL_THRESHOLD = 0.33;
    private static final double HEALTH_WARNING_THRESHOLD = 0.66;

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

            if (hpRatio <= HEALTH_WARNING_THRESHOLD && hpRatio >= HEALTH_CRITICAL_THRESHOLD) {
                return ANSI_DARK_YELLOW_BACKGROUND;
            }

            if (hpRatio < HEALTH_CRITICAL_THRESHOLD) {
                return ANSI_DARK_MAROON_BACKGROUND;
            }
        }

        return ANSI_BLACK_BACKGROUND;
    }

    private String getSprite(WorldMap worldMap, Coordinate coordinate) {
        Entity entity = worldMap.getEntity(coordinate).orElse(null);

        if (entity != null) {
            return switch (entity.getClass().getSimpleName()) {
                case "Predator" -> SpriteType.PREDATOR.getSprite();
                case "Herbivore" -> SpriteType.HERBIVORE.getSprite();
                case "Grass" -> SpriteType.GRASS.getSprite();
                case "Rock" -> SpriteType.ROCK.getSprite();
                case "Tree" -> SpriteType.TREE.getSprite();
                default -> throw new UnknownEntityException(entity.getClass().getSimpleName());
            };
        }

        return SpriteType.EMPTY.getSprite();
    }

    private enum SpriteType {
        EMPTY("\u2B1B"),
        PREDATOR("\uD83E\uDD8A"),
        HERBIVORE("\uD83D\uDC07"),
        GRASS("\uD83C\uDF31"),
        ROCK("\uD83D\uDDFB"),
        TREE("\uD83C\uDFDD\uFE0F");

        private final String sprite;

        SpriteType(String sprite) {
            this.sprite = sprite;
        }

        public String getSprite() {
            return sprite;
        }
    }
}
