package main.java.phsimulation.messages;

import main.java.phsimulation.config.SimulationFactory;

public final class DialogMessages {
    public static final String PRESET_SELECT_DIALOG_TITLE = """
            Пожалуйста, выберите размер карты симуляции:
            %d. Маленький *
            %d. Средний
            %d. Большой
            
            * Обратите внимание, что в маленьком размере используются буквы вместо emoji,
            где P - хищник, H - травоядное, G - трава, T - дерево, R - камень.
            """.formatted(SimulationFactory.SMALL_WORLD_KEY, SimulationFactory.MEDIUM_WORLD_KEY, SimulationFactory.LARGE_WORLD_KEY);

    public static final String PRESET_SELECT_DIALOG_ERROR = "Неверный ввод. Попробуйте еще раз \n";

    public static final String COMMAND_SELECT_DIALOG_TITLE = "";

    public static final String COMMAND_SELECT_DIALOG_ERROR = "Неверный ввод. Попробуйте еще раз \n";

    private DialogMessages() {}
}
