package by.bogdan.lifetivity.service.impl;

import by.bogdan.lifetivity.exception.EmailAlreadyExistsException;
import by.bogdan.lifetivity.model.Role;
import by.bogdan.lifetivity.model.User;
import by.bogdan.lifetivity.model.dto.AuthCredentials;
import by.bogdan.lifetivity.repository.UserRepository;
import by.bogdan.lifetivity.service.TokenService;
import by.bogdan.lifetivity.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private AuthenticationManager authManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           TokenService tokenService,
                           AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.authManager = authManager;
    }

    @Override
    public User registerNew(AuthCredentials authCredentials) throws EmailAlreadyExistsException {
        if (!userRepository.existsByEmail(authCredentials.getEmail())) {
            User user = new User();
            user.setEmail(authCredentials.getEmail());
            user.setPassword(passwordEncoder.encode(authCredentials.getPassword()));
            user.setRole(Role.USER);
            user.setAccountActive(true);
            user.setRegistrationDate(LocalDate.now());
            return userRepository.save(user);
        } else throw new EmailAlreadyExistsException("Email " + authCredentials.getEmail() +
                " already exists");
    }

    @Override
    public String loginUser(AuthCredentials authCredentials) throws BadCredentialsException {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authCredentials.getEmail(), authCredentials.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = userRepository.findByEmail(authCredentials.getEmail());
            user.setLastLoggedInDateTime(LocalDateTime.now());
            return tokenService.generateToken(authentication);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Incorrect email or password");
        }
    }
}
