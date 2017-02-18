package cz.fku.webapp.resttemplate;

import cz.fku.webapp.model.GreetingTO;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static cz.fku.webapp.ApiURL.API_GREETING;

/**
 * Created by Filip on 18.02.2017.
 */
public class ApiTest {
    private static final Logger log = Logger.getLogger(ApiTest.class);

    private static final String PROTOCOL = "http://";
    private static final String SERVER = "localhost:8080";

    private URI uri;

    @Before
    public void setUp(){
        try {
            uri = new URI(PROTOCOL+SERVER+API_GREETING);
        } catch (URISyntaxException e) {
            log.error("Can not construct URI",e);
        }
    }

    @Test
    public void greetingTest(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GreetingTO> response = restTemplate.getForEntity(uri, GreetingTO.class);
    }
}
