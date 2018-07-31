package by.bogdan.lifetivity.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
public class MonthlyStatistics {
    @Id private String id;
    private long mysqlUserId;
    private Map<String, Object> statistics;
    private String monthDescription;
}
