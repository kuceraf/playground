package cz.fku.java8.collector;

import cz.fku.collector.PrimeNumber;
import org.junit.Test;
import org.slf4j.LoggerFactory;


/**
 * Created by Filip on 08.04.2017.
 */
public class PrimeNumberPartitioningTest {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(PrimeNumberPartitioningTest.class);
    private PrimeNumber primeNumber = new PrimeNumber();

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
