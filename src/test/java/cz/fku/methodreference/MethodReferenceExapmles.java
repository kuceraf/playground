package cz.fku.methodreference;

import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;

/**
 * Created by Filip on 03.12.2016.
 */
public class MethodReferenceExapmles {
    @Test
    public void intstanceMethodReferenceTest(){
        List<String> fruits = Arrays.asList("Apple", "Banana");
        // with lambda expression
        fruits.forEach((name) -> System.out.println(name));

        // with method reference
        fruits.forEach(System.out::println);
    }

    //instance method reference with no instance
    @Test
    public void methodRefetenceTest() {
        String[] names = {"Alexis", "anna", "Kyleen"};

        Arrays.sort(names, String::compareToIgnoreCase);
        for (String name : names) {
            System.out.println(name);
        }
    }

    //Reference to a constructor
    @Test
    public void methodReferenceTest2() {
        Integer[] array = {1, 8, 5};
        Collection<Integer> col1
                = arrayToCollection(ArrayList<Integer>::new, array);
        System.out.println("Natural order");
        col1.forEach(System.out::println);
        System.out.println("=======================");
        System.out.println("Ascending order");
        Collection<Integer> col2
                //the same as: () -> new ArrayList<Integer>()
                = arrayToCollection(HashSet<Integer>::new, array);
        col2.forEach(System.out::println);
    }

    private static Collection<Integer> arrayToCollection(
            //supplier is functional interface, which just return thing (T)
            Supplier<Collection<Integer>> supplier, Integer[]
            numbers) {
        Collection<Integer> collection = supplier.get();
        for (int i : numbers) {
            collection.add(i);
        }
        return collection;
    }
}

