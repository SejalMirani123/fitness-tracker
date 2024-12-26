package org.fitnesstracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fitnesstracker.model.WorkoutPlan;
import org.fitnesstracker.service.WorkoutPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/workout" )
@RequiredArgsConstructor
public class WorkoutPlanController
{
    private final WorkoutPlanService workoutPlanService;

    @PostMapping
    public ResponseEntity<WorkoutPlan> createWorkoutPlan( @Valid @RequestBody WorkoutPlan plan )
    {
        return new ResponseEntity<>( workoutPlanService.saveWorkoutPlan( plan ), HttpStatus.CREATED );
    }

    @GetMapping
    public ResponseEntity<List<WorkoutPlan>> getWorkoutPlans()
    {
        return ResponseEntity.ok( workoutPlanService.getWorkoutPlans() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<WorkoutPlan> getWorkoutPlanById( @PathVariable Long id )
    {
        return workoutPlanService.getWorkoutPlanById( id )
                                 .map( ResponseEntity::ok )
                                 .orElse( ResponseEntity.notFound().build() );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Void> deleteWorkoutPlan( @PathVariable Long id )
    {
        workoutPlanService.deleteWorkoutPlan( id );
        return ResponseEntity.noContent().build();
    }
}