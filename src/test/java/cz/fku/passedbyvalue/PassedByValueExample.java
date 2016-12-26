package cz.fku.passedbyvalue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Filip on 26.12.2016.
 */
public class PassedByValueExample {
    private class Dog {
        private String name;

        public Dog(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void testPassedByValue() {
        Dog aDog = new Dog("Rex");

        changeObjectReference(aDog);
        Assert.assertEquals("Rex", aDog.getName());

        changeReferencedObject(aDog);
        Assert.assertEquals("Azor", aDog.getName());
    }

    //object reference is passed by value
    //new object reference is created (new variable d, that points to the same object)
    //if the reference is changed, the original is not
    private void changeObjectReference(Dog d) {
        Assert.assertEquals("Rex", d.getName());
        d = new Dog("Azor");
        Assert.assertEquals("Azor", d.getName());
    }

    //both object references (the original one and the one created when a method is called)
    // point to the same object
    private void changeReferencedObject(Dog d) {
        Assert.assertEquals("Rex", d.getName());
        d.setName("Azor");
        Assert.assertEquals("Azor", d.getName());
    }
}
