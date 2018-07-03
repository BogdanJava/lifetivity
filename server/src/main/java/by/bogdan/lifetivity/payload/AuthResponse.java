package by.bogdan.lifetivity.payload;

import by.bogdan.lifetivity.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
