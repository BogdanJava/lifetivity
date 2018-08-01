package by.bogdan.lifetivity.repository.mongo;

import by.bogdan.lifetivity.model.document.MonthlyStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.Nullable;

import java.util.List;

public interface MonthlyStatisticsRepository extends MongoRepository<MonthlyStatistics, String> {

    @Nullable
    List<MonthlyStatistics> getAllByMysqlUserId(long mysqlUserId);

    @Nullable
    MonthlyStatistics getByYearAndMonth(String year, String month);
}
