package cz.fku.generics.wrapper;

/**
 * Created by Filip on 01.04.2017.
 */
public class Wrapper<T> {
    private T ref;

    public Wrapper(T ref) {
        this.ref = ref;
    }

    public T get() {
        return ref;
    }

    public void set(T ref) {
        this.ref = ref;
    }
}
