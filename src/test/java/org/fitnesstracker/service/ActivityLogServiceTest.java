package org.fitnesstracker.service;

import org.fitnesstracker.model.ActivityLog;
import org.fitnesstracker.repository.ActivityLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


 class ActivityLogServiceTest {

    @Mock
    private ActivityLogRepository activityLogRepository;

    @InjectMocks
    private ActivityLogService activityLogService;

    private ActivityLog activityLog;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks( this );
        activityLog = new ActivityLog();
        activityLog.setId(1L);

        activityLog.setDuration(30);
    }

    @Test
     void testSaveActivityLog() {
        when(activityLogRepository.save(activityLog)).thenReturn(activityLog);

        ActivityLog savedLog = activityLogService.saveActivityLog(activityLog);

        assertNotNull(savedLog);

        assertEquals(30, savedLog.getDuration());

        verify(activityLogRepository, times(1)).save(activityLog);
    }

    @Test
     void testGetActivityLogs() {
        List<ActivityLog> logs = List.of(activityLog);
        when(activityLogRepository.findAll()).thenReturn(logs);

        List<ActivityLog> result = activityLogService.getActivityLogs();

        assertNotNull(result);
        assertEquals(1, result.size());


        verify(activityLogRepository, times(1)).findAll();
    }

    @Test
     void testGetActivityLogById() {
        when(activityLogRepository.findById(1L)).thenReturn(Optional.of(activityLog));

        Optional<ActivityLog> result = activityLogService.getActivityLogById(1L);

        assertTrue(result.isPresent());


        verify(activityLogRepository, times(1)).findById(1L);
    }

    @Test
     void testDeleteActivityLog() {
        doNothing().when(activityLogRepository).deleteById(1L);

        activityLogService.deleteActivityLog(1L);

        verify(activityLogRepository, times(1)).deleteById(1L);
    }

    @Test
     void testGetActivityLogById_LogNotFound() {
        when(activityLogRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ActivityLog> result = activityLogService.getActivityLogById(1L);

        assertFalse(result.isPresent());

        verify(activityLogRepository, times(1)).findById(1L);
    }
}
