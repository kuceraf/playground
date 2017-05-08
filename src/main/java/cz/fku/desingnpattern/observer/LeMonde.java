package cz.fku.desingnpattern.observer;

/**
 * Created by Filip on 08.05.2017.
 */
public class LeMonde implements Observer {
    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("wine")){
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}
