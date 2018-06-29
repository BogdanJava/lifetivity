package by.bogdan.lifetivity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.TABLE) private Long id;
    @Column(name = "first_name") private String firstName;
    @Column(name = "last_name") private String lastName;
    private String email;
    @JsonIgnore private String password;
    @Column(name = "birthday_date") private LocalDate birthdayDate;
    @Column(name = "registration_date") private LocalDate registrationDate;
    @Column(name = "last_logged_in") private LocalDateTime lastLoggedInDateTime;
    @Embedded private ContactInfo contactInfo;
    private Role role;
    @Column(name = "active") private boolean isAccountActive;
}
