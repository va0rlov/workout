package com.example.servingwebcontent.projectstructure;

import java.io.IOException;

public interface ProjectStructureWriter {
    void writeProjectStructure(String projectPath, String outputFile) throws IOException;
}