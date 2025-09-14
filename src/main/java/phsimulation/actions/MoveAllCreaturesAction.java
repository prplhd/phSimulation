package main.java.phsimulation.actions;

import main.java.phsimulation.WorldMap;
import main.java.phsimulation.config.SimulationConfig;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.entities.Entity;
import main.java.phsimulation.entities.creatures.Creature;

import java.util.Optional;

public final class MoveAllCreaturesAction implements Action{
    private final SimulationConfig cfg;

    public MoveAllCreaturesAction(SimulationConfig cfg) {
        this.cfg = cfg;
    }

    @Override
    public void execute(WorldMap worldMap) {
        for (Coordinate coordinate : worldMap.toMap().keySet()) {
            Optional<Entity> optional = worldMap.getEntity(coordinate);
            if (optional.isEmpty()) {
                continue;
            }

            if (optional.get() instanceof Creature c) {
                c.makeMove(worldMap, coordinate, cfg.getPathfinder());
            }
        }
    }
}
