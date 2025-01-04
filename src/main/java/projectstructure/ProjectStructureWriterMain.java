package projectstructure;

import java.io.IOException;

public class ProjectStructureWriterMain {
    public static void main(String[] args) throws IOException {
        // Используем интерфейс, но создаем объект реализации
        ProjectStructureWriter writer = new ProjectStructureWriterImpl();

        // Записываем структуру проекта в файл
        writer.writeProjectStructure(".", "project_structure.txt");
    }
}