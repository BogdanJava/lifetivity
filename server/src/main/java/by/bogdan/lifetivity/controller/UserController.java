package by.bogdan.lifetivity.controller;

import by.bogdan.lifetivity.exception.FileException;
import by.bogdan.lifetivity.model.entity.ContactInfo;
import by.bogdan.lifetivity.model.entity.User;
import by.bogdan.lifetivity.model.entity.UserPageData;
import by.bogdan.lifetivity.repository.mysql.UserPageDataRepository;
import by.bogdan.lifetivity.repository.mysql.UserRepository;
import by.bogdan.lifetivity.security.TokenUserDetails;
import by.bogdan.lifetivity.service.FileService;
import by.bogdan.lifetivity.service.UserService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;

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

    @GetMapping("/page_data/{userId}")
    public ResponseEntity getUserPageData(@PathVariable long userId) {
        return ResponseEntity.ok(userPageDataRepository.getByUserId(userId));
    }

    @GetMapping("/contact_info/{userId}")
    public ResponseEntity getUserContactInfo(@PathVariable long userId) {
        final ContactInfo userContactInfo = userRepository.getUserContactInfo(userId);
        return ResponseEntity.ok(userContactInfo != null ? userContactInfo : new ContactInfo());
    }

    @PreAuthorize("#userId == authentication.principal.getId() || hasRole('ADMIN')")
    @PutMapping("/update_status/{userId}")
    public ResponseEntity updateStatus(@PathVariable long userId,
                                       @RequestParam String status) {
        UserPageData data = userService.updateStatus(userId, status);
        return ResponseEntity.ok(ImmutableMap.of("message", "status changed",
                "success", true, "status", data.getStatus()));
    }

    @PreAuthorize("#userId == authentication.principal.getId() || hasRole('ADMIN')")
    @PutMapping("/update_contact_info/{userId}")
    public ResponseEntity updateContactInfo(@PathVariable long userId,
                                            @RequestBody ContactInfo contactInfo) {
        ContactInfo updatedInfo = this.userService.updateContactInfo(userId, contactInfo);
        return ResponseEntity.ok(updatedInfo);
    }

    @PreAuthorize("#userId == authentication.principal.getId() || hasRole('ADMIN')")
    @PostMapping(value = "/upload_profile_photo",
            consumes = {"multipart/form-data"})
    public ResponseEntity uploadProfilePhoto(@PathVariable long userId,
                                             @RequestPart("file") @Valid @NotNull
                                             @NotBlank MultipartFile file) {
        UserPageData userPageData = fileService.saveProfilePhoto(file, userId);
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
                    new String(Base64.encodeBase64(fileService.getProfilePhoto(userId)), StandardCharsets.UTF_8),
                    "mimeType", contentType));
        } catch (FileException e) {
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

    @PreAuthorize("#userId == authentication.principal.getId() || hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity updateUser(@RequestParam long userId,
                                     @RequestBody @Valid User user) {
        try {
            User updatedUser = this.userService.updateUser(userId, user);
            return ResponseEntity.ok(updatedUser);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ImmutableMap.of(
                    "message", e.getMessage()
            ));
        }
    }

}
