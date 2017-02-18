package cz.fku.java8;

import cz.fku.other.Calculator;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Filip on 27.11.2016.
 */
public class LambdaExamples {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(LambdaExamples.class);

    //implementation of my functional interface
    @Test
    public void testCalculator() {
        Calculator addition = (a, b) -> (a + b);
        logger.info("RESULT: " + String.valueOf(addition.calculate(1, 1)));
    }

    //implementation of predefined interface from java.util.function
    @Test
    public void testFunction() {
        //parametr type of lambda can be infered from generic type of function interface (Integer may be omitted)
        //return type is infered from generic type of function interface (by default is returned the result of lambda expression)
        Function<Integer, Double> convert = (Integer miles) -> (miles * 1.6);
        logger.info("RESULT: " + String.valueOf(convert.apply(3)));
    }

    //implementation of predefined interface from java.util.function
    @Test
    public void testBiFunction() {
        BiFunction<Integer, Integer, Integer> area = (width, height) -> (width * height);
        logger.info("RESULT: " + area.apply(4, 8));
        area.apply(5, 8);
    }

    //???
    @Test
    public void testAfterFunction() {
        Function<Integer, Double> convert = (Integer miles) -> (miles * 1.6);
        logger.info("RESULT: " + String.valueOf(convert.apply(5)));
    }

    @Test
    public void testConsumer() {
        Function<Integer, String> spacer = (count) -> {
            StringBuilder sb = new StringBuilder(count);
            for (int i = 0; i < count; i++) {
                sb.append(" ");
            }
            return sb.toString();
        };

        int lineLength = 60; // characters
        Consumer<String> printCentered =
                (input) -> {
                    int length = input.length();
                    String spaces = spacer.apply(
                            (lineLength - length) / 2);
                    System.out.println(spaces + input);
                };

        printCentered.accept("A lambda expression a day");
        printCentered.accept("makes you");
        printCentered.accept("look smarter");

    }


    @Test
    public void testEcho() {
        Function<Integer,Integer> echo = (x) -> {
            return 2;
        };
        Integer result = echo.apply(4);
        logger.info("RESULT: " + result);
    }
}
