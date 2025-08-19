package main.java.phsimulation.rendering;

import main.java.phsimulation.exceptions.UnknownEntityException;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.WorldMap;
import main.java.phsimulation.entities.*;

import java.util.Optional;


public class ConsoleWorldMapRenderer implements WorldMapRenderer {
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[0;100m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Override
    public void render(WorldMap worldMap) {
        for (int y = worldMap.getHeight() - 1; y >= 0 ; y--) {
            for (int x = 0; x < worldMap.getWidth(); x++) {
                Coordinate coordinate = new Coordinate(x, y);
                worldMap.ensureInBounds(coordinate);
                System.out.print(ANSI_BLACK_BACKGROUND + getSprite(worldMap, coordinate) + ANSI_RESET);
            }
            System.out.println();
        }
    }

    private String getSprite(WorldMap worldMap, Coordinate coordinate) {
        Optional<Entity> optEntity = worldMap.getEntity(coordinate);

        if (optEntity.isPresent()) {
            Entity entity = optEntity.get();
            return switch (entity.getClass().getSimpleName()) {
                case "Predator" -> SpriteType.PREDATOR.getSprite();
                case "Herbivore" -> SpriteType.HERBIVORE.getSprite();
                case "Grass" -> SpriteType.GRASS.getSprite();
                case "Rock" -> SpriteType.ROCK.getSprite();
                case "Tree" -> SpriteType.TREE.getSprite();
                default -> throw new UnknownEntityException(entity);
            };
        }

        return SpriteType.EMPTY.getSprite();
    }

    private enum SpriteType {
        EMPTY("\u2B1B"),
        PREDATOR("\uD83E\uDD8A"),
        HERBIVORE("\uD83D\uDC07"),
        GRASS("\uD83C\uDF31"),
        ROCK("\u26F0\uFE0F"),
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
