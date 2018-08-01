package by.bogdan.lifetivity.payload;

import by.bogdan.lifetivity.model.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Wrapper for signup request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    @Valid private User user;
    @NotNull @Size(min = 8) private String password;

}
