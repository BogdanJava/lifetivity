package by.bogdan.lifetivity.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@Embeddable
public class ContactInfo {
    @Pattern(regexp = "[a-zA-Z]{3,20}") private String country;
    @Pattern(regexp = "[a-zA-Z]{3,20}") private String city;
    @URL private String website;
    @Column(name = "skype") private String skypeAccount;
    @Column(name = "phone_number") private String phoneNumber;
    @Column(name = "additional_phone") private String additionalPhone;
}
