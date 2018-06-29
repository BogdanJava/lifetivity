package by.bogdan.lifetivity.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailAlreadyExistsException extends RuntimeException {
    @Getter private String email;

    public EmailAlreadyExistsException(String email) {
        super(email);
        this.email = email;
    }
}
