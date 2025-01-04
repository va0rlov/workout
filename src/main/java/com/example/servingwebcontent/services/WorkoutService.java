package com.example.servingwebcontent.services;


import com.example.servingwebcontent.models.Workout;

import java.util.List;
import java.util.UUID;

public interface WorkoutService {
    /**
     * Saves the given list of workouts to the JSON file.
     *
     * @param workouts the list of workouts to save
     */
    void saveWorkouts(List<Workout> workouts); // Сохранить список тренировок

    List<Workout> loadWorkouts(); // Загрузить список тренировок

    /**
     * Adds a new workout to the list of workouts.
     *
     * @param workout the new workout to add
     * @return the added workout
     */
    Workout addWorkout(Workout workout); //

    /**
     * Returns a list of all workouts.
     *
     * @return a list of all workouts
     */
    List<Workout> getAllWorkouts(); //

    /**
     * Deletes a workout from the list of workouts at the given index.
     *
     * @param index the index of the workout to delete
     */
    void deleteWorkout(int index); //

    /**
     * Returns a workout by its ID.
     *
     * @param id the ID of the workout to retrieve
     * @return the workout with the specified ID, or null if not found
     */
    Workout getWorkoutById(UUID id); // Получить тренировку по ID
}