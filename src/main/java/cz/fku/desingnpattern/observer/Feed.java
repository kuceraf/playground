package cz.fku.desingnpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 08.05.2017.
 */
public class Feed implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
