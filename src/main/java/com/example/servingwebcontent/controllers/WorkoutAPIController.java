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
public class WorkoutAPIController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutAPIController(WorkoutService workoutService) {
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

    /**
     * Updates an existing workout by its ID.
     *
     * @param id             the ID of the workout to update
     * @param updatedWorkout the updated workout data
     * @return the updated workout
     */
    @PutMapping("/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable UUID id, @RequestBody Workout updatedWorkout) {
        Workout workout = workoutService.updateWorkout(id, updatedWorkout);
        if (workout != null) {
            return ResponseEntity.ok(workout); // Возвращаем 200 OK и обновленную тренировку
        } else {
            return ResponseEntity.notFound().build(); // Возвращаем 404 Not Found, если тренировка не найдена
        }
    }

    /**
     * Deletes a workout by its ID.
     *
     * @param id the ID of the workout to delete
     * @return 204 No Content if the workout was deleted, or 404 Not Found if the workout was not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable UUID id) {
        boolean deleted = workoutService.deleteWorkout(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Возвращаем 204 No Content, если тренировка удалена
        } else {
            return ResponseEntity.notFound().build(); // Возвращаем 404 Not Found, если тренировка не найдена
        }
    }

}