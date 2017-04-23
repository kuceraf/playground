package cz.fku.java8.parallelism;

import cz.fku.java8.LambdaExamples;
import cz.fku.parallelism.Accumulator;
import cz.fku.parallelism.ParallelStream;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by Filip on 17.04.2017.
 */
public class ParallelTest {
    final private Logger logger = LoggerFactory.getLogger(ParallelTest.class);


    private long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    //SEQUENTIAL
    @Test
    public void sequentialSumTest(){
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        logger.info(String.valueOf(availableProcessors));

        System.out.println("Sequential sum done in: " +
                measureSumPerf(ParallelStream::sequentialSum, 10_000_000) + " msecs");
    }

    @Test
    public void sequentialSumNoBoxingTest(){
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        logger.info(String.valueOf(availableProcessors));

        System.out.println("Sequential sum done in: " +
                measureSumPerf(ParallelStream::sequentialNoBoxingSum, 10_000_000) + " msecs");
    }

    @Test
    public void iterativeSumTest(){
        System.out.println("Iterative sum done in: " +
                measureSumPerf(ParallelStream::iterativeSum, 10_000_000) + " msecs");
    }

    //PARALLEL
//    Parallel sum is slower because:
//    the iterate operation is hard to split
//    into chunks that can be executed independently
//    because the input of one function application
//    always depends on the result of the previous application
    @Test
    public void iterativeParallelSumTest() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        logger.info(String.valueOf(availableProcessors));

        System.out.println("Parallel sum done in: " +
                measureSumPerf(ParallelStream::parallelSum, 10_000_000) + " msecs");
    }

    //this sum is mutating a shared accumulator - while use in parallel it provides incorrect result
    //there is data race on every access of total variable
    //multiple threads are concurrently accessing the accumulator and in particular executing total += value
    @Test
    public void sideEffectParallelSumTest() {
        System.out.println("SideEffect parallel sum done in: " +
                        measureSumPerf(ParallelStream::sideEffectParallelSum, 10_000_000L) + " msecs" );
    }

    //Best solution
    //The stream can be easily split into multiple parts (for each thread) and the combination of
    //partial result into final result isn´t expensive
    @Test
    public void rangedParallelSumTest() {
        System.out.println("Ranged parallel sum done in: " +
                measureSumPerf(ParallelStream::rangedParallelSum, 10_000_000L) + " msecs" );
    }

}
