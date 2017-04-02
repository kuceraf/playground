package cz.fku.generics.c;

import com.sun.istack.internal.NotNull;
import org.springframework.util.Assert;

/**
 * Created by Filip on 01.04.2017.
 */
public class RunC {
    public static void main(String[] args){
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
