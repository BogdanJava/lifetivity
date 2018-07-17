package by.bogdan.lifetivity;

import by.bogdan.lifetivity.model.Role;
import by.bogdan.lifetivity.model.dto.AuthCredentials;
import by.bogdan.lifetivity.payload.AuthResponse;
import by.bogdan.lifetivity.payload.ErrorResponse;
import by.bogdan.lifetivity.service.TokenService;
import by.bogdan.lifetivity.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationTests {

    private final String email = "lol123@gmail.com";
    private final String password = "123456789";

    @LocalServerPort private Integer port;
    @Autowired private TestRestTemplate restTemplate;
    @Autowired private TokenService tokenService;
    @Autowired private UserService userService;
    private String baseUrl;

    @Before
    public void init() {
        this.baseUrl = "http://localhost:" + port + "/api/auth";
        restTemplate.postForEntity(baseUrl + "/signup",
                new AuthCredentials(email, password), Map.class);
    }

    @Test
    public void registersUser_success() {
        final String email = "bogdanshishkin1998@gmail.com";
        final String password = "123456789";
        final ResponseEntity<Map> response = restTemplate.postForEntity(baseUrl + "/signup",
                new AuthCredentials(email, password), Map.class);
        final Map responseBody = response.getBody();
        final Map user = (Map) responseBody.get("user");
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertNotNull(user);
        assertThat(user.get("email"), is(email));
        assertThat(user.get("accountActive"), is(true));
        assertThat(Role.valueOf((String) user.get("role")), is(Role.USER));
    }

    @Test
    public void registerUserThatAlreadyExists_fail() {
        final ResponseEntity<ErrorResponse> response = restTemplate.postForEntity(baseUrl + "/signup",
                new AuthCredentials(email, password), ErrorResponse.class);
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void getAuthToken_success() {
        final ResponseEntity<AuthResponse> response = restTemplate.postForEntity(baseUrl + "/login",
                new AuthCredentials(email, password), AuthResponse.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        final String token = response.getBody().getToken();
        assertNotNull(token);
        assertTrue(tokenService.validateToken(token));
    }

    @Test
    public void getAuthTokenWithInvalidPassword_fail() {
        final ResponseEntity<ErrorResponse> response = restTemplate.postForEntity(baseUrl + "/login",
                new AuthCredentials(email, "invalid_password"), ErrorResponse.class);
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertEquals(response.getBody().getMessage(), "Incorrect email or password");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void checkTokenValidity() {
        Map requestBody = new HashMap();
        requestBody.put("token", "invalid_token");
        final ResponseEntity<Boolean> responseFailed = restTemplate.postForEntity(baseUrl + "/check_token_valid",
                requestBody, Boolean.class);
        assertThat(responseFailed.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseFailed.getBody(), is(false));

        requestBody.put("token", userService.loginUser(new AuthCredentials(email, password)));
        final ResponseEntity<Boolean> responseSuccess = restTemplate.postForEntity(baseUrl + "/check_token_valid",
                requestBody, Boolean.class);
        assertThat(responseSuccess.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseSuccess.getBody(), is(true));
    }

}
