package by.bogdan.lifetivity.controller;

import by.bogdan.lifetivity.exception.EmailAlreadyExistsException;
import by.bogdan.lifetivity.model.entity.User;
import by.bogdan.lifetivity.payload.LoginRequest;
import by.bogdan.lifetivity.payload.AuthResponse;
import by.bogdan.lifetivity.payload.ErrorResponse;
import by.bogdan.lifetivity.payload.SignupRequest;
import by.bogdan.lifetivity.service.UserService;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "authentication", description = "Encapsulates authentication&authorization logic")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @ApiOperation(value = "Retrieving access token")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Authentication successful", response = AuthResponse.class),
            @ApiResponse(code = 400, message = "Bad request(bad credentials, etc.)", response = ErrorResponse.class)
    })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try {
            String accessToken = userService.loginUser(loginRequest);
            return ResponseEntity.ok(new AuthResponse(accessToken, "Authentication successful"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }


    @ApiOperation(value = "Creates new user", response = AuthResponse.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Account created", response = AuthResponse.class),
            @ApiResponse(code = 400, message = "Bad request(Incorrect account data, etc.)", response = ErrorResponse.class)
    })
    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody SignupRequest signupRequest) {
        try {
            User user = signupRequest.getUser();
            user.setPassword(signupRequest.getPassword());
            if (user.getPassword() == null || user.getPassword().equals("")) {
                return ResponseEntity.badRequest().body(ImmutableMap.of(
                        "message", "Password is not valid or null"
                ));
            }
            User newUser = userService.registerNew(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(newUser, "Registered successfully"));
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }
}
