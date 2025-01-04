package com.example.servingwebcontent.models;

import java.time.LocalDateTime;

public record Workout(
        String workoutName, // Название тренировки
        String workoutType, // Тип тренировки
        int durationInMinutes, // Продолжительность тренировки в минутах
        int caloriesBurned, // Количество сожженных калорий
        LocalDateTime workoutDate, // Дата и время тренировки
        String notes // Дополнительные заметки
) {}