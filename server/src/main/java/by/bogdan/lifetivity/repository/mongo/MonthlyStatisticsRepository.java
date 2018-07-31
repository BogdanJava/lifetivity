package by.bogdan.lifetivity.repository.mongo;

import by.bogdan.lifetivity.model.MonthlyStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface MonthlyStatisticsRepository extends MongoRepository<MonthlyStatistics, String> {

    @Query("{ 'mysqlUserId': ?0 }")
    @Nullable
    List<MonthlyStatistics> findAllByUserId(long mysqlUserId);
}
