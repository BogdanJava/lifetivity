package by.bogdan.lifetivity.service;

import by.bogdan.lifetivity.model.ContactInfo;
import by.bogdan.lifetivity.model.User;
import by.bogdan.lifetivity.model.UserPageData;
import by.bogdan.lifetivity.payload.LoginRequest;

public interface UserService {
    /**
     * Registers a new user
     * @param user new user data
     * @return registered user
     */
    User registerNew(User user);

    /**
     * Logs in existing user
     * @param loginRequest user credentials
     * @return access token
     */
    String loginUser(LoginRequest loginRequest);

    UserPageData updateStatus(long userId, String status);
    User updateUser(long userId, User user);

    ContactInfo updateContactInfo(long userId, ContactInfo contactInfo);
}
