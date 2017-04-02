package cz.fku.generics;

import com.sun.istack.internal.NotNull;
import cz.fku.generics.c.C1;
import cz.fku.generics.c.C2;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Created by Filip on 01.04.2017.
 */
public class RunC {
    @Test
    public void runC(){
        C1 c = new C2();
        c.m1(10);  // Which method is called - C1.m() or C2.m2()?

//        annotationTest(null);
    }

    public static void annotationTest(@NotNull String s){
        assert s != null;
        Assert.notNull(s);
        s.concat("aaa");
    }
}
