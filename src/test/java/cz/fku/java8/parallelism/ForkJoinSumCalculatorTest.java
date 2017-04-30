package cz.fku.java8.parallelism;

import cz.fku.parallelism.ForkJoinSumCalculator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Filip on 30.04.2017.
 */
public class ForkJoinSumCalculatorTest {

    @Test
    public void forkJoinSumTest() {
        long result = ForkJoinSumCalculator.forkJoinSum(1_000_000);
        Assert.assertNotNull(result);
        Assert.assertEquals(500000500000L,result);
    }
}
