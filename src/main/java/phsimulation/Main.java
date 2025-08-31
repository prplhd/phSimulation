package main.java.phsimulation;

import main.java.phsimulation.exceptions.SimulationException;

public class Main {

    public static void main(String[] args) {
        try {
            SimulationLauncher.launch();
        } catch (SimulationException e) {
            System.out.println(e.getMessage() + "\nCall chain:");

            for (StackTraceElement s : e.getStackTrace()) {
                System.out.println(s.toString());
            }
        }
    }
}
