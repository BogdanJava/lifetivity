package by.bogdan.lifetivity.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
public class MonthlyStatistics {
    @Id private String id;
    private long mysqlUserId;
    /**
     * Contains statistics for particular date.
     * Structure is {@link StatisticsKind#name} against the value
     */
    private Map<String, Object> statistics;
    private String monthDescription;
    private String month;
    private String year;
}
