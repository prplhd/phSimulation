package main.java.phsimulation;

import main.java.phsimulation.actions.Action;
import main.java.phsimulation.actions.MaintainPopulationAction;
import main.java.phsimulation.actions.MoveAllCreaturesAction;
import main.java.phsimulation.actions.PopulateWorldAction;
import main.java.phsimulation.config.SimulationConfig;
import main.java.phsimulation.rendering.WorldMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final WorldMap worldMap;
    private final WorldMapRenderer worldMapRenderer;
    private final List<Action> initActions = new ArrayList<>();
    private final List<Action> turnActions = new ArrayList<>();
    private final Object lock = new Object();
    private volatile boolean running = true;
    private volatile boolean paused;
    private int turnCounter = 0;

    public Simulation(SimulationConfig cfg) {
        this.worldMap = new WorldMap(cfg.getWorldMapHeight(), cfg.getWorldMapWidth());
        this.worldMapRenderer = cfg.getWorldMapRenderer();
        initActions.add(new PopulateWorldAction(cfg));
        turnActions.add(new MoveAllCreaturesAction(cfg));
        turnActions.add(new MaintainPopulationAction(cfg));
    }

    public void startSimulation() {
        incrementAndPrintTurnCounter();
        makeTurn(initActions);
        printControlCommands();
        delayBeforeNextTurn();

        while (running) {
            synchronized (lock) {
                while (paused) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            incrementAndPrintTurnCounter();
            makeTurn(turnActions);
            printControlCommands();
            delayBeforeNextTurn();
        }
    }

    public void nextTurn() {
        incrementAndPrintTurnCounter();
        makeTurn(turnActions);
        printControlCommands();
    }

    public void pauseSimulation() {
        synchronized (lock) {
            paused = true;
        }
    }

    public void resumeSimulation() {
        synchronized (lock) {
            paused = false;
            lock.notifyAll();
        }
    }

    public void stopSimulation() {
        running = false;
        resumeSimulation();
    }

    private void incrementAndPrintTurnCounter() {
        turnCounter++;
        System.out.printf("""
                
                ────═ Ход: %d ═────
                """, turnCounter);
    }

    private void printControlCommands() {
        System.out.printf("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                  %d. Пауза         %d. Продолжить
                  %d. Сделать ход   %d. Выйти
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """, SimulationLauncher.COMMAND_PAUSE,
                SimulationLauncher.COMMAND_RESUME,
                SimulationLauncher.COMMAND_NEXT_TURN,
                SimulationLauncher.COMMAND_EXIT);
    }

    private void makeTurn(List<Action> actions) {
        for (Action action : actions) {
            action.execute(worldMap);
        }
        render();
    }


    private void render() {
        worldMapRenderer.render(worldMap);
    }

    private void delayBeforeNextTurn() {
        try {
            Thread.sleep(2200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}