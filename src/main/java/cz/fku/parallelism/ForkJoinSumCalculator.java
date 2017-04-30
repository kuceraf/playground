package cz.fku.parallelism;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Created by Filip on 30.04.2017.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if(length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, start + length/2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, start + length/2, end);

//        It might seem natural to invoke fork on both the left and right subtasks,
//        but this is less efficient than just directly calling compute on one of them. Doing
//        this allows you to reuse the same thread for one of the two subtasks and avoid
//        the overhead caused by the unnecessary allocation of a further task on the pool.
        Long rightResult = rightTask.compute();
        //wait for the result of leftTask
        Long leftResult = leftTask.join();

        return leftResult + rightResult;
    }


    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
