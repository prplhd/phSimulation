package main.java.phsimulation;

import main.java.phsimulation.coordinate.Coordinate;
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
            WorldMap worldMap = new WorldMap(10, 10);
            worldMap.setEntity(new Coordinate(1, 1), new Predator(2, 100, 50));
            worldMap.setEntity(new Coordinate(1, 4), new Herbivore(1, 100, 20));
            worldMap.setEntity(new Coordinate(5, 5), new Herbivore(1, 100, 20));
            worldMap.setEntity(new Coordinate(7, 2), new Herbivore(1, 100, 20));
            worldMap.setEntity(new Coordinate(8, 3), new Grass());
            worldMap.setEntity(new Coordinate(2, 7), new Grass());
            worldMap.setEntity(new Coordinate(4, 5), new Tree());
            worldMap.setEntity(new Coordinate(3, 1), new Tree());
            worldMap.setEntity(new Coordinate(3, 2), new Rock());
            worldMap.setEntity(new Coordinate(2, 6), new Rock());
            worldMap.setEntity(new Coordinate(7, 3), new Rock());

            WorldMapRenderer worldMapRenderer = new ConsoleWorldMapRenderer();
            worldMapRenderer.render(worldMap);
            System.out.println();

            for (int i = 0; i < 20; i++) {
                for (Coordinate key : worldMap.getEntitiesCopy().keySet()) {
                    Thread.sleep(15);
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
