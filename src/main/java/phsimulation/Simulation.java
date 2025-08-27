package main.java.phsimulation;

import main.java.phsimulation.actions.Action;
import main.java.phsimulation.actions.MaintainPopulationAction;
import main.java.phsimulation.actions.MoveAllCreaturesAction;
import main.java.phsimulation.actions.PopulateWorldAction;
import main.java.phsimulation.config.SimulationConfig;
import main.java.phsimulation.rendering.ConsoleWorldMapRenderer;
import main.java.phsimulation.rendering.WorldMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final SimulationConfig cfg;
    private final WorldMap worldMap;
    private final WorldMapRenderer worldMapRenderer;
    private final List<Action> initActions = new ArrayList<>();
    private final List<Action> turnActions = new ArrayList<>();

    public Simulation(SimulationConfig cfg) {
        this.cfg = cfg;
        this.worldMap = new WorldMap(cfg.getWorldMapHeight(), cfg.getWorldMapWidth());
        this.worldMapRenderer = new ConsoleWorldMapRenderer();
        initActions.add(new PopulateWorldAction(cfg));
        turnActions.add(new MoveAllCreaturesAction());
        turnActions.add(new MaintainPopulationAction(cfg));
    }

    public void startSimulation() {
        for (Action action : initActions) {
            action.execute(worldMap);
        }
        worldMapRenderer.render(worldMap);

        for (int i = 0; i < 500; i++) {
            for (Action action : turnActions) {
                action.execute(worldMap);
            }
            worldMapRenderer.render(worldMap);
        }
    }


}
