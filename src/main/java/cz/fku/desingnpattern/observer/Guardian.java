package cz.fku.desingnpattern.observer;

/**
 * Created by Filip on 08.05.2017.
 */

//this class is here just for wrapping a behavior (code in notify)
public class Guardian implements Observer {
    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("queen")){
            System.out.println("Yet another news in London... " + tweet);
        }
    }
}
