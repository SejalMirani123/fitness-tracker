package org.fitnesstracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fitnesstracker.model.ActivityLog;
import org.fitnesstracker.service.ActivityLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityLogController {
    private final ActivityLogService activityLogService;

    @Operation(summary = "Create a new activity log", description = "Creates a new activity log and returns it.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Activity log created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActivityLog.class))), @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)})
    @PostMapping
    public ResponseEntity<ActivityLog> createActivityLog(@Valid @RequestBody ActivityLog activityLog) {
        return new ResponseEntity<>(activityLogService.saveActivityLog(activityLog), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all activity logs", description = "Fetches all activity logs.")
    @GetMapping
    public ResponseEntity<List<ActivityLog>> getActivityLogs() {
        return ResponseEntity.ok(activityLogService.getActivityLogs());
    }

    @Operation(summary = "Get activity log by ID", description = "Fetches an activity log by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ActivityLog> getActivityLogById(@PathVariable Long id) {
        return activityLogService.getActivityLogById(id)
                                 .map(ResponseEntity::ok)
                                 .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an activity log", description = "Deletes an activity log by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivityLog(@PathVariable Long id) {
        activityLogService.deleteActivityLog(id);
        return ResponseEntity.noContent().build();
    }
}
