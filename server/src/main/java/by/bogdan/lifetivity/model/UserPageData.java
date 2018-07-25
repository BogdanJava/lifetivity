package by.bogdan.lifetivity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "page_data")
@Data
@NoArgsConstructor
@ToString(exclude = "user")
public class UserPageData {
    @JsonIgnore @Id @GeneratedValue private long id;
    @Size(max = 255) private String status;
    @JsonIgnore private String profilePhotoPath;
    @JsonIgnore @OneToOne
    private User user;
}
