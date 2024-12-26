package org.fitnesstracker.service;

import lombok.RequiredArgsConstructor;
import org.fitnesstracker.model.WorkoutPlan;
import org.fitnesstracker.repository.WorkoutPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutPlanService
{
    private final WorkoutPlanRepository workoutPlanRepository;

    public WorkoutPlan saveWorkoutPlan( WorkoutPlan plan )
    {
        return workoutPlanRepository.save( plan );
    }

    public List<WorkoutPlan> getWorkoutPlans()
    {
        return workoutPlanRepository.findAll();
    }

    public Optional<WorkoutPlan> getWorkoutPlanById( Long id )
    {
        return workoutPlanRepository.findById( id );
    }

    public void deleteWorkoutPlan( Long id )
    {
        workoutPlanRepository.deleteById( id );
    }
}
