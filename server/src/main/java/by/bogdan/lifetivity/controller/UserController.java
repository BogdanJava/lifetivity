package by.bogdan.lifetivity.controller;

import by.bogdan.lifetivity.exception.FileException;
import by.bogdan.lifetivity.exception.ForbiddenException;
import by.bogdan.lifetivity.model.ContactInfo;
import by.bogdan.lifetivity.model.User;
import by.bogdan.lifetivity.model.UserPageData;
import by.bogdan.lifetivity.repository.UserPageDataRepository;
import by.bogdan.lifetivity.repository.UserRepository;
import by.bogdan.lifetivity.security.TokenUserDetails;
import by.bogdan.lifetivity.service.FileService;
import by.bogdan.lifetivity.service.UserService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserPageDataRepository userPageDataRepository;
    private final UserService userService;
    private final FileService fileService;

    @GetMapping("/me")
    public ResponseEntity getUserByToken(@AuthenticationPrincipal TokenUserDetails currentUser) {
        return ResponseEntity.ok(userRepository.findByEmail(currentUser.getUsername()));
    }

    @GetMapping("/page_data")
    public ResponseEntity getUserPageData(@AuthenticationPrincipal TokenUserDetails currentUser) {
        return ResponseEntity.ok(userPageDataRepository.getByUserId(currentUser.getId()));
    }

    @GetMapping("/contact_info")
    public ResponseEntity getUserContactInfo(@AuthenticationPrincipal TokenUserDetails currentUser) {
        final ContactInfo userContactInfo = userRepository.getUserContactInfo(currentUser.getId());
        return ResponseEntity.ok(userContactInfo != null ? userContactInfo : new ContactInfo());
    }

    @PutMapping("/update_status")
    public ResponseEntity updateStatus(@AuthenticationPrincipal TokenUserDetails currentUser,
                                       @RequestParam String status) {
        UserPageData data = userService.updateStatus(currentUser.getId(), status);
        return ResponseEntity.ok(ImmutableMap.of("message", "status changed",
                "success", true, "status", data.getStatus()));
    }

    @PutMapping("/update_contact_info")
    public ResponseEntity updateContactInfo(@AuthenticationPrincipal TokenUserDetails currentUser,
                                            @RequestBody ContactInfo contactInfo) {
        ContactInfo updatedInfo = this.userService.updateContactInfo(currentUser.getId(), contactInfo);
        return ResponseEntity.ok(updatedInfo);
    }

    @PostMapping(value = "/upload_profile_photo",
            consumes = {"multipart/form-data"})
    public ResponseEntity uploadProfilePhoto(@AuthenticationPrincipal TokenUserDetails currentUser,
                                             @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,
                                             HttpServletRequest request) {
        UserPageData userPageData = fileService.saveProfilePhoto(file, currentUser.getId(), request);
        return ResponseEntity.ok(ImmutableMap.of("success", true, "userPageData", userPageData));
    }

    @GetMapping(value = "/profile_photo", produces = {
            "application/json"
    })
    public ResponseEntity getProfilePhoto(@RequestParam("id") long userId,
                                          HttpServletResponse response) {
        try {
            String contentType = fileService.getFileMediaType(
                    userPageDataRepository.getByUserId(userId).getProfilePhotoPath());
            response.setContentType(contentType);
            return ResponseEntity.ok(ImmutableMap.of("file",
                    new String(Base64.encodeBase64(fileService.getProfilePhoto(userId)), "UTF-8"),
                    "mimeType", contentType));
        } catch (FileException | UnsupportedEncodingException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/is_photo_present")
    public ResponseEntity isPhotoPresent(@RequestParam("id") long userId) {
        UserPageData data = this.userPageDataRepository.getByUserId(userId);
        final String path = data.getProfilePhotoPath();
        return path != null && !path.equals("") ?
                ResponseEntity.ok().body(ImmutableMap.of("status", 200)) :
                ResponseEntity.status(404).body(ImmutableMap.of("status", 404));
    }

    @PutMapping
    public ResponseEntity updateUser(@AuthenticationPrincipal TokenUserDetails currentUser,
                                     @RequestBody @Valid User user) {
        try {
            User updatedUser = this.userService.updateUser(currentUser.getId(), user);
            return ResponseEntity.ok(updatedUser);
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ImmutableMap.of(
                    "message", e.getMessage()
            ));
        }
    }

}

