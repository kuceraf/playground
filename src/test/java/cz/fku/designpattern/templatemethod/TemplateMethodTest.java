package cz.fku.designpattern.templatemethod;

import cz.fku.desingnpattern.templatemethod.Customer;
import cz.fku.desingnpattern.templatemethod.OnlineBankingDiscountImpl;
import cz.fku.desingnpattern.templatemethod.OnlineBankingLambda;
import cz.fku.desingnpattern.templatemethod.OnlineBankingPointsImpl;
import org.junit.Test;
import org.slf4j.LoggerFactory;

/**
 * Created by Filip on 08.05.2017.
 */
public class TemplateMethodTest {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(OnlineBankingDiscountImpl.class);

    @Test
    public void testTemplateMethod() {

        OnlineBankingDiscountImpl onlineBankingDiscount = new OnlineBankingDiscountImpl();
        OnlineBankingPointsImpl onlineBankingPoints = new OnlineBankingPointsImpl();

        onlineBankingDiscount.processCustomer(1);
        onlineBankingPoints.processCustomer(1);

        //The different components of the algorithms you want to plug in can be represented by lambda expressions
        //or method references.
        OnlineBankingLambda onlineBankingLambda = new OnlineBankingLambda();
        onlineBankingLambda.processCustomer(1, onlineBankingDiscount::makeCustomerHappy);
        onlineBankingLambda.processCustomer(1, (Customer c) -> logger.info("Customer process from lambda: " + c.getName() ));
    }

}
