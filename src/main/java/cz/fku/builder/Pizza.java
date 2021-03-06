package cz.fku.builder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {

    public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }

    final Set<Topping> toppings;

//    hierarchical builders (static nested class)
    abstract static class Builder<T extends Builder<T>> { // recursive type parameter
        // default no topping
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        // Subclasses must override this method to return "this"
        // self method, allows method chaining to work properly in subclasses, without the need for casts.
        // This workaround for the fact that Java lacks a self type is known as the simulated self-type idiom.
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }

}
