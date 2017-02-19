//
//package cz.fku.generics;
//
//import cz.fku.playground.lambda.Animal;
//import cz.fku.playground.lambda.Dog;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Filip on 27.11.2016.
// http://stackoverflow.com/questions/4343202/difference-between-super-t-and-extends-t-in-java
// Remember PECS: "Producer Extends, Consumer Super".\\
// */
//public class PECS {
//    public static <T> void copy(List<? super T> dest, List<? extends T> src)
//    {
//        for (int i=0; i<src.size(); i++)
//            dest.set(i,src.get(i));
//    }
//
//    @Test
//    public void testAnimalExtend() {
//        List<? extends Animal> producerList = new ArrayList<>();
//        Animal product = producerList.get(0);
//    }
//
//    @Test
//    public void testAnimalSuper() {
//        List<? super Animal> producerList = new ArrayList<>();
//        producerList.add(new Dog());
//    }
//}
