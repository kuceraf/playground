package cz.fku.java8.function;

import org.junit.Test;

import java.util.function.Function;

/**
 * Created by Filip on 12.03.2017.
 */

/*
* The term closure refers to a later innovation of the lambda.
* For Java developers, it is easiest to think of the term closure as meaning encapsulating function.
* In the same way that an object can encapsulate state and expose it through a particular API
* (such as properties exposing fields), a closure can also encapsulate some state and then act on that state wherever
* and whenever the closure is invoked
*/
public class ClusureTest {

    public interface Greeter {
        String createGreeting(String whom);
    }
    //---------------------BEGIN Anonymous function closure
    @Test
    public void testClusureInAnnonymousFunction() {

        final String greeting = "Hello, ";
        Greeter greeterInstance = new Greeter() {
            @Override
            public String createGreeting(String whom) {
                // Close (ie: capture) the variable here
                //greeting = "A"; --cannot be changed
                return greeting + whom + "!";
            }
        };

        //TEST
        greetWorld(greeterInstance);
    }

    private static void greetWorld(Greeter greeter) {
        // Have the greeter use the closed variable here
        // Note that the "greeting" variable is out of scope
        System.out.println(greeter.createGreeting("World"));
    }
    //---------------------END Anonymous function closure

    //---------------------BEGIN Lambda closure
    @Test
    public void testLambdaClosure() {
        String greeting = "Hello, ";
        Function<String, String> greeter = whom -> greeting + whom + "!";
        greetWorld(greeter);
    }

    private static void greetWorld(Function<String, String> greeter) {
        System.out.println(greeter.apply("World"));
    }
    //---------------------END Lambda closure

}
