package org.fitnesstracker.controller;

import org.fitnesstracker.model.ActivityLog;
import org.fitnesstracker.service.ActivityLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class ActivityLogControllerTest {

    @Mock
    private ActivityLogService activityLogService;

    @InjectMocks
    private ActivityLogController activityLogController;

    private ActivityLog activityLog;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks( this );
        activityLog = new ActivityLog();
        activityLog.setId(1L);
        activityLog.setActivityType("Running");
        activityLog.setDuration(30);

    }

    @Test
    void testCreateActivityLog() {
        when(activityLogService.saveActivityLog(activityLog)).thenReturn(activityLog);

        ActivityLog result = activityLogController.createActivityLog(activityLog).getBody();
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Running", result.getActivityType());
        assertEquals(30, result.getDuration());

        verify(activityLogService, times(1)).saveActivityLog(activityLog);
    }

    @Test
    void testGetActivityLogById() {
        when(activityLogService.getActivityLogById(1L)).thenReturn(Optional.of(activityLog));
        ResponseEntity<ActivityLog> result = activityLogController.getActivityLogById( 1L);
        verify(activityLogService, times(1)).getActivityLogById(1L);
    }

    @Test
    void testGetActivityLogById_NotFound() {
        when(activityLogService.getActivityLogById(1L)).thenReturn(Optional.empty());

        ResponseEntity<ActivityLog> result = activityLogController.getActivityLogById( 1L);


        verify(activityLogService, times(1)).getActivityLogById(1L);
    }

    @Test
    void testDeleteActivityLog() {
        doNothing().when(activityLogService).deleteActivityLog(1L);

        activityLogController.deleteActivityLog(1L);
        verify(activityLogService, times(1)).deleteActivityLog(1L);
    }

    @Test
    void testGetActivityLogs() {
        List<ActivityLog> activityLogs = List.of(activityLog);
        when(activityLogService.getActivityLogs()).thenReturn(activityLogs);

        List<ActivityLog> result = activityLogController.getActivityLogs().getBody();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.getFirst().getId());
        assertEquals("Running", result.getFirst().getActivityType());
        assertEquals(30, result.getFirst().getDuration());


        verify(activityLogService, times(1)).getActivityLogs();
    }
}
