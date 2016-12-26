package cz.fku.java8;

import org.junit.Test;
import org.slf4j.LoggerFactory;

/**
 * Created by Filip on 25.12.2016.
 */
public class DefaultMethodExample {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultMethodExample.class);

    public interface Parent{
        void printMessage(String message);
        default void welcome(){
            final  org.slf4j.Logger logger = LoggerFactory.getLogger(Parent.class);
            logger.info("Parent default");
            printMessage("Parent: Hi!");
        }
    }

    public interface Child extends Parent{
        default void welcome(){
            final  org.slf4j.Logger logger = LoggerFactory.getLogger(Child.class);
            logger.info("Child default override");
            printMessage("Child: Hi!");
        }
    }

    public class ParentImpl implements  Parent{
        final private org.slf4j.Logger logger = LoggerFactory.getLogger(ParentImpl.class);
        @Override
        public void printMessage(String message) {
            logger.info(message);
        }
    }

    public class ChildImpl implements  Child {
        final private org.slf4j.Logger logger = LoggerFactory.getLogger(ChildImpl.class);
        @Override
        public void printMessage(String message) {
            logger.info(message);
        }
    }

    @Test
    public void testDefaultMethod() {
        Parent child = new ChildImpl();
        child.welcome();

        Parent parent = new ParentImpl();
        parent.welcome();
    }
}
