package by.bogdan.lifetivity.controller;

import by.bogdan.lifetivity.model.dto.AuthCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthCredentials authCredentials) {
        System.out.println(authCredentials);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody AuthCredentials authCredentials) {
        System.out.println(authCredentials);
        return ResponseEntity.ok().build();
    }
}
