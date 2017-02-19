package cz.fku.java8;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;

/**
 * Created by Filip on 26.12.2016.
 */
public class OptionalExample {

    final private org.slf4j.Logger logger = LoggerFactory.getLogger(OptionalExample.class);

    @Test
    public void optionalTest() {
        Optional<Double> optional1 = Optional.of(3.00);
        Optional<Double> optional2 = Optional.empty();
        Optional<Double> optional3 = Optional.ofNullable(null);

        Supplier<Double> supplierRandom = () -> Math.random();
        Consumer<Double> consumerCos = (number) -> {
            logger.info("Cosinus of " + number + " is: ");
            logger.info( String.valueOf(Math.cos(number)) );
        };

        List<Double> list = new ArrayList<>();

        //supplier
        Double result1 = optional1
                .orElseGet(supplierRandom);
        logger.info(result1.toString());

        Double result2 = optional2
                .orElseGet(supplierRandom);
        logger.info(result2.toString());

        //consumer
        optional1.ifPresent(consumerCos);
    }

}
