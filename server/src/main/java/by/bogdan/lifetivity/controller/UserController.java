package by.bogdan.lifetivity.controller;

import by.bogdan.lifetivity.model.UserPageData;
import by.bogdan.lifetivity.repository.UserPageDataRepository;
import by.bogdan.lifetivity.repository.UserRepository;
import by.bogdan.lifetivity.security.TokenUserDetails;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserRepository userRepository;
    private UserPageDataRepository userPageDataRepository;

    public UserController(UserRepository userRepository,
                          UserPageDataRepository userPageDataRepository) {
        this.userRepository = userRepository;
        this.userPageDataRepository = userPageDataRepository;
    }

    @GetMapping("/me")
    public ResponseEntity getUserByToken(@AuthenticationPrincipal TokenUserDetails currentUser) {
        return ResponseEntity.ok(userRepository.findByEmail(currentUser.getUsername()));
    }

    @GetMapping("/page_data")
    public ResponseEntity getUserPageData(@AuthenticationPrincipal TokenUserDetails currentUser) {
        return ResponseEntity.ok(userPageDataRepository.getUserPageData(currentUser.getId()));
    }

    @PutMapping("/update_status")
    public ResponseEntity updateStatus(@AuthenticationPrincipal TokenUserDetails currentUser,
                                       @RequestParam String status) {
        UserPageData data = userPageDataRepository.getByUserId(currentUser.getId());
        data.setStatus(status);
        return ResponseEntity.ok(ImmutableMap.of("message", "status changed",
                "success", true));
    }

}

