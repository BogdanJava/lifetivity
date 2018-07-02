package by.bogdan.lifetivity.controller;

import by.bogdan.lifetivity.exception.EmailAlreadyExistsException;
import by.bogdan.lifetivity.model.User;
import by.bogdan.lifetivity.model.dto.AuthCredentials;
import by.bogdan.lifetivity.payload.AuthResponse;
import by.bogdan.lifetivity.payload.ErrorResponse;
import by.bogdan.lifetivity.service.TokenService;
import by.bogdan.lifetivity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private UserService userService;
    private TokenService tokenService;

    public AuthenticationController(UserService userService,
                                    TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthCredentials authCredentials) {
        try {
            String accessToken = userService.loginUser(authCredentials);
            return ResponseEntity.ok(new AuthResponse(accessToken, "Authentication successful"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody AuthCredentials authCredentials) {
        try {
            User newUser = userService.registerNew(authCredentials);
            return ResponseEntity.ok(new AuthResponse(newUser, "Registered successfully"));
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/check_token_valid")
    public boolean isTokenValid(@RequestBody Map<String, String> body) {
        return tokenService.validateToken(body.get("token"));
    }
}
