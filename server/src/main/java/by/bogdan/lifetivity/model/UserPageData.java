package by.bogdan.lifetivity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "page_data")
@Data
@NoArgsConstructor
@ToString(exclude = "user")
public class UserPageData {
    @JsonIgnore @Id @GeneratedValue private long id;
    private String status;
    private String profilePhotoPath;
    @JsonIgnore @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
