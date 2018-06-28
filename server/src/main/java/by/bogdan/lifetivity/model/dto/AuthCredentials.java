package by.bogdan.lifetivity.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthCredentials {
    private String email;
    private String password;
}
