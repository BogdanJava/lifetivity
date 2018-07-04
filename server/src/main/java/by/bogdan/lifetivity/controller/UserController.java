package by.bogdan.lifetivity.controller;

import by.bogdan.lifetivity.repository.UserRepository;
import by.bogdan.lifetivity.security.TokenUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity getUserByToken(@AuthenticationPrincipal TokenUserDetails currentUser) {
        return ResponseEntity.ok(userRepository.findByEmail(currentUser.getUsername()));
    }
}

