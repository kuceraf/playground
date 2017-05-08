package cz.fku.desingnpattern.strategy;

/**
 * Created by Filip on 08.05.2017.
 */
public class Validator {
    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }
}
