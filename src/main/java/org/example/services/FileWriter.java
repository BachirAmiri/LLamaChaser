package org.example.services;

import org.example.models.Instance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.example.constants.Constants.DEFAULT_OUTPUT_PATH;
import static org.example.utils.Utils.*;

public class FileWriter {

    public static void writeFile(Instance instance) throws IOException {
        String content = getMapContent(instance.getMap());
        content = content.concat(getAdventurers(instance.getAdventurerList()));

        Files.write(Paths.get(DEFAULT_OUTPUT_PATH), content.getBytes());
    }
}
