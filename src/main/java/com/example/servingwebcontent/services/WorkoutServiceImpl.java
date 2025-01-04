package com.example.servingwebcontent.services;

import com.example.servingwebcontent.models.Workout;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private static final String JSON_FILE_PATH = "src/main/resources/data/workouts.json"; // Абсолютный путь к файлу
    private final ObjectMapper objectMapper;

    public WorkoutServiceImpl() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Поддержка LocalDateTime
    }

    @Override
    public void saveWorkouts(List<Workout> workouts) {
        try {
            File file = Paths.get(JSON_FILE_PATH).toFile(); // Получаем файл по абсолютному пути
            objectMapper.writeValue(file, workouts);
        } catch (IOException e) {
            System.err.println("Ошибка при записи файла: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Workout> loadWorkouts() {
        try {
            File file = Paths.get(JSON_FILE_PATH).toFile(); // Получаем файл по абсолютному пути
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
    public Workout addWorkout(Workout workout) {
        // Создаем новую тренировку с автоматически сгенерированным UUID
        Workout newWorkout = new Workout(workout.workoutName(), workout.workoutType(), workout.durationInMinutes(), workout.caloriesBurned(), workout.workoutDate(), workout.notes());

        // Загружаем текущий список тренировок
        List<Workout> workouts = loadWorkouts();
        // Добавляем новую тренировку
        workouts.add(newWorkout);
        // Сохраняем обновленный список в файл
        saveWorkouts(workouts);

        return newWorkout; // Возвращаем созданную тренировку
    }

    @Override
    public List<Workout> getAllWorkouts() {
        return loadWorkouts();
    }

    @Override
    public Workout getWorkoutById(UUID id) {
        List<Workout> workouts = loadWorkouts();
        return workouts.stream().filter(workout -> workout.id().equals(id)) // Ищем тренировку по ID
                .findFirst() // Возвращаем первую найденную
                .orElse(null); // Если не найдено, возвращаем null
    }

    @Override
    public Workout updateWorkout(UUID id, Workout updatedWorkout) {
        List<Workout> workouts = loadWorkouts();
        Workout existingWorkout = getWorkoutById(id); // Используем существующий метод для поиска тренировки

        if (existingWorkout != null) {
            // Обновляем поля с помощью рефлексии
            Workout newWorkout = copyNonNullFields(existingWorkout, updatedWorkout);

            // Заменяем старую тренировку на обновленную
            workouts.set(workouts.indexOf(existingWorkout), newWorkout);
            saveWorkouts(workouts); // Сохраняем обновленный список в файл
            return newWorkout; // Возвращаем обновленную тренировку
        }

        return null; // Если тренировка не найдена, возвращаем null
    }

    /**
     * Копирует не-null поля из updatedWorkout в existingWorkout.
     *
     * @param existingWorkout существующий объект Workout
     * @param updatedWorkout  объект Workout с обновленными данными
     * @return новый объект Workout с обновленными полями
     */
    private Workout copyNonNullFields(Workout existingWorkout, Workout updatedWorkout) {
        try {
            // Создаем массив для хранения значений полей
            Object[] fieldValues = new Object[Workout.class.getRecordComponents().length];

            // Используем рефлексию для копирования полей
            for (int i = 0; i < Workout.class.getRecordComponents().length; i++) {
                var component = Workout.class.getRecordComponents()[i];
                Method accessor = component.getAccessor(); // Получаем метод-аксессор

                // Получаем значение из updatedWorkout
                Object updatedValue = accessor.invoke(updatedWorkout);
                // Если значение не null, используем его, иначе берем значение из existingWorkout
                fieldValues[i] = (updatedValue != null) ? updatedValue : accessor.invoke(existingWorkout);
            }

            // Создаем новый объект Workout с обновленными значениями полей
            return Workout.class.getDeclaredConstructor(UUID.class, String.class, String.class, int.class, int.class, LocalDateTime.class, String.class).newInstance(fieldValues);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при копировании полей через рефлексию", e);
        }
    }

    @Override
    public boolean deleteWorkout(UUID id) {
        List<Workout> workouts = loadWorkouts();
        // Ищем тренировку по ID
        Workout workoutToDelete = getWorkoutById(id);

        if (workoutToDelete != null) {
            // Удаляем тренировку из списка
            workouts.remove(workoutToDelete);
            // Сохраняем обновленный список в файл
            saveWorkouts(workouts);
            return true; // Возвращаем true, если тренировка удалена
        }

        return false; // Возвращаем false, если тренировка не найдена
    }
}