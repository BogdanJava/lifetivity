package by.bogdan.lifetivity.service.impl;

import by.bogdan.lifetivity.exception.EmailAlreadyExistsException;
import by.bogdan.lifetivity.model.entity.ContactInfo;
import by.bogdan.lifetivity.model.entity.Role;
import by.bogdan.lifetivity.model.entity.User;
import by.bogdan.lifetivity.model.entity.UserPageData;
import by.bogdan.lifetivity.payload.LoginRequest;
import by.bogdan.lifetivity.repository.mysql.UserPageDataRepository;
import by.bogdan.lifetivity.repository.mysql.UserRepository;
import by.bogdan.lifetivity.service.TokenService;
import by.bogdan.lifetivity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserPageDataRepository userPageDataRepository;

    @Override
    public User registerNew(User user) throws EmailAlreadyExistsException {
        if (!userRepository.existsByEmail(user.getEmail())) {
            user = userRepository.save(setupDefault(user));
            UserPageData userPageData = new UserPageData();
            userPageData.setUser(user);
            userPageDataRepository.save(userPageData);
            return user;
        } else throw new EmailAlreadyExistsException("Email " + user.getEmail() +
                " already exists");
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public String loginUser(LoginRequest loginRequest) throws BadCredentialsException {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            User user = userRepository.findByEmail(loginRequest.getEmail());
            user.setLastLoggedInDateTime(LocalDateTime.now());
            return tokenService.generateToken(authentication);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Incorrect email or password");
        }
    }

    @PreAuthorize("#userId == #user.id")
    @Override
    public User updateUser(long userId, User user) {
        user.setPassword(this.userRepository.getOne(userId).getPassword());
        return this.userRepository.save(user);
    }

    @Override
    public ContactInfo updateContactInfo(long userId, ContactInfo contactInfo) {
        User user = userRepository.getOne(userId);
        user.setContactInfo(contactInfo);
        return userRepository.save(user).getContactInfo();
    }

    @Override
    public UserPageData updateStatus(long userId, String status) {
        UserPageData data = userPageDataRepository.getByUserId(userId);
        data.setStatus(status);
        return userPageDataRepository.save(data);
    }

    private User setupDefault(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setAccountActive(true);
        user.setRegistrationDate(LocalDate.now());
        return user;
    }
}
