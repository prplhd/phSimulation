package main.java.phsimulation;

import main.java.phsimulation.config.SimulationConfig;
import main.java.phsimulation.config.SimulationPreset;
import main.java.phsimulation.dialogs.Dialog;
import main.java.phsimulation.dialogs.IntegerSelectDialog;
import main.java.phsimulation.exceptions.SimulationException;
import main.java.phsimulation.messages.DialogMessages;
import main.java.phsimulation.messages.WelcomeMessages;

import java.util.List;

public class SimulationLauncher {
    public static final int COMMAND_PAUSE = 1;
    public static final int COMMAND_RESUME = 2;
    public static final int COMMAND_NEXT_TURN = 3;
    public static final int COMMAND_EXIT = 4;

    private static final String MESSAGE_PAUSE = "Ставим на паузу";
    private static final String MESSAGE_PAUSE_RESUME = "Возобновляем симуляцию";
    private static final String MESSAGE_PAUSE_NEXT_TURN = "Совершаем один ход";
    private static final String MESSAGE_PAUSE_EXIT = "До свидания";

    private SimulationLauncher() {}

    public static void launch() {
        WelcomeMessages.printWelcomeMessages();

        int worldSizeKey = getWorldSizeKey();
        SimulationConfig cfg = SimulationPreset.getForSize(worldSizeKey);
        Simulation simulation = new Simulation(cfg);

        Thread simulationThread = new Thread(() -> {
            try {
                simulation.startSimulation();
            } catch (SimulationException e) {
                System.out.println(e.getMessage() + "\nCall chain:");

                for (StackTraceElement s : e.getStackTrace()) {
                    System.out.println(s.toString());
                }
            }
        });
        simulationThread.start();

        runControlLoop(simulation);
    }

    private static int getWorldSizeKey() {
        List<Integer> presetKeys = List.of(
                SimulationPreset.SMALL_WORLD_KEY,
                SimulationPreset.MEDIUM_WORLD_KEY,
                SimulationPreset.LARGE_WORLD_KEY);

        Dialog<Integer> presetSelectDialog = new IntegerSelectDialog(
                DialogMessages.PRESET_SELECT_DIALOG_TITLE,
                DialogMessages.PRESET_SELECT_DIALOG_ERROR,
                presetKeys);

        return presetSelectDialog.input();
    }

    private static void runControlLoop(Simulation simulation) {


        List<Integer> presetKeys = List.of(
                COMMAND_PAUSE,
                COMMAND_RESUME,
                COMMAND_NEXT_TURN,
                COMMAND_EXIT
        );

        while (true) {
            Dialog<Integer> commandSelectDialog = new IntegerSelectDialog(
                    DialogMessages.COMMAND_SELECT_DIALOG_TITLE,
                    DialogMessages.COMMAND_SELECT_DIALOG_ERROR,
                    presetKeys);
            int choice = commandSelectDialog.input();

            switch (choice) {
                case COMMAND_PAUSE -> {
                    System.out.println(MESSAGE_PAUSE);
                    simulation.pauseSimulation();
                }
                case COMMAND_RESUME -> {
                    System.out.println(MESSAGE_PAUSE_RESUME);
                    simulation.resumeSimulation();
                }
                case COMMAND_NEXT_TURN -> {
                    System.out.println(MESSAGE_PAUSE_NEXT_TURN);
                    simulation.nextTurn();
                }
                case COMMAND_EXIT -> {
                    System.out.println(MESSAGE_PAUSE_EXIT);
                    simulation.stopSimulation();
                    return;
                }
                default -> throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }
}
