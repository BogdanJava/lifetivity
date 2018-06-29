package by.bogdan.lifetivity.service;

import by.bogdan.lifetivity.model.User;
import by.bogdan.lifetivity.model.dto.AuthCredentials;

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
}
