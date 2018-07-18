package by.bogdan.lifetivity.model.dto;

import by.bogdan.lifetivity.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthCredentials {
    @NotNull @Email private String email;
    @NotNull @Size(min = 8) private String password;
    private User user;
}
