package cz.fku.desingnpattern.strategy;

/**
 * Created by Filip on 08.05.2017.
 */
public class IsNumeric implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
