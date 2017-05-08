package cz.fku.desingnpattern.observer;

/**
 * Created by Filip on 08.05.2017.
 */
public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
