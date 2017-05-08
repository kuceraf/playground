package cz.fku.desingnpattern.templatemethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Filip on 08.05.2017.
 */
public abstract class OnlineBanking {

    public void processCustomer(int id){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }
    abstract void makeCustomerHappy(Customer c);
}
