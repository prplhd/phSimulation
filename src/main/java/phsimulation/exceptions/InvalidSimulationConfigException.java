package main.java.phsimulation.exceptions;

import java.util.List;

public class InvalidSimulationConfigException extends SimulationException {
    public InvalidSimulationConfigException(List<String> errors) {
        super(joinErrors(errors));
    }

    private static String joinErrors(List<String> errors) {
        StringBuilder result = new StringBuilder();
        result.append("Config validation failed:\n");

        for (String error : errors) {
            result.append(error).append("\n");
        }

        return result.toString();
    }
}
