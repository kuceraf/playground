package cz.fku.desingnpattern.chainofresponsibility;

/**
 * Created by Filip on 08.05.2017.
 */
public class HeaderTextProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return "Text header: " + input;
    }
}
