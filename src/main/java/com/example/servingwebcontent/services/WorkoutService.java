package com.example.servingwebcontent.services;


import com.example.servingwebcontent.models.Workout;

import java.util.List;

public interface WorkoutService {
    void saveWorkouts(List<Workout> workouts); // Сохранить список тренировок

    List<Workout> loadWorkouts(); // Загрузить список тренировок

    void addWorkout(Workout workout); // Добавить новую тренировку

    List<Workout> getAllWorkouts(); // Получить все тренировки

    void deleteWorkout(int index); // Удалить тренировку по индексу
}