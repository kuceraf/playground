package cz.fku.collector;

import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by Filip on 08.04.2017.
 */
public class MyToListCollector<T> implements Collector<T, List<T>, List<T>>{

    @Override
    public Supplier<List<T>> supplier() {
//        return ArrayList<T>::new;
        return () -> {
            //Supplier = no arguments
            return new ArrayList<T>();
        };
    }

    @Override
//    The accumulator method returns the function that performs the reduction operation.
    public BiConsumer<List<T>, T> accumulator() {
//        return List<T>::add;
        return (List<T> accumulator, T inputElements) -> {
          //Consumer does not return anything
            //modifying the accumulator
            accumulator.add(inputElements);
        };
    }

    @Override
//    The combiner defines how the accumulators resulting from the reduction of
//    different subparts of the stream are combined when the subparts are processed in parallel
    public BinaryOperator<List<T>> combiner() {
        return (List<T> accumulator1, List<T> accumulator2) -> {
//            combines two intermediate accumulators
            accumulator1.addAll(accumulator2);
            return accumulator1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
//        return Function.identity();
        return t -> t;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return ImmutableSet.of(Collector.Characteristics.IDENTITY_FINISH);
    }
}
