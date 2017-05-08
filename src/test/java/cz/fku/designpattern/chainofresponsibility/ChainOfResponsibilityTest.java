package cz.fku.designpattern.chainofresponsibility;

import cz.fku.desingnpattern.chainofresponsibility.HeaderTextProcessing;
import cz.fku.desingnpattern.chainofresponsibility.SpellCheckerProcessing;
import cz.fku.java8.parallelism.ParallelTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
