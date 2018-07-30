package by.bogdan.lifetivity.payload;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Wrapper for login request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotNull private String email;
    @NotNull private String password;
}
