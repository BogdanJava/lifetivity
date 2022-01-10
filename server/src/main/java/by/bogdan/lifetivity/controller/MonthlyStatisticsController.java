package by.bogdan.lifetivity.controller;

import by.bogdan.lifetivity.model.document.MonthlyStatistics;
import by.bogdan.lifetivity.model.document.StatisticsKind;
import by.bogdan.lifetivity.service.MonthlyModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

// TODO: 31.7.18 Should get methods be secured for current user or not

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/monthly")
@Api("Endpoints for working with user's monthly statistics")
public class MonthlyStatisticsController {

    private final MonthlyModuleService statsService;

    @ApiOperation("Creates new statistics entry")
    @ApiResponse(code = 201, message = "New statistics entry", response = MonthlyStatistics.class)
    @PreAuthorize("#monthlyStatistics.mysqlUserId == authentication.principal.getId() || " +
            "hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MonthlyStatistics addMonthlyStatistics(@RequestBody MonthlyStatistics monthlyStatistics) {
        return statsService.addStatistics(monthlyStatistics);
    }

    @ApiOperation("Retrieves MonthlyStatistics by it's id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Entry by it's id", response = MonthlyStatistics.class),
            @ApiResponse(code = 404, message = "No entry with such id found", response = Map.class)
    })
    @GetMapping("/by_id")
    public ResponseEntity getById(@RequestParam String id) {
        try {
            return ResponseEntity.ok(statsService.getMonthlyStatisticsById(id).orElseThrow(
                    () -> new NoResultException("There's no entry with userId=" + id)));
        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(of(
                    "message", e.getMessage()
            ));
        }
    }

    @ApiOperation("Retrieves MonthlyStatistics by year and month")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Entry by year and month", response = MonthlyStatistics.class),
            @ApiResponse(code = 404, message = "No entry with such date and month found", response = Map.class)
    })
    @GetMapping("/by_date")
    public ResponseEntity getByDate(@NotNull @RequestParam String year, @NotNull @RequestParam String month) {
        MonthlyStatistics stats = statsService.getMonthlyStatisticsByDate(year, month);
        return objectOrNotFoundWithMessage(stats, "No record by date: year=" + year + ";month=" + month + ";");
    }

    @ApiOperation("Retrieves all MonthlyStatistics objects by userId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All entries by userId field", response = List.class),
            @ApiResponse(code = 404, message = "No entries for such userId found", response = Map.class)
    })
    @GetMapping("/by_user_id")
    public ResponseEntity getByUserId(@RequestParam long userId) {
        final var found = statsService.getAllMonthlyStatisticsByUserId(userId);
        return objectOrNotFoundWithMessage(found, "No entries with userId=" + userId + " found");
    }

    @ApiOperation("Retrieves StatisticsKind objects related to particular user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "StatisticsKind objects", response = List.class),
            @ApiResponse(code = 404, message = "No entries for such userId found", response = Map.class)
    })
    @GetMapping("/kinds/{userId}")
    public ResponseEntity getStatisticsKinds(@PathVariable long userId) {
        var found = statsService.getStatisticsKindsByUserId(userId);
        if (found != null && found.size() == 0) found = null;
        return objectOrNotFoundWithMessage(found, "No entries with userId=" + userId + " found");
    }

    @ApiOperation("Saves new StatisticsKind")
    @ApiResponse(code = 201, message = "Create new StatisticsKind", response = StatisticsKind.class)
    @PreAuthorize("#statisticsKind.mysqlUserId == authentication.principal.getId() || hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/kinds")
    public StatisticsKind addNewStatisticsKind(@RequestBody StatisticsKind statisticsKind) {
        return statsService.addStatisticsKind(statisticsKind);
    }

    private ResponseEntity objectOrNotFoundWithMessage(Object object, String notFoundMessage) {
        return object != null ? ResponseEntity.ok(object) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(of("message", notFoundMessage));
    }
}
