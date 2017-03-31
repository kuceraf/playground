package cz.fku.java8.function.stream;

import com.google.common.collect.Interner;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;


/**
 * Created by Filip on 17.03.2017.
 *
 * Sum, min, max count are special cases of reduction
 * Reduction reduce elements to a single summary value using combining function
 */
public class ReductionTest {

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
