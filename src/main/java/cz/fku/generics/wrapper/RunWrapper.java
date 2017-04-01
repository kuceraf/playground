package cz.fku.generics.wrapper;

import cz.fku.generics.c.C1;
import cz.fku.generics.c.C2;

/**
 * Created by Filip on 01.04.2017.
 *
 * from book: Beginning Java 8 Language Features
 */
public class RunWrapper {
    public static void main(String[] args) {
        Wrapper<String> stringWrapper = new Wrapper<>("some string");
        Wrapper<Integer> integerWrapper = new Wrapper<>(2);

        //RAW TYPE - compiler shows warning during compilation. DO NOT USE IT
        Wrapper rawWrapper = new Wrapper("some string");
        String string = (String) rawWrapper.get();
        rawWrapper.set(3);
        Integer integer = (Integer)rawWrapper.get();

        //UNBOUNDED WILDCARD <?>
        Wrapper<?> unknownWrapper = new Wrapper<String>("some string");
        //can be assigned only to object or casted sice we do not know what it is (similat to raw type)
        Object o = unknownWrapper.get();
        String s = (String) unknownWrapper.get();
        WrapperUtil.printDetails(unknownWrapper);
        //can set anything except null
        unknownWrapper.set(null);

        //UPPER-BOUNDED WILDCARD <? extends T>
        Wrapper<? extends Number> numberWrapperInt = new Wrapper<Integer>(1);
        Wrapper<? extends Number> numberWrapperLong = new Wrapper<Long>(1L);
        Number number1 = numberWrapperLong.get();
        Number number2 = numberWrapperLong.get();
        WrapperUtil.sum(numberWrapperLong,numberWrapperLong);
//        intWrapper = numberWrapperInt;
        numberWrapperLong=integerWrapper;
        //can get but cannot set
        //When you try to use the set() method on numberWrapper, the compiler starts complaining
        //because it cannot make sure at compile time that numberWrapper is a type of Integer or Double,
        //which are subtypes of a Number.
//        numberWrapperInt.set(1L);
//        numberWrapperInt.set(1);
    }
}
