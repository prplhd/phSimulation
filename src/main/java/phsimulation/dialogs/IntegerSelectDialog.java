package main.java.phsimulation.dialogs;

import java.util.List;

public class IntegerSelectDialog extends AbstractDialog<Integer>{

    public IntegerSelectDialog(String title, String error, List<Integer> presetKeys) {
        super(title, error, Integer::parseInt, presetKeys::contains);
    }
}
