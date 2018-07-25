package by.bogdan.lifetivity.service;

import by.bogdan.lifetivity.model.UserPageData;
import by.bogdan.lifetivity.security.TokenUserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileService {
    UserPageData saveProfilePhoto(MultipartFile file, long userId);

    byte[] getProfilePhoto(long userId);

    String getFileMediaType(String filename);
}
