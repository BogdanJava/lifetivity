package by.bogdan.lifetivity.payload;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Simple error wrapper")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
}
