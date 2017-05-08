package cz.fku.desingnpattern.templatemethod;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by Filip on 08.05.2017.
 */
public class OnlineBankingLambda {

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }
}
