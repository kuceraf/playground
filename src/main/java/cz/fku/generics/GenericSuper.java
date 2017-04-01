package cz.fku.generics;

import cz.fku.trader.Trader;

/**
 * Created by Filip on 01.04.2017.
 */
public class GenericSuper<T> {
    public void m1(T a) {
        // Code goes here
    }

    public <P extends Trader> void m2(P a) {
        // Code goes here
    }
}
