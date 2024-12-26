package org.fitnesstracker.controller;

import org.fitnesstracker.model.WorkoutPlan;
import org.fitnesstracker.service.WorkoutPlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


 class WorkoutPlanControllerTest {

    @Mock
    private WorkoutPlanService workoutPlanService;

    @InjectMocks
    private WorkoutPlanController workoutPlanController;

    private WorkoutPlan workoutPlan;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks( this );
        workoutPlan = new WorkoutPlan();
        workoutPlan.setId(1L);
        workoutPlan.setName("Morning Routine");
      
    }

    @Test
    void testCreateWorkoutPlan() {
        when(workoutPlanService.saveWorkoutPlan(workoutPlan)).thenReturn(workoutPlan);

        WorkoutPlan result = workoutPlanController.createWorkoutPlan(workoutPlan).getBody();
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Morning Routine", result.getName());
      

        verify(workoutPlanService, times(1)).saveWorkoutPlan(workoutPlan);
    }

    @Test
    void testGetWorkoutPlanById() {
        when(workoutPlanService.getWorkoutPlanById(1L)).thenReturn(Optional.of(workoutPlan));

        ResponseEntity<WorkoutPlan> result = workoutPlanController.getWorkoutPlanById( 1L);
       

        verify(workoutPlanService, times(1)).getWorkoutPlanById(1L);
    }

    @Test
    void testGetWorkoutPlanById_NotFound() {
        when(workoutPlanService.getWorkoutPlanById(1L)).thenReturn(Optional.empty());

        ResponseEntity<WorkoutPlan> result = workoutPlanController.getWorkoutPlanById( 1L);
    

        verify(workoutPlanService, times(1)).getWorkoutPlanById(1L);
    }

    @Test
    void testDeleteWorkoutPlan() {
        doNothing().when(workoutPlanService).deleteWorkoutPlan(1L);

        workoutPlanController.deleteWorkoutPlan(1L);
        verify(workoutPlanService, times(1)).deleteWorkoutPlan(1L);
    }

    @Test
    void testGetWorkoutPlans() {
        List<WorkoutPlan> workoutPlans = List.of(workoutPlan);
        when(workoutPlanService.getWorkoutPlans()).thenReturn(workoutPlans);

        List<WorkoutPlan> result = workoutPlanController.getWorkoutPlans().getBody();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Morning Routine", result.get(0).getName());
        
        verify(workoutPlanService, times(1)).getWorkoutPlans();
    }
}
