package by.bogdan.lifetivity.controller;

import by.bogdan.lifetivity.repository.mysql.UserRepository;
import by.bogdan.lifetivity.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api("Consists of help methods(i.e. if token valid)")
@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @ApiOperation("Checks if token valid")
    @ApiResponse(code = 200, response = Boolean.class, message = "'true' or 'false'")
    @PostMapping("/check_token_valid")
    public boolean isTokenValid(@RequestBody Map<String, String> body) {
        return tokenService.validateToken(body.get("token"));
    }

    @ApiOperation("Checks if user with such email already exists")
    @ApiResponses({
            @ApiResponse(code = 200, response = Boolean.class, message = "'true'"),
            @ApiResponse(code = 404, response = Boolean.class, message = "'false'")
    })
    @PostMapping("/check_email_reserved")
    public ResponseEntity<Boolean> isEmailReserved(@RequestBody Map<String, String> body) {
        final boolean exists = userRepository.existsByEmail(body.get("email"));
        return exists ? ResponseEntity.ok().body(true) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
