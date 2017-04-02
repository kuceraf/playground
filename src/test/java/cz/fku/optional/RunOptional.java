package cz.fku.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * Created by Filip on 02.04.2017.
 */
public class RunOptional {
    @Test
    public void optionalTets(){
        Optional<String> stringOpt = Optional.ofNullable("aaa");

        stringOpt.orElse("");
    }
}
