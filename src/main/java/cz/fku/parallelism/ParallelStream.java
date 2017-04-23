package cz.fku.parallelism;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by Filip on 22.04.2017.
 */
public class ParallelStream {
    public static Long sequentialSum(long n){
        Long result = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
        return result;
    }

    public static Long sequentialNoBoxingSum(long n){
        Long result = LongStream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, (a,b) -> a+b);
        return result;
    }

    public static Long parallelSum(long n){
        Long result = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
        return result;
    }

    public static Long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long rangedParallelSum(long n){
        long result = LongStream.rangeClosed(1, n).parallel()
                .reduce(0L, (a, b) -> a + b);

        return result;
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }
}
