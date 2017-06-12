package cz.fku.designpattern.chainofresponsibility;

import cz.fku.desingnpattern.chainofresponsibility.HeaderTextProcessing;
import cz.fku.desingnpattern.chainofresponsibility.SpellCheckerProcessing;
import cz.fku.java8.parallelism.ParallelTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by Filip on 08.05.2017.
 */
public class ChainOfResponsibilityTest {
    final private Logger logger = LoggerFactory.getLogger(ChainOfResponsibilityTest.class);

    @Test
    public void testChainOfResponsibility() throws Exception {
        HeaderTextProcessing headerTextProcessing = new HeaderTextProcessing();
        SpellCheckerProcessing spellCheckerProcessing = new SpellCheckerProcessing();

        headerTextProcessing.setSuccessor(spellCheckerProcessing);
        String result = headerTextProcessing.handle("We like labda");
        logger.info(result);
    }

    @Test
    public void lambdaChaining() throws Exception {
        UnaryOperator<String> headerProcessing = (String input) -> "Text header: " + input;
        UnaryOperator<String> spellChecker = (String input) -> input.replaceAll("labda", "lambda");
        Function<String,String> pipeline = headerProcessing.andThen(spellChecker);

        String result = pipeline.apply("We like labda");

        logger.info(result);
    }
}
