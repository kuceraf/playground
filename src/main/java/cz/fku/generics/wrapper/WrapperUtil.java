package cz.fku.generics.wrapper;

/**
 * Created by Filip on 01.04.2017.
 */

//For a generic type, a wildcard type is what an Object type is for a raw type.
public class WrapperUtil {
    public static void printDetails(Wrapper<?> wrapper) {
        // Can assign get() return value to Object
        Object value = wrapper.get();
        String className = null;

        if (value != null) {
            className = value.getClass().getName();
        }

        System.out.println("Class: " + className);
        System.out.println("Value: " + value);
    }
}
