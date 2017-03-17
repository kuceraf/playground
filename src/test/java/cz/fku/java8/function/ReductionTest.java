package cz.fku.java8.function;

import com.google.common.collect.Interner;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Filip on 17.03.2017.
 *
 * Sum, min, max count are special cases of reduction
 * Reduction reduce elements to a single summary value using combining function
 */
public class ReductionTest {

    @Test
    public void testReduction() {
       List<Integer> list = new ArrayList<Integer>() {{
           add(1);
           add(2);
           add(2);
       }};

       //use sum
       int total1 = list.stream().mapToInt(integer -> integer).sum();
       int total2 = list.stream().reduce(0, (integer, integer2) -> integer = integer + integer2);

        Assert.assertEquals(total1,total2);
    }
}
