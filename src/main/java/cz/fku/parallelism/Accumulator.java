package cz.fku.parallelism;

/**
 * Created by Filip on 23.04.2017.
 */
public class Accumulator {
    public long total = 0;

    public void add(long value) {
        total += value;
    }
}

