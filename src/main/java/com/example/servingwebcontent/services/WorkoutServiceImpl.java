package com.example.servingwebcontent.services;

import com.example.servingwebcontent.models.Workout;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private static final String JSON_FILE = "data/workouts.json"; // Относительный путь в resources
    private final ObjectMapper objectMapper;

    public WorkoutServiceImpl() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Поддержка LocalDateTime
    }

    @Override
    public void saveWorkouts(List<Workout> workouts) {
        try {
            File file = new ClassPathResource(JSON_FILE).getFile(); // Получаем файл из resources
            objectMapper.writeValue(file, workouts);
        } catch (IOException e) {
            System.err.println("Ошибка при записи файла: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Workout> loadWorkouts() {
        try {
            File file = new ClassPathResource(JSON_FILE).getFile(); // Получаем файл из resources
            if (file.exists()) {
                System.out.println("Файл найден: " + file.getAbsolutePath());
                Workout[] workoutsArray = objectMapper.readValue(file, Workout[].class);
                return new ArrayList<>(Arrays.asList(workoutsArray));
            } else {
                System.out.println("Файл не найден: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void addWorkout(Workout workout) {
        List<Workout> workouts = loadWorkouts();
        workouts.add(workout);
        saveWorkouts(workouts);
    }

    @Override
    public List<Workout> getAllWorkouts() {
        return loadWorkouts();
    }

    @Override
    public void deleteWorkout(int index) {
        List<Workout> workouts = loadWorkouts();
        if (index >= 0 && index < workouts.size()) {
            workouts.remove(index);
            saveWorkouts(workouts);
        }
    }
}