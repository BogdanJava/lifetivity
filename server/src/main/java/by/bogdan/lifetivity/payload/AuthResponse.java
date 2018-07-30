package by.bogdan.lifetivity.payload;

import by.bogdan.lifetivity.model.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Response for authentication or signup requests")
@Data
public class AuthResponse {
    private User user;
    private String message;
    private String token;

    public AuthResponse(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }
}
