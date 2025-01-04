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
     * Deletes a workout by its ID.
     *
     * @param id the ID of the workout to delete
     * @return true if the workout was deleted, false if the workout was not found
     */
    boolean deleteWorkout(UUID id); // Удалить тренировку по ID

    /**
     * Returns a workout by its ID.
     *
     * @param id the ID of the workout to retrieve
     * @return the workout with the specified ID, or null if not found
     */
    Workout getWorkoutById(UUID id); // Получить тренировку по ID

    /**
     * Updates an existing workout by its ID.
     *
     * @param id             the ID of the workout to update
     * @param updatedWorkout the updated workout data
     * @return the updated workout, or null if the workout was not found
     */
    Workout updateWorkout(UUID id, Workout updatedWorkout); // Обновить тренировку по ID
}