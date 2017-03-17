package cz.fku.java8.function;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Filip on 13.03.2017.
 */

//Creating the function shaped x->(y->z) from the function shaped (x,y)->z is called currying.
public class CurryingTest {

    //Implicit Partial Function Application of String Concatenation
    @Test
    public void testImplicitCurrying() {
        BiFunction<String, String, String> concat = (a,b) -> a + b;
        greetFolks(whom -> concat.apply("Hello, ", whom));
    }

    private static void greetFolks(Function<String, String> greeter) {
        for (String name : Arrays.asList("Alice", "Bob", "Cathy")) {
            System.out.println(greeter.apply(name));
        }
    }

    //Explicit Partial Function Application of String Concatenation
    @Test
    public void testExplicitCurrying() {
        BiFunction<String, String, String> concat = (a,b) -> a + b;
        greetFolks(applyPartial(concat, "Hello, "));
    }

    public static <T,U,V> Function<U,V> applyPartial(
            BiFunction<T,U,V> bif, T firstArgument
    ) {
        return u -> bif.apply(firstArgument, u);
    }




    @Test
    public void testCurrying() {
        BiFunction<String, String, String> concat = (a, b) -> a + b;
        Function<String, Function<String, String>> curriedConcat = curry(concat);
        for (String greetings : Arrays.asList("Hello", "Guten Tag", "Bonjour")) {
            greetFolks(curriedConcat.apply(greetings + ", "));
        }
    }

    private static <T, U, V> Function<T, Function<U, V>> curry(BiFunction<T, U, V> bif) {
        return t -> (u -> bif.apply(t, u));
    }

}
