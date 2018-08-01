package by.bogdan.lifetivity.repository.mongo;

import by.bogdan.lifetivity.model.document.StatisticsKind;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.Nullable;

import java.util.List;

public interface StatisticsKindRepository extends MongoRepository<StatisticsKind, String> {
    @Nullable
    List<StatisticsKind> getAllByMysqlUserId(long mysqlUserId);
}
