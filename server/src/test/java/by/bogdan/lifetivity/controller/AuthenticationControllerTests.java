package by.bogdan.lifetivity.controller;

import by.bogdan.lifetivity.model.entity.ContactInfo;
import by.bogdan.lifetivity.model.entity.Role;
import by.bogdan.lifetivity.model.entity.User;
import by.bogdan.lifetivity.payload.AuthResponse;
import by.bogdan.lifetivity.payload.ErrorResponse;
import by.bogdan.lifetivity.payload.LoginRequest;
import by.bogdan.lifetivity.payload.SignupRequest;
import by.bogdan.lifetivity.service.TokenService;
import by.bogdan.lifetivity.service.UserService;
import lombok.val;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationControllerTests {

    private final String email = "lol123@gmail.com";
    private final String password = "123456789";
    private final User mainUser = randomUser(email);
    @Value("${jwt.secret}") String secretKey;
    @LocalServerPort private Integer port;
    @Autowired private TestRestTemplate restTemplate;
    @Autowired private TokenService tokenService;
    @Autowired private UserService userService;
    private String baseAuthUrl;
    private String baseInfoUrl;
    private boolean userCreated = false;

    @Before
    public void init() {
        this.baseAuthUrl = "http://localhost:" + port + "/api/auth";
        this.baseInfoUrl = "http://localhost:" + port + "/api/info";
        if (!userCreated) {
            restTemplate.postForEntity(baseAuthUrl + "/signup",
                    new SignupRequest(mainUser, password), Map.class);
            userCreated = true;
        }
    }

    @Test
    public void registersUser_success() {
        val email = "bogdanshishkin1998@gmail.com";
        val password = "123456789";
        val newUser = randomUser(email);
        val response = restTemplate.postForEntity(baseAuthUrl + "/signup",
                new SignupRequest(newUser, password), Map.class);
        val responseBody = response.getBody();
        val user = (Map) responseBody.get("user");
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertNotNull(user);
        assertThat(user.get("email"), is(email));
        assertThat(user.get("accountActive"), is(true));
        assertThat(Role.valueOf((String) user.get("role")), is(Role.USER));
    }

    @Test
    public void registerUserThatAlreadyExists_fail() {
        final ResponseEntity<ErrorResponse> response = restTemplate.postForEntity(baseAuthUrl + "/signup",
                new SignupRequest(mainUser, password), ErrorResponse.class);
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void getAuthToken_success() {
        final ResponseEntity<AuthResponse> response = restTemplate.postForEntity(baseAuthUrl + "/login",
                new LoginRequest(email, password), AuthResponse.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        final String token = response.getBody().getToken();
        assertNotNull(token);
        assertTrue(tokenService.validateToken(token));
    }

    @Test
    public void getAuthTokenWithInvalidPassword_fail() {
        final ResponseEntity<ErrorResponse> response = restTemplate.postForEntity(baseAuthUrl + "/login",
                new LoginRequest(email, "invalid_password"), ErrorResponse.class);
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertEquals(response.getBody().getMessage(), "Incorrect email or password");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void checkTokenValidity() {
        Map requestBody = new HashMap();
        requestBody.put("token", "invalid_token");
        val responseFailed = restTemplate.postForEntity(baseInfoUrl + "/check_token_valid",
                requestBody, Object.class);
        assertThat(responseFailed.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseFailed.getBody(), is(false));
        requestBody.put("token", userService.loginUser(new LoginRequest(email, password)));
        final ResponseEntity<Boolean> responseSuccess = restTemplate.postForEntity(baseInfoUrl + "/check_token_valid",
                requestBody, Boolean.class);
        assertThat(responseSuccess.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseSuccess.getBody(), is(true));
    }

    private User randomUser(String email) {
        val user = new User();
        user.setEmail(email);
        user.setAccountActive(true);
        val contactInfo = new ContactInfo();
        contactInfo.setCity(RandomString.make(5));
        contactInfo.setCountry(RandomString.make(5));
        user.setContactInfo(contactInfo);
        user.setBirthdayDate(LocalDate.of(1998, 1, 6));
        user.setFirstName(RandomString.make(10));
        user.setLastName(RandomString.make(10));
        user.setLastLoggedInDateTime(LocalDateTime.now());
        user.setRegistrationDate(LocalDate.now());
        return user;
    }
}