package cz.fku.other;

/**
 * Created by Filip on 27.11.2016.
 */

@FunctionalInterface
public interface Calculator {

    double calculate(int a, int b);

    public default int subtract(int a, int b) {
        return a - b;
    }

    public default int add(int a, int b) {
        return a * b;
    }

    @Override
    public String toString();
}

