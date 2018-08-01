package by.bogdan.lifetivity.service.impl;

import by.bogdan.lifetivity.model.document.MonthlyStatistics;
import by.bogdan.lifetivity.model.document.StatisticsKind;
import by.bogdan.lifetivity.repository.mongo.MonthlyStatisticsRepository;
import by.bogdan.lifetivity.repository.mongo.StatisticsKindRepository;
import by.bogdan.lifetivity.service.MonthlyModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// TODO: 31.7.18 Research about mongodb transactions

@Slf4j
@Service
@RequiredArgsConstructor
public class MonthlyModuleServiceImpl implements MonthlyModuleService {

    private final MonthlyStatisticsRepository statsRepo;
    private final StatisticsKindRepository kindsRepo;

    @Override
    public MonthlyStatistics addStatistics(MonthlyStatistics monthlyStatistics) {
        final MonthlyStatistics saved = statsRepo.save(monthlyStatistics);
        log.debug("Saved MonthlyStatistics(id = {})", saved.getId());
        return saved;
    }

    @Override
    public List<MonthlyStatistics> getAllMonthlyStatisticsByUserId(long userId) {
        return statsRepo.getAllByMysqlUserId(userId);
    }

    @Override
    public Optional<MonthlyStatistics> getMonthlyStatisticsById(String id) {
        return statsRepo.findById(id);
    }

    @Override
    public MonthlyStatistics editStatistics(MonthlyStatistics monthlyStatistics, String id) {
        monthlyStatistics.setId(id);
        final MonthlyStatistics updated = statsRepo.save(monthlyStatistics);
        log.debug("Updated MonthlyStatistics(id = {})", id);
        return updated;
    }

    @Override
    public MonthlyStatistics getMonthlyStatisticsByDate(String year, String month) {
        return statsRepo.getByYearAndMonth(year, month);
    }

    @Override
    public void deleteStatistics(String id) {
        statsRepo.deleteById(id);
        log.debug("Deleted MonthlyStatistics(id = {})", id);
    }

    @Override
    public List<StatisticsKind> getStatisticsKindsByUserId(long userId) {
        return kindsRepo.getAllByMysqlUserId(userId);
    }

    @Override
    public StatisticsKind addStatisticsKind(StatisticsKind statisticsKind) {
        return kindsRepo.save(statisticsKind);
    }
}
