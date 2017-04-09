package cz.fku.java8.collector;

import cz.fku.collector.PrimeNumber;
import cz.fku.collector.PrimeNumberCollector;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


/**
 * Created by Filip on 08.04.2017.
 */
public class PrimeNumberPartitioningTest {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(PrimeNumberPartitioningTest.class);
    private PrimeNumber primeNumber = new PrimeNumber();

    //TODO test perfoemance in JMH framework
    @Test
    public void partitionPrimes() {
        int n = 100;

        Map<Boolean, List<Integer>> partitionPrimes = primeNumber.partitionPrimes(1000000);
        Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector = primeNumber.partitionPrimesWithCustomCollector(1000000);

        assertThat(partitionPrimes, is(partitionPrimesWithCustomCollector));
    }



    @Test
    public void isPrimeOldTest() {
        primeNumber.isPrimeOld(169);
    }

    @Test
    public void isPrimeTest() {
        primeNumber.isPrime(81);
    }

    @Test
    public void isEvenTest() {
        primeNumber.isEven(5);
    }
}
