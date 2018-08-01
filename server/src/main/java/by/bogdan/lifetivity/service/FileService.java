package by.bogdan.lifetivity.service;

import by.bogdan.lifetivity.model.entity.UserPageData;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    UserPageData saveProfilePhoto(MultipartFile file, long userId);

    byte[] getProfilePhoto(long userId);

    String getFileMediaType(String filename);
}
