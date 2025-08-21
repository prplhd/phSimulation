package main.java.phsimulation;

import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.entities.EntityFactory;
import main.java.phsimulation.entities.EntityType;
import main.java.phsimulation.entities.creatures.Creature;
import main.java.phsimulation.entities.creatures.Herbivore;
import main.java.phsimulation.entities.creatures.Predator;
import main.java.phsimulation.entities.terrain.Grass;
import main.java.phsimulation.entities.terrain.Rock;
import main.java.phsimulation.entities.terrain.Tree;
import main.java.phsimulation.exceptions.SimulationException;
import main.java.phsimulation.rendering.ConsoleWorldMapRenderer;
import main.java.phsimulation.rendering.WorldMapRenderer;

public class Main {
    public static void main(String[] args) {
        try {
            EntityFactory entityFactory = new EntityFactory();
            WorldMap worldMap = new WorldMap(10, 10);
            worldMap.setEntity(new Coordinate(1, 1), entityFactory.create(EntityType.PREDATOR));
            worldMap.setEntity(new Coordinate(1, 4), entityFactory.create(EntityType.HERBIVORE));
            worldMap.setEntity(new Coordinate(5, 5), entityFactory.create(EntityType.HERBIVORE));
            worldMap.setEntity(new Coordinate(7, 2), entityFactory.create(EntityType.HERBIVORE));
            worldMap.setEntity(new Coordinate(8, 3), entityFactory.create(EntityType.GRASS));
            worldMap.setEntity(new Coordinate(2, 7), entityFactory.create(EntityType.GRASS));
            worldMap.setEntity(new Coordinate(4, 5), entityFactory.create(EntityType.TREE));
            worldMap.setEntity(new Coordinate(3, 1), entityFactory.create(EntityType.TREE));
            worldMap.setEntity(new Coordinate(3, 2), entityFactory.create(EntityType.ROCK));
            worldMap.setEntity(new Coordinate(2, 6), entityFactory.create(EntityType.ROCK));
            worldMap.setEntity(new Coordinate(7, 3), entityFactory.create(EntityType.ROCK));

            WorldMapRenderer worldMapRenderer = new ConsoleWorldMapRenderer();
            String res = """
                    ⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛
                    ⬛⬛🦊🦊🦊🦊⬛🐇🐇🐇⬛🌱⬛⬛⬛🌱⬛⛰️⬛⬛⬛⛰️⬛🏝️⬛⬛⬛⬛⬛⬛🦊⬛⬛⬛🐇🐇🐇🐇🐇⬛⛰️⛰️⛰️⬛⬛🌱🌱🌱⬛⬛🏝️⬛⬛⬛🏝️⬛
                    ⬛🦊⬛⬛⬛⬛⬛⬛🐇⬛⬛🌱🌱⬛🌱🌱⬛⛰️⬛⬛⬛⛰️⬛🏝️⬛⬛⬛⬛⬛🦊⬛🦊⬛⬛⬛⬛🐇⬛⬛⬛⬛⛰️⬛⬛🌱⬛⬛⬛🌱⬛🏝️🏝️️⬛⬛🏝️⬛
                    ⬛⬛🦊🦊🦊⬛⬛⬛🐇⬛⬛🌱⬛🌱⬛🌱⬛⛰️⬛⬛⬛⛰️⬛🏝️⬛⬛⬛⬛🦊⬛⬛⬛🦊⬛⬛⬛🐇⬛⬛⬛⬛⛰️⬛⬛🌱⬛⬛⬛🌱⬛🏝️⬛🏝️️⬛🏝️⬛
                    ⬛⬛⬛⬛⬛🦊⬛⬛🐇⬛⬛🌱⬛⬛⬛🌱⬛⛰️⬛⬛⬛⛰️⬛🏝️⬛⬛⬛⬛🦊🦊🦊🦊🦊⬛⬛⬛🐇⬛⬛⬛⬛⛰️⬛⬛🌱⬛⬛⬛🌱⬛🏝️⬛⬛🏝️️🏝️⬛
                    ⬛⬛⬛⬛⬛🦊⬛⬛🐇⬛⬛🌱⬛⬛⬛🌱⬛⛰️⬛⬛⬛⛰️⬛🏝️⬛⬛⬛⬛🦊⬛⬛⬛🦊⬛⬛⬛🐇⬛⬛⬛⬛⛰️⬛⬛🌱⬛⬛⬛🌱⬛🏝️⬛⬛⬛🏝️⬛
                    ⬛🦊🦊🦊🦊⬛⬛🐇🐇🐇⬛🌱⬛⬛⬛🌱⬛⬛⛰️⛰️⛰️⬛⬛🏝️🏝️🏝️🏝️⬛🦊⬛⬛⬛🦊⬛⬛⬛🐇⬛⬛⬛⛰️⛰️⛰️⬛⬛🌱🌱🌱⬛⬛🏝️⬛⬛⬛🏝️️⬛
                    ⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛
                    """;
            System.out.println(res);
            worldMapRenderer.render(worldMap);
            System.out.println();

            for (int i = 0; i < 10; i++) {
                for (Coordinate key : worldMap.getEntitiesCopy().keySet()) {
                    Thread.sleep(140);
                    if (worldMap.getEntity(key).orElse(null) instanceof Creature c) {
                        c.makeMove(worldMap, key);
                    }
                }
                worldMapRenderer.render(worldMap);
                System.out.println();

            }

            int i = 0;
        } catch (SimulationException e) {
            System.out.println(e.getMessage() + "\nCall chain:");

            for (StackTraceElement s : e.getStackTrace()) {
                System.out.println(s.toString());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
