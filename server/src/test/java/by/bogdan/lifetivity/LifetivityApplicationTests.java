package by.bogdan.lifetivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LifetivityApplicationTests {

    @LocalServerPort
    private Integer port;
    private String baseUrl;

    @Before
    public void init() {
        this.baseUrl = "http://localhost:" + port + "/api";
    }

    @Test
    public void contextLoads() {
    }

}
