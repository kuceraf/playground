package cz.fku.desingnpattern.chainofresponsibility;

/**
 * Created by Filip on 08.05.2017.
 */
public class SpellCheckerProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return input.replaceAll("labda", "lambda");
    }
}
