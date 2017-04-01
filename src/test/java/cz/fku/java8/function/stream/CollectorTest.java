package cz.fku.java8.function.stream;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.reducing;
import static org.junit.Assert.assertEquals;

/**
 * Created by Filip on 31.03.2017.
 */
public class CollectorTest {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(FibonacciTuples.class);

    @Test
    public void testCollectorOperation() {
        //using convenient specializations of a reduction
        int sum1 = IntStream.of(1, 2, 3, 4, 5, 6)
                .sum();

        IntSummaryStatistics intSummaryStatistics = IntStream.of(1, 2, 3, 4, 5, 6)
                .summaryStatistics();

        logger.info(intSummaryStatistics.toString());

        //using reduction factory method
        Integer sum2 = IntStream.of(1, 2, 3, 4, 5, 6)
                .boxed()
                .collect(reducing(0, (a, b) -> a + b));

        assertEquals(sum1,sum2.intValue());

//        Collect vs. reduce

        /*collect method is designed to mutate a container to accumulate the result it’s supposed to produce*/
        //misused of collect
        Integer collectSum = Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.reducing(0, (a, b) -> a + b));

        /*reduce method is meant to combine two values and
        produce a new one; it’s an immutable reduction.*/
        //good usage of reduce
        Integer reduceSum = Stream.of(1, 2, 3, 4, 5, 6)
                .reduce(0, (a, b) -> a + b);

    }
}
