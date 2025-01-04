package com.example.servingwebcontent.controllers;


import com.example.servingwebcontent.models.Workout;
import com.example.servingwebcontent.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    /**
     * Returns a list of all workouts from the JSON file.
     *
     * @return a list of all workouts from the JSON file
     */
    @GetMapping
    public List<Workout> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    /**
     * Returns a single workout by its ID.
     *
     * @param id the ID of the workout to retrieve
     * @return the workout with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable UUID id) {
        Workout workout = workoutService.getWorkoutById(id);
        if (workout != null) {
            return ResponseEntity.ok(workout); // Возвращаем 200 OK и тренировку
        } else {
            return ResponseEntity.notFound().build(); // Возвращаем 404 Not Found, если тренировка не найдена
        }
    }

    /**
     * Creates a new workout.
     *
     * @param workout the workout data to create
     * @return the created workout with its ID
     */
    @PostMapping
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {
        Workout createdWorkout = workoutService.addWorkout(workout);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout); // Возвращаем 201 Created и созданную тренировку
    }
}