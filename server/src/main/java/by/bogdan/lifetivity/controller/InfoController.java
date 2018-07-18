package by.bogdan.lifetivity.controller;

import by.bogdan.lifetivity.repository.UserRepository;
import by.bogdan.lifetivity.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
public class InfoController {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @PostMapping("/check_token_valid")
    public boolean isTokenValid(@RequestBody Map<String, String> body) {
        return tokenService.validateToken(body.get("token"));
    }

    @PostMapping("/check_email_reserved")
    public boolean isEmailReserved(@RequestBody Map<String, String> body) {
        return userRepository.findByEmail(body.get("email")) != null;
    }
}
