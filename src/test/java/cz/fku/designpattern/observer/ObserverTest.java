package cz.fku.designpattern.observer;

import cz.fku.desingnpattern.observer.Feed;
import cz.fku.desingnpattern.observer.Guardian;
import cz.fku.desingnpattern.observer.LeMonde;
import cz.fku.desingnpattern.observer.NYTimes;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Filip on 08.05.2017.
 */
public class ObserverTest {

    @Test
    public void testObserver(){
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObservers("The queen said: blah!");

        //WITH LAMBDA
        f.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("money")){
                System.out.println("Breaking news in NY! " + tweet);
            }
        });

        f.getObservers().get(3);
    }
}
