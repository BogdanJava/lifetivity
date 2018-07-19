package by.bogdan.lifetivity.controller;

import by.bogdan.lifetivity.exception.EmailAlreadyExistsException;
import by.bogdan.lifetivity.model.User;
import by.bogdan.lifetivity.model.dto.AuthCredentials;
import by.bogdan.lifetivity.payload.AuthResponse;
import by.bogdan.lifetivity.payload.ErrorResponse;
import by.bogdan.lifetivity.service.TokenService;
import by.bogdan.lifetivity.service.UserService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

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
            User user = authCredentials.getUser();
            user.setPassword(authCredentials.getPassword());
            if (user.getPassword() == null || user.getPassword().equals("")) {
                return ResponseEntity.badRequest().body(ImmutableMap.of(
                        "message", "Password is not valid or null"
                ));
            }
            User newUser = userService.registerNew(user);
            return ResponseEntity.ok(new AuthResponse(newUser, "Registered successfully"));
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }
}
