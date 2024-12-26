package org.fitnesstracker.service;

import org.fitnesstracker.model.WorkoutPlan;
import org.fitnesstracker.repository.WorkoutPlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


 class WorkoutPlanServiceTest {

    @Mock
    private WorkoutPlanRepository workoutPlanRepository;

    @InjectMocks
    private WorkoutPlanService workoutPlanService;

    private WorkoutPlan workoutPlan;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks( this );
        workoutPlan = new WorkoutPlan();
        workoutPlan.setId(1L);
        workoutPlan.setName("Morning Routine");
        workoutPlan.setDescription("A full body workout plan for the morning.");
    }

    @Test
     void testSaveWorkoutPlan() {
        when(workoutPlanRepository.save(workoutPlan)).thenReturn(workoutPlan);

        WorkoutPlan savedPlan = workoutPlanService.saveWorkoutPlan(workoutPlan);

        assertNotNull(savedPlan);
        assertEquals("Morning Routine", savedPlan.getName());
        assertEquals("A full body workout plan for the morning.", savedPlan.getDescription());

        verify(workoutPlanRepository, times(1)).save(workoutPlan);
    }

    @Test
     void testGetWorkoutPlans() {
        List<WorkoutPlan> plans = List.of(workoutPlan);
        when(workoutPlanRepository.findAll()).thenReturn(plans);

        List<WorkoutPlan> result = workoutPlanService.getWorkoutPlans();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Morning Routine", result.get(0).getName());

        verify(workoutPlanRepository, times(1)).findAll();
    }

    @Test
     void testGetWorkoutPlanById() {
        when(workoutPlanRepository.findById(1L)).thenReturn(Optional.of(workoutPlan));

        Optional<WorkoutPlan> result = workoutPlanService.getWorkoutPlanById(1L);

        assertTrue(result.isPresent());
        assertEquals("Morning Routine", result.get().getName());

        verify(workoutPlanRepository, times(1)).findById(1L);
    }

    @Test
     void testDeleteWorkoutPlan() {
        doNothing().when(workoutPlanRepository).deleteById(1L);

        workoutPlanService.deleteWorkoutPlan(1L);

        verify(workoutPlanRepository, times(1)).deleteById(1L);
    }

    @Test
     void testGetWorkoutPlanById_PlanNotFound() {
        when(workoutPlanRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<WorkoutPlan> result = workoutPlanService.getWorkoutPlanById(1L);

        assertFalse(result.isPresent());

        verify(workoutPlanRepository, times(1)).findById(1L);
    }
}
