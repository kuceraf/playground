package cz.fku.desingnpattern.templatemethod;

import org.slf4j.LoggerFactory;

/**
 * Created by Filip on 08.05.2017.
 */
public class OnlineBankingDiscountImpl extends OnlineBanking {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(OnlineBankingDiscountImpl.class);

    @Override
    public void makeCustomerHappy(Customer c) {
        logger.info("Customer " + c.getName() + " has got a discount!");
    }
}
