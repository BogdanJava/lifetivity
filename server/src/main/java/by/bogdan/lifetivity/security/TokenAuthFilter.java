package by.bogdan.lifetivity.security;

import by.bogdan.lifetivity.repository.UserRepository;
import by.bogdan.lifetivity.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthFilter extends OncePerRequestFilter {

    private UserRepository userRepository;
    private TokenService tokenService;

    public TokenAuthFilter(UserRepository userRepository,
                           TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        String token = getTokenFromRequest(req);

        if (StringUtils.hasText(token) && tokenService.validateToken(token)) {
            TokenUserDetails userDetails =
                    new TokenUserDetails(userRepository.getOne(tokenService.getUserIdFromToken(token)));
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(req, res);
    }

    private String getTokenFromRequest(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        } else return null;
    }
}
