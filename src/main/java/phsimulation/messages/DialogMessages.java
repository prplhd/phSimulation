package main.java.phsimulation.messages;

import main.java.phsimulation.config.SimulationPreset;

public class DialogMessages {
    public static final String SIMULATION_PRESET_DIALOG_TITLE = """
            Пожалуйста, выберите размер карты симуляции:
            %d. Маленький
            %d. Средний
            %d. Большой
            """.formatted(SimulationPreset.SMALL_WORLD_KEY, SimulationPreset.MEDIUM_WORLD_KEY, SimulationPreset.LARGE_WORLD_KEY);

    public static final String SIMULATION_PRESET_DIALOG_ERROR = "Неверный ввод. Попробуйте еще раз \n";

    private DialogMessages() {}
}
