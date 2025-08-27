package main.java.phsimulation;

import main.java.phsimulation.actions.MaintainPopulationAction;
import main.java.phsimulation.actions.MoveAllCreaturesAction;
import main.java.phsimulation.actions.PopulateWorldAction;
import main.java.phsimulation.config.SimulationConfig;
import main.java.phsimulation.config.SimulationPreset;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.dialogs.Dialog;
import main.java.phsimulation.dialogs.IntegerSelectDialog;
import main.java.phsimulation.exceptions.InvalidCoordinateException;
import main.java.phsimulation.exceptions.InvalidMoveException;
import main.java.phsimulation.exceptions.SimulationException;
import main.java.phsimulation.rendering.ConsoleWorldMapRenderer;
import main.java.phsimulation.rendering.WorldMapRenderer;
import main.java.phsimulation.messages.DialogMessages;
import main.java.phsimulation.messages.WelcomeMessages;

import java.util.List;

public class Main {


    public static void main(String[] args) {
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

            Simulation simulation = new Simulation(cfg);

        try {
            simulation.startSimulation();
        } catch (SimulationException e) {
            System.out.println(e.getMessage() + "\nCall chain:");

            for (StackTraceElement s : e.getStackTrace()) {
                System.out.println(s.toString());
            }
        }
    }
}
