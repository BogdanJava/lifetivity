package by.bogdan.lifetivity.service.impl;

import by.bogdan.lifetivity.security.TokenUserDetails;
import by.bogdan.lifetivity.service.TokenService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expirationTimeMillis}")
    private long expirationTimeMillis;

    @Override
    public String generateToken(Authentication authentication) {
        TokenUserDetails principal = (TokenUserDetails) authentication.getPrincipal();
        Date expDate = new Date(new Date().getTime() + expirationTimeMillis);
        return Jwts.builder()
                .setSubject(Long.toString(principal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parse(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            logger.error("Token expired");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            logger.error("Token is null or empty");
        }
        return false;
    }

    @Override
    public long getUserIdFromToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return Long.parseLong(claims.getBody().getSubject());
    }
}
