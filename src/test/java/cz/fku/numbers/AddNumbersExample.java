package cz.fku.numbers;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static org.junit.Assert.*;
/**
 * Created by Filip on 22.01.2017.
 */

public class AddNumbersExample {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(AddNumbersExample.class);

    //0.1 + 0.2 is 0.30000000000000004 not 0.3
    @Test
    public void add01To02() {
        //floating point number are defined by IEEE 754 (Standard for Binary Floating-Point Arithmetic)
        //float 32-bit floating point number
        //double 64-bit floating point number

        float aFloat = 0.1f;
        float bFloat = 0.2f;
        float resultFloat = aFloat+bFloat;

        double aDoulbe = 0.1;
        double bDoulbe = 0.2;
        double resultDouble = aDoulbe + bDoulbe;
        System.out.println(resultDouble);

        //assertEquals(0.1f + 0.2f , 0.3f, 0f);
        assertEquals(0.1 + 0.2 , 0.3, 0.2 - 0.1);
        System.out.println(0.2 - 0.1);
        assertNotEquals(0.1 + 0.2 , 0.3, 0);
    }

    @Test
    public void bigDecimalTest() {
        //always use string to crate BigDecimal
        assertNotEquals(new BigDecimal("1.3"), new BigDecimal(1.3));
        System.out.println(new BigDecimal("1.3")); //accurately craated big decimal
        System.out.println(new BigDecimal(1.3)); //crate big decimal from double - not accurate

        //big decimal are immutable (like String)
        BigDecimal firstAmount = new BigDecimal("3.55");
        //scale = how many digits are after decimal point
        BigDecimal secontAmount = firstAmount.setScale(1,BigDecimal.ROUND_UP); //rounding strategy
        assertNotEquals(new BigDecimal("3.6"),firstAmount);
        assertEquals(new BigDecimal("3.6"),secontAmount);


    }
}
