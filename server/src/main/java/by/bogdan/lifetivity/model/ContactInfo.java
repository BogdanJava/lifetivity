package by.bogdan.lifetivity.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
public class ContactInfo {
    private String country;
    private String city;
    @Column(name = "phone_number") private String phoneNumber;
}
