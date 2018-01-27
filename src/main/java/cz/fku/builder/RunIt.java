package cz.fku.builder;

import java.util.EnumSet;

public class RunIt {
    public static void main(String[] args) {
        // instantiation of static nested class - the builder - and then building the desired class
        NyPizza nyPizza = new NyPizza.Builder(NyPizza.Size.MEDIUM)
                .addTopping(Pizza.Topping.HAM)
                .build();
    }
}
