package cz.fku.java8.function.stream;

import com.google.common.collect.Interner;
import cz.fku.java8.parallelism.WordCounter;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;


/**
 * Created by Filip on 17.03.2017.
 *
 * Sum, min, max count are special cases of reduction
 * Reduction reduce elements to a single summary value using combining function
 */
public class ReductionTest {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(ReductionTest.class);


    @Test
    public void testReduction() {
       List<Integer> list = Arrays.asList(1,2,3);

       //use sum
       int total1 = list.stream().mapToInt(integer -> integer).sum();
       int total2 = list.stream().reduce(0, (integer, integer2) -> integer = integer + integer2);
       Optional<Integer> total3opt = list.stream().reduce((Integer::sum));
       assertEquals(total1,total2,total3opt.get());

        //maximum
        Optional<Integer> max1 = list.stream()
                .reduce((a, b) -> (a >= b) ? a : b);
        Optional<Integer> max2 = list.stream()
                .reduce(Integer::max);

        assertEquals(max1, max2);

    }
}
