package main.java.phsimulation;

import main.java.phsimulation.actions.MaintainPopulationAction;
import main.java.phsimulation.actions.MoveAllCreaturesAction;
import main.java.phsimulation.actions.PopulateWorldAction;
import main.java.phsimulation.config.SimulationConfig;
import main.java.phsimulation.config.SimulationPreset;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.entities.factory.EntityFactory;
import main.java.phsimulation.entities.EntityType;
import main.java.phsimulation.entities.creatures.Creature;
import main.java.phsimulation.exceptions.SimulationException;
import main.java.phsimulation.rendering.ConsoleWorldMapRenderer;
import main.java.phsimulation.rendering.WorldMapRenderer;

public class Main {
    public static void main(String[] args) {
        try {
            SimulationConfig cfg = SimulationPreset.getForSize(SimulationPreset.WorldSize.SMALL);

            WorldMap worldMap = new WorldMap(cfg.getWorldMapHeight(), cfg.getWorldMapWidth());

            PopulateWorldAction populateWorldAction = new PopulateWorldAction(cfg);
            MaintainPopulationAction maintainPopulationAction = new MaintainPopulationAction(cfg);
            MoveAllCreaturesAction moveAllCreaturesAction = new MoveAllCreaturesAction();
            populateWorldAction.execute(worldMap);

            WorldMapRenderer worldMapRenderer = new ConsoleWorldMapRenderer();
            String res = """
                    ⬛⬛🦊🦊🦊🦊⬛🐇🐇🐇⬛🌱⬛⬛⬛🌱⬛🗻⬛⬛⬛🗻⬛🏝️⬛⬛⬛⬛⬛⬛🦊⬛⬛⬛🐇🐇🐇🐇🐇⬛🗻🗻🗻⬛⬛🌱🌱🌱⬛⬛🏝️⬛⬛⬛🏝️⬛
                    ⬛🦊⬛⬛⬛⬛⬛⬛🐇⬛⬛🌱🌱⬛🌱🌱⬛🗻⬛⬛⬛🗻⬛🏝️⬛⬛⬛⬛⬛🦊⬛🦊⬛⬛⬛⬛🐇⬛⬛⬛⬛🗻⬛⬛🌱⬛⬛⬛🌱⬛🏝️🏝️️⬛⬛🏝️⬛
                    ⬛⬛🦊🦊🦊⬛⬛⬛🐇⬛⬛🌱⬛🌱⬛🌱⬛🗻⬛⬛⬛🗻⬛🏝️⬛⬛⬛⬛🦊⬛⬛⬛🦊⬛⬛⬛🐇⬛⬛⬛⬛🗻⬛⬛🌱⬛⬛⬛🌱⬛🏝️⬛🏝️️⬛🏝️⬛
                    ⬛⬛⬛⬛⬛🦊⬛⬛🐇⬛⬛🌱⬛⬛⬛🌱⬛🗻⬛⬛⬛🗻⬛🏝️⬛⬛⬛⬛🦊🦊🦊🦊🦊⬛⬛⬛🐇⬛⬛⬛⬛🗻⬛⬛🌱⬛⬛⬛🌱⬛🏝️⬛⬛🏝️️🏝️⬛
                    ⬛⬛⬛⬛⬛🦊⬛⬛🐇⬛⬛🌱⬛⬛⬛🌱⬛🗻⬛⬛⬛🗻⬛🏝️⬛⬛⬛⬛🦊⬛⬛⬛🦊⬛⬛⬛🐇⬛⬛⬛⬛🗻⬛⬛🌱⬛⬛⬛🌱⬛🏝️⬛⬛⬛🏝️⬛
                    ⬛🦊🦊🦊🦊⬛⬛🐇🐇🐇⬛🌱⬛⬛⬛🌱⬛⬛🗻🗻🗻⬛⬛🏝️🏝️🏝️🏝️⬛🦊⬛⬛⬛🦊⬛⬛⬛🐇⬛⬛⬛🗻🗻🗻⬛⬛🌱🌱🌱⬛⬛🏝️⬛⬛⬛🏝️️⬛
                    """;
            System.out.println(res);
            worldMapRenderer.render(worldMap);
            System.out.println();

            for (int i = 0; i < 30; i++) {
                moveAllCreaturesAction.execute(worldMap);
                maintainPopulationAction.execute(worldMap);
                worldMapRenderer.render(worldMap);
            }

            int i = 0;
        } catch (SimulationException e) {
            System.out.println(e.getMessage() + "\nCall chain:");

            for (StackTraceElement s : e.getStackTrace()) {
                System.out.println(s.toString());
            }
        }
    }
}
