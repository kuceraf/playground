package cz.fku.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @SpringBootApplication=
 @Configuration tags the class as a source of bean definitions for the application context.
 @EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans,
 and various property settings.
 Normally you would add @EnableWebMvc for a Spring MVC app,
 but Spring Boot adds it automatically when it sees spring-webmvc on the classpath.
 This flags the application as a web application and activates key behaviors such as setting up
 a DispatcherServlet.
 @ComponentScan tells Spring to look for other components, configurations,
 and services in the the hello package, allowing it to find the controllers.*
 * Created by Filip on 18.02.2017.
 */


@EnableWebMvc
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
