package main.java.phsimulation.dialogs;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractDialog<T> implements Dialog<T>{
    protected String title;
    protected String invalidInput;
    private final Function<String, T> mapper;
    private final Predicate<T> validator;
    protected Scanner scanner = new Scanner(System.in);

    protected AbstractDialog(String title, String error, Function<String, T> mapper, Predicate<T> validator) {
        this.title = title;
        this.invalidInput = error;
        this.mapper = mapper;
        this.validator = validator;
    }

    protected void showTitle() {
        System.out.println(title);
    }

    protected void showInvalidInput() {
        System.out.println(invalidInput);
    }

    @Override
    public T input() {
        while (true) {
            showTitle();
            String input = scanner.nextLine();

            try {
                T result = mapper.apply(input);
                if (validator.test(result)) {
                    return result;
                }
            } catch (IllegalArgumentException ignored) {
            }

            showInvalidInput();
        }
    }
}
