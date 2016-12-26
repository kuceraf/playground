package cz.fku.java8;

import org.junit.Test;

import java.util.function.BinaryOperator;

/**
 * Created by Filip on 24.12.2016.
 */
public class OverloadingExample {

    @Test
    public void overloadMethodTest() {
        //the passed lambda expression will match the most specific functional interface
        overloadedMethod((x, y) -> x + y);
    }

    private interface IntegerBiFunction extends BinaryOperator<Integer> {

    }

    private void overloadedMethod(BinaryOperator<Integer> lambda) {
        System.out.print("BinaryOperator");
        lambda.apply(1,2);
    }

    private void overloadedMethod(IntegerBiFunction lambda) {
        System.out.print("IntegerBinaryOperator");
        lambda.apply(1,2);
    }
}
