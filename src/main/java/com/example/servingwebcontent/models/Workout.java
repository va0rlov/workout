package com.example.servingwebcontent.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record Workout(UUID id, // Уникальный идентификатор тренировки
                      String workoutName, // Название тренировки
                      String workoutType, // Тип тренировки
                      int durationInMinutes, // Продолжительность тренировки в минутах
                      int caloriesBurned, // Количество сожженных калорий
                      LocalDateTime workoutDate, // Дата и время тренировки
                      String notes // Дополнительные заметки
) {
    // Конструктор для создания новой тренировки с автоматической генерацией UUID
    public Workout(String workoutName, String workoutType, int durationInMinutes, int caloriesBurned, LocalDateTime workoutDate, String notes) {
        this(UUID.randomUUID(), workoutName, workoutType, durationInMinutes, caloriesBurned, workoutDate, notes);
    }
}