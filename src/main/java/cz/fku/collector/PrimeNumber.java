package cz.fku.collector;

import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * Created by Filip on 08.04.2017.
 */
public class PrimeNumber {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(PrimeNumber.class);

    //PARTITIONING PRIMES METHODS
    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    public Map<Boolean, List<Integer>>
    partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumberCollector());
    }

    //IS PRIME HELPERS

    //--------------BEGIN optimized version--------------
    /*
    * to find out if candidate is or is'nt prime number:
    * - check remainders of candidate divided by all other prime numbers less then
    * square root of candidate
    * - stop once you find a prime that’s greater than the candidate root
    * */
    public static boolean isPrimeOptimized(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }

    //helper for isPrimeOptimized
    //given a sorted list return sublist whose members satisfy the predicate
    static private <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }
    //--------------END optimized version--------------


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
        for (int i = 2; i <= candidateRoot; i++) {
            int remainder = candidate % i;
            logger.info("Executing test: " + candidateRoot + " % " + i + " = " + remainder);
            if (remainder == 0) {
                logger.info("It is not prime number, found divisor: " + i);
                return false;
            }
        }
        logger.info("It is prime number");
        return true;
    }

    public boolean isEven(int candidate) {
        logger.info("Testing number:" + candidate);
        int result = candidate & 1;
        logger.info("Executing bitwise operation: " + Integer.toBinaryString(candidate) + " & " + 1 + " = " + result);
        return (result) == 0;
    }
}
