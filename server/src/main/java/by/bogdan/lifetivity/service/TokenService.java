package by.bogdan.lifetivity.service;

import org.springframework.security.core.Authentication;

public interface TokenService {
    String generateToken(Authentication authentication);
    boolean validateToken(String token);
    long getUserIdFromToken(String token);
}
