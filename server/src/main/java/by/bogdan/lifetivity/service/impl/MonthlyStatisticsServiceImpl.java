package by.bogdan.lifetivity.service.impl;

import by.bogdan.lifetivity.model.MonthlyStatistics;
import by.bogdan.lifetivity.repository.mongo.MonthlyStatisticsRepository;
import by.bogdan.lifetivity.service.MonthlyStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// TODO: 31.7.18 Research about mongodb transactions

@Slf4j
@Service
@RequiredArgsConstructor
public class MonthlyStatisticsServiceImpl implements MonthlyStatisticsService {

    private final MonthlyStatisticsRepository repo;

    @Override
    public MonthlyStatistics addStatistics(MonthlyStatistics monthlyStatistics) {
        final MonthlyStatistics saved = repo.save(monthlyStatistics);
        log.debug("Saved MonthlyStatistics(id = {})", saved.getId());
        return saved;
    }

    @Override
    public List<MonthlyStatistics> getAllByUserId(long userId) {
        return repo.findAllByUserId(userId);
    }

    @Override
    public Optional<MonthlyStatistics> getById(String id) {
        return repo.findById(id);
    }

    @Override
    public MonthlyStatistics editStatistics(MonthlyStatistics monthlyStatistics, String id) {
        monthlyStatistics.setId(id);
        final MonthlyStatistics updated = repo.save(monthlyStatistics);
        log.debug("Updated MonthlyStatistics(id = {})", id);
        return updated;
    }

    @Override
    public void deleteStatistics(String id) {
        repo.deleteById(id);
        log.debug("Deleted MonthlyStatistics(id = {})", id);
    }
}
