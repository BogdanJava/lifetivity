package by.bogdan.lifetivity.service.impl;

import by.bogdan.lifetivity.exception.FileException;
import by.bogdan.lifetivity.model.UserPageData;
import by.bogdan.lifetivity.repository.UserPageDataRepository;
import by.bogdan.lifetivity.security.TokenUserDetails;
import by.bogdan.lifetivity.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final UserPageDataRepository dataRepository;
    private final Environment env;

    @Transactional
    @Override
    public UserPageData saveProfilePhoto(MultipartFile file, long userId,
                                         HttpServletRequest request) {
        String baseUrl = "/uploads/";
        String filePath = String.format("%s%d/%s", baseUrl,
                userId, file.getOriginalFilename());
        File fileToSave = changeFileName(new File(filePath));
        try {
            while (fileToSave.exists()) {
                fileToSave = changeFileName(fileToSave);
            }
            File userDirectory = new File(baseUrl + userId + "/");
            if (!userDirectory.exists()) userDirectory.mkdir();
            fileToSave.createNewFile();
            file.transferTo(fileToSave);
            if (fileToSave.exists()) {
                UserPageData data = dataRepository.getByUserId(userId);
                data.setProfilePhotoPath(fileToSave.getAbsolutePath());
                return dataRepository.save(data);
            } else {
                throw new FileException("Error while uploading file");
            }
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    @Transactional
    @Override
    public byte[] getProfilePhoto(long userId) {
        UserPageData data = dataRepository.getByUserId(userId);
        File profilePhoto = new File(data.getProfilePhotoPath());
        try {
            if (profilePhoto.exists()) {
                return FileCopyUtils.copyToByteArray(profilePhoto);
            } else {
                throw new FileException("Profile photo not found");
            }
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    @Override
    public String getFileMediaType(String filename) {
        String fileType = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        String contentType = env.getProperty(fileType);
        if (!contentType.equals("")) {
            return contentType;
        } else return env.getProperty("jpg");
    }

    private File changeFileName(File file) {
        String absolutePath = file.getAbsolutePath();
        String directory = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
        String oldFileName = absolutePath.substring(absolutePath.lastIndexOf("/") + 1, absolutePath.length());
        String nameWithoutType = "avatar";
        String type = oldFileName.substring(oldFileName.lastIndexOf(".") + 1, oldFileName.length());
        int tryCount = 0;
        String newPath;
        do {
            newPath = directory + File.separator + nameWithoutType + String.format("(%d).%s", ++tryCount, type);
        } while (new File(newPath).exists());

        return new File(newPath);
    }
}
