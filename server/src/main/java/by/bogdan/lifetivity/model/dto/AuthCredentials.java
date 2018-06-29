package by.bogdan.lifetivity.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AuthCredentials {
    @NotNull @Email private String email;
    @NotNull @Size(min = 8) private String password;
}
