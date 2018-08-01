package by.bogdan.lifetivity.service;

import by.bogdan.lifetivity.model.document.MonthlyStatistics;
import by.bogdan.lifetivity.model.document.StatisticsKind;

import java.util.List;
import java.util.Optional;

/**
 * Service that works with MongoDB collection 'monthlyStatistics'
 */
public interface MonthlyModuleService {
    MonthlyStatistics addStatistics(MonthlyStatistics monthlyStatistics);
    List<MonthlyStatistics> getAllMonthlyStatisticsByUserId(long userId);
    Optional<MonthlyStatistics> getMonthlyStatisticsById(String id);
    MonthlyStatistics editStatistics(MonthlyStatistics monthlyStatistics, String id);
    MonthlyStatistics getMonthlyStatisticsByDate(String year, String month);
    void deleteStatistics(String id);
    List<StatisticsKind> getStatisticsKindsByUserId(long userId);
    StatisticsKind addStatisticsKind(StatisticsKind statisticsKind);
}
