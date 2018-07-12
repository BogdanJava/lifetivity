package by.bogdan.lifetivity.service.impl;

import by.bogdan.lifetivity.exception.FileException;
import by.bogdan.lifetivity.model.UserPageData;
import by.bogdan.lifetivity.repository.UserPageDataRepository;
import by.bogdan.lifetivity.security.TokenUserDetails;
import by.bogdan.lifetivity.service.FileService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    private UserPageDataRepository dataRepository;
    private Environment env;

    public FileServiceImpl(UserPageDataRepository dataRepository,
                           Environment env) {
        this.dataRepository = dataRepository;
        this.env = env;
    }

    @Transactional
    @Override
    public UserPageData saveProfilePhoto(MultipartFile file, TokenUserDetails userDetails,
                                         HttpServletRequest request) {
        String baseUrl = request.getServletContext().getRealPath("/");
        String filePath = String.format("%s%d/%s", baseUrl,
                userDetails.getId(), file.getOriginalFilename());
        File fileToSave = new File(filePath);
        try {
            while (fileToSave.exists()) {
                fileToSave = changeFileName(fileToSave);
            }
            File userDirectory = new File(baseUrl + userDetails.getId() + "/");
            if (!userDirectory.exists()) userDirectory.mkdir();
            fileToSave.createNewFile();
            file.transferTo(fileToSave);
            if (fileToSave.exists()) {
                UserPageData data = dataRepository.getByUserId(userDetails.getId());
                data.setProfilePhotoPath(filePath);
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
        } else throw new FileException("File has no type");
    }

    private File changeFileName(File file) {
        String absolutePath = file.getAbsolutePath();
        String directory = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
        String oldFileName = absolutePath.substring(absolutePath.lastIndexOf("/") + 1, absolutePath.length());
        String nameWithoutType = oldFileName.substring(0, oldFileName.lastIndexOf("."));
        String type = oldFileName.substring(oldFileName.lastIndexOf(".") + 1, oldFileName.length());
        int tryCount = 0;
        String newName = "";
        if (!nameWithoutType.equals("")) {
            do {
                newName = nameWithoutType + String.format("(%d)", ++tryCount);
            } while (new File(directory + nameWithoutType + type).exists());
        }
        return new File(directory + newName + type);
    }
}
