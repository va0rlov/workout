package com.example.servingwebcontent.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter // Автоматически генерирует геттеры для всех полей
@Setter // Автоматически генерирует сеттеры для всех полей
@ToString // Автоматически генерирует метод toString()
public class Workout {
    private String workoutName;        // Название тренировки
    private String workoutType;        // Тип тренировки
    private int durationInMinutes;     // Продолжительность тренировки в минутах
    private int caloriesBurned;        // Количество сожженных калорий
    private LocalDateTime workoutDate; // Дата и время начала тренировки
    private String notes;              // Дополнительные заметки о тренировке

    // Конструктор с параметрами
    public Workout(String workoutName, String workoutType, int durationInMinutes, int caloriesBurned, LocalDateTime workoutDate, String notes) {
        this.workoutName = workoutName;
        this.workoutType = workoutType;
        this.durationInMinutes = durationInMinutes;
        this.caloriesBurned = caloriesBurned;
        this.workoutDate = workoutDate;
        this.notes = notes;
    }
}
