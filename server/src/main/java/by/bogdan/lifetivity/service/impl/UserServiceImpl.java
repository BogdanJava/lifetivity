package by.bogdan.lifetivity.service.impl;

import by.bogdan.lifetivity.exception.EmailAlreadyExistsException;
import by.bogdan.lifetivity.exception.ForbiddenException;
import by.bogdan.lifetivity.model.ContactInfo;
import by.bogdan.lifetivity.model.Role;
import by.bogdan.lifetivity.model.User;
import by.bogdan.lifetivity.model.UserPageData;
import by.bogdan.lifetivity.model.dto.AuthCredentials;
import by.bogdan.lifetivity.repository.UserPageDataRepository;
import by.bogdan.lifetivity.repository.UserRepository;
import by.bogdan.lifetivity.service.TokenService;
import by.bogdan.lifetivity.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public User registerNew(AuthCredentials authCredentials) throws EmailAlreadyExistsException {
        if (!userRepository.existsByEmail(authCredentials.getEmail())) {
            User user = userRepository.save(getDefaultUser(authCredentials));
            UserPageData userPageData = new UserPageData();
            userPageDataRepository.save(userPageData);
            userPageData.setUser(user);
            return user;
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

    @Override
    public User updateUser(long userId, User user) {
        if (userId != user.getId()) throw new ForbiddenException("Forbidden method invocation");
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

    private User getDefaultUser(AuthCredentials authCredentials) {
        User user = new User();
        user.setEmail(authCredentials.getEmail());
        user.setPassword(passwordEncoder.encode(authCredentials.getPassword()));
        user.setRole(Role.USER);
        user.setAccountActive(true);
        user.setRegistrationDate(LocalDate.now());
        return user;
    }
}
