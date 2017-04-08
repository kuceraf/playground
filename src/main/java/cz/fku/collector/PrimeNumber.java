package cz.fku.collector;

import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

/**
 * Created by Filip on 08.04.2017.
 */
public class PrimeNumber {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(PrimeNumber.class);

    public boolean isPrime(int candidate) {
        logger.info("Testing number:" + candidate);
        int candidateRoot = (int) Math.sqrt((double) candidate);
        logger.info("Square root:" + candidateRoot);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public boolean isPrimeOld(int candidate) {
        logger.info("Testing number:" + candidate);
        int candidateRoot = (int) Math.sqrt((double) candidate);
        logger.info("Square root:" + candidateRoot);
        for(int i = 2; i <= candidateRoot; i++) {
            int remainder = candidate % i;
            logger.info("Executing test: "+candidateRoot +" % "+i+" = "+remainder);
            if (remainder == 0) {
                logger.info("It is not prime number, found divisor: " + i);
                return false;
            }
        }
        logger.info("It is prime number");
        return true;
    }

    public boolean isEven (int candidate) {
        logger.info("Testing number:" + candidate);
        int result = candidate & 1;
        logger.info("Executing bitwise operation: " + Integer.toBinaryString(candidate)+" & "+1+" = "+result );
        return (result) == 0;
    }
}
