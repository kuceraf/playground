package cz.fku.desingnpattern.templatemethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Filip on 08.05.2017.
 */
public class Database {
    private static Map<Integer,Customer> databaseStore = new HashMap(){{
        put(1,new Customer(1,"Pepa"));
        put(2,new Customer(2,"Honza"));
        put(3,new Customer(3,"Eda"));
    }};

    public static Customer getCustomerWithId(int id){
        return databaseStore.get(id);
    }
}
