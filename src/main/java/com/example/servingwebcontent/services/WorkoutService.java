package com.example.servingwebcontent.services;


import com.example.servingwebcontent.models.Workout;

import java.util.List;

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
     */
    void addWorkout(Workout workout); //

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
}