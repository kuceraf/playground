package cz.fku.generics.wrapper;

/**
 * Created by Filip on 01.04.2017.
 */

//For a generic type, a wildcard type is what an Object type is for a raw type.
public class WrapperUtil {

    //UNBOUNDED WILDCARD <?>
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

    //UPPER-BOUNDED WILDCARD <? extends T>
//    <? extends T> means anything that is of type T or its subclass is acceptable.
    public static double sum(Wrapper<? extends Number> n1, Wrapper<? extends Number> n2){
        Number num1 = n1.get();
        Number num2 = n2.get();
        double sum = num1.doubleValue() + num2.doubleValue();
        System.out.println("SUM: " + sum);
        return sum;
    }

    //LOWER-BOUNDED WILDCARD <? super T>
//Now you are saying that the dest argument of the copy() method could be either T, same as source,
// or any of its supertype. You can use the copy() method to copy the contents of a Wrapper<String> to a Wrapper<Object>
    public static <T> void copy(Wrapper<T> source, Wrapper<? super T> dest){
        T value = source.get();
        dest.set(value);
    }

    //varargs
    public static void process(Wrapper<Long>...nums) {
        Object[] obj = nums; // Heap pollution
        obj[0] = new Wrapper<String>("Hello"); // Array corruption
        Long lv = nums[0].get(); // A ClassCastException
        // Other code goes here
    }

}
