package cz.fku.java8.function.stream;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Filip on 31.03.2017.
 */
public class FibonacciTuples {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(FibonacciTuples.class);


    @Test
    public void fibonacciTestWrong(){
        List<int[]> collect = Stream.iterate(new int[]{0, 1}, n -> {
            //here must be new tuple, in this case will collection contain 20 identical object
            int first = n[0];
            int second = n[1];
            n[0] = second;
            n[1] = first + second;

            return n;
        })
                .limit(20)
                .collect(Collectors.toList());
//                .forEach(tuple -> logger.info("("+String.valueOf(tuple[0]) +","+ String.valueOf(tuple[1])+")"));
    }

    @Test
    public void fibonacciTestIterate(){
        List<int[]> collect = Stream.iterate(new int[]{0, 1}, n -> {
            int first = n[0];
            int second = n[1];
            return new int[]{second, first + second};

        })
                .limit(20)
                .collect(Collectors.toList());
//                .forEach(tuple -> logger.info("("+String.valueOf(tuple[0]) +","+ String.valueOf(tuple[1])+")"));
    }

    IntSupplier fib = new IntSupplier() {
        private int previous = 0;
        private int current = 1;
        @Override
        public int getAsInt() {
            int oldPrevious = this.previous;
            int nextValue = this.previous + this.current;
            this.previous = this.current;
            this.current = nextValue;
            return oldPrevious;
        }
    };

    @Test
    public void testFibonacciGenerate() {
        IntStream.generate(fib).limit(20)
                        .forEach(tuple -> logger.info(String.valueOf(tuple)));

    }
}
