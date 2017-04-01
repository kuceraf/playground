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

        //UNBOUNDED WILDCARD
        Wrapper<?> unknownWrapper = new Wrapper<String>("some string");
        //can be assigned only to object or casted sice we do not know what it is (similat to raw type)
        Object o = unknownWrapper.get();
        String s = (String) unknownWrapper.get();
        WrapperUtil.printDetails(unknownWrapper);
        //can set anything except null
        unknownWrapper.set(null);
    }
}
