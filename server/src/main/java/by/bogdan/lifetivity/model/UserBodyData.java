package by.bogdan.lifetivity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "body_data")
@Data
@NoArgsConstructor
@ToString(exclude = "user")
public class UserBodyData {
    @Id @GeneratedValue private long id;
    @NotNull private Float height;
    @NotNull private Float weight;
    @Size(max = 512) private String periodDescription;
    @Column(name = "stamp_date") private LocalDate stampDate;

    @JsonIgnore @ManyToOne private User user;
}
