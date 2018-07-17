package by.bogdan.lifetivity.service;

import by.bogdan.lifetivity.model.ContactInfo;
import by.bogdan.lifetivity.model.User;
import by.bogdan.lifetivity.model.UserPageData;
import by.bogdan.lifetivity.model.dto.AuthCredentials;
import by.bogdan.lifetivity.security.TokenUserDetails;

/**
 * Used in {@link by.bogdan.lifetivity.controller.AuthenticationController}
 */
public interface UserService {
    /**
     * Registers a new user
     * @param authCredentials new user credentials
     * @return registered user
     */
    User registerNew(AuthCredentials authCredentials);

    /**
     * Logs in existing user
     * @param authCredentials user credentials
     * @return access token
     */
    String loginUser(AuthCredentials authCredentials);

    UserPageData updateStatus(long userId, String status);
    User updateUser(long userId, User user);

    ContactInfo updateContactInfo(long userId, ContactInfo contactInfo);
}
