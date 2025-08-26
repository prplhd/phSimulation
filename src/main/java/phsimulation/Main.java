package main.java.phsimulation;

import main.java.phsimulation.actions.MaintainPopulationAction;
import main.java.phsimulation.actions.MoveAllCreaturesAction;
import main.java.phsimulation.actions.PopulateWorldAction;
import main.java.phsimulation.config.SimulationConfig;
import main.java.phsimulation.config.SimulationPreset;
import main.java.phsimulation.dialogs.Dialog;
import main.java.phsimulation.dialogs.IntegerSelectDialog;
import main.java.phsimulation.exceptions.SimulationException;
import main.java.phsimulation.rendering.ConsoleWorldMapRenderer;
import main.java.phsimulation.rendering.WorldMapRenderer;
import main.java.phsimulation.messages.DialogMessages;
import main.java.phsimulation.messages.WelcomeMessages;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        try {
            WelcomeMessages.printWelcomeMessages();

            List<Integer> presetKeys = List.of(
                    SimulationPreset.SMALL_WORLD_KEY,
                    SimulationPreset.MEDIUM_WORLD_KEY,
                    SimulationPreset.LARGE_WORLD_KEY);

            Dialog<Integer> simulationPresetDialog = new IntegerSelectDialog(
                    DialogMessages.SIMULATION_PRESET_DIALOG_TITLE,
                    DialogMessages.SIMULATION_PRESET_DIALOG_ERROR,
                    presetKeys);

            int worldSize = simulationPresetDialog.input();
            SimulationConfig cfg = SimulationPreset.getForSize(worldSize);

            WorldMap worldMap = new WorldMap(cfg.getWorldMapHeight(), cfg.getWorldMapWidth());

            PopulateWorldAction populateWorldAction = new PopulateWorldAction(cfg);
            MaintainPopulationAction maintainPopulationAction = new MaintainPopulationAction(cfg);
            MoveAllCreaturesAction moveAllCreaturesAction = new MoveAllCreaturesAction();
            populateWorldAction.execute(worldMap);

            WorldMapRenderer worldMapRenderer = new ConsoleWorldMapRenderer();
            worldMapRenderer.render(worldMap);
            System.out.println();

            for (int i = 0; i < 500; i++) {
                Thread.sleep(0);
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
        } catch (InterruptedException e) {
            return;
        }
    }
}
