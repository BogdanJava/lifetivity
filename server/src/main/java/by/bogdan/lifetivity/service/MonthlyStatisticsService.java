package by.bogdan.lifetivity.service;

import by.bogdan.lifetivity.model.MonthlyStatistics;

import java.util.List;
import java.util.Optional;

/**
 * Service that works with MongoDB collection 'monthlyStatistics'
 */
public interface MonthlyStatisticsService {
    MonthlyStatistics addStatistics(MonthlyStatistics monthlyStatistics);
    List<MonthlyStatistics> getAllByUserId(long userId);
    Optional<MonthlyStatistics> getById(String id);
    MonthlyStatistics editStatistics(MonthlyStatistics monthlyStatistics, String id);
    void deleteStatistics(String id);
}
