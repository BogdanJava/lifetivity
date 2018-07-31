package by.bogdan.lifetivity.controller;

import by.bogdan.lifetivity.model.MonthlyStatistics;
import by.bogdan.lifetivity.service.MonthlyStatisticsService;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Map;

// TODO: 31.7.18 Should get methods be secured for current user or not

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monthly")
@Api(description = "Endpoints for working with user's monthly statistics")
public class MonthlyStatisticsController {

    private final MonthlyStatisticsService statsService;

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
            return ResponseEntity.ok(statsService.getById(id).orElseThrow(
                    () -> new NoResultException("There's no entry with userId=" + id)));
        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ImmutableMap.of(
                    "message", e.getMessage()
            ));
        }
    }

    @ApiOperation("Retrieves all MonthlyStatistics objects by userId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All entries by userId field", response = List.class),
            @ApiResponse(code = 404, message = "No entries with such userId found", response = Map.class)
    })
    @GetMapping("/by_user_id")
    public ResponseEntity getByUserId(@RequestParam long userId) {
        val found = statsService.getAllByUserId(userId);
        return found != null ? ResponseEntity.ok(found) : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ImmutableMap.of("message", "No entries with userId=" + userId + " found"));
    }
}
