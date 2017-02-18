package cz.fku.webapp.contoller;

import cz.fku.webapp.model.GreetingTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Filip on 18.02.2017.
 */
@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public GreetingTO greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new GreetingTO(counter.incrementAndGet(),
                String.format(template, name));
    }
}
