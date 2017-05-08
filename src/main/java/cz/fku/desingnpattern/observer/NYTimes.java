package cz.fku.desingnpattern.observer;

/**
 * Created by Filip on 08.05.2017.
 */
public class NYTimes implements Observer  {
    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("money")){
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}
