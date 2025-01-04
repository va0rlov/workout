package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.models.Workout;
import com.example.servingwebcontent.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // Используем @Controller для возврата HTML
@RequestMapping("/") // Базовый путь для веб-интерфейса
public class WorkoutWebController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutWebController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    /**
     * Возвращает HTML-страницу со списком тренировок.
     *
     * @param model модель для передачи данных в шаблон
     * @return имя шаблона (workouts.html)
     */
    @GetMapping
    public String getAllWorkouts(Model model) {
        List<Workout> workouts = workoutService.getAllWorkouts();
        model.addAttribute("workouts", workouts); // Передаем список тренировок в шаблон
        return "workouts"; // Имя шаблона (workouts.html)
    }

    /**
     * Отображает форму для создания новой тренировки.
     *
     * @return имя шаблона (create-workout.html)
     */
    @GetMapping("/new")
    public String showCreateForm() {
        return "create-workout"; // Возвращаем шаблон формы создания тренировки
    }

}