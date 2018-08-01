package by.bogdan.lifetivity.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class StatisticsKind {
    @Id private String id;
    private String name;
    private String measure;
    private long mysqlUserId;
}
