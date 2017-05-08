package cz.fku.designpattern.strategy;

import cz.fku.desingnpattern.strategy.IsAllLowerCase;
import cz.fku.desingnpattern.strategy.IsNumeric;
import cz.fku.desingnpattern.strategy.Validator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Filip on 08.05.2017.
 */
public class StrategyTest {

    @Test
    public void testStrategy(){
        Validator numericValidator = new Validator(new IsNumeric());
        Assert.assertFalse(numericValidator.validate("aaaaa"));

        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        Assert.assertTrue(lowerCaseValidator.validate("bbbb"));
    }
}
