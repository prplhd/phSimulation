package main.java.phsimulation.actions;

import main.java.phsimulation.WorldMap;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.entities.creatures.Creature;

public final class MoveAllCreaturesAction implements Action{

    @Override
    public void execute(WorldMap worldMap) {
        for (Coordinate coordinate : worldMap.getEntitiesCopy().keySet()) {
            if (worldMap.getEntity(coordinate).orElse(null) instanceof Creature c) {
                c.makeMove(worldMap, coordinate);
            }
        }
    }
}
