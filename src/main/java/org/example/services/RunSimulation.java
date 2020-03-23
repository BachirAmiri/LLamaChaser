package org.example.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.models.Adventurer;
import org.example.models.Instance;

import java.io.IOException;

import static org.example.services.FileWriter.writeFile;
import static org.example.utils.Utils.*;

public class RunSimulation {
    private static final Logger logger = LogManager.getLogger(RunSimulation.class);

    public static void run(Instance instance) {
        logger.info("Début de la simulation.");
        int iterations = getMaxInstructionsCount(instance.getAdventurerList());
        for (int i = 0; i < iterations; i++) {
            for (Adventurer adventurer : instance.getAdventurerList()) {
                if (i <= adventurer.getInstructions().size() - 1) {
                    Character character = adventurer.getInstructions().get(i);

                    if (character == 'D') {
                        adventurer.turnRight();
                    }

                    if (character == 'G') {
                        adventurer.turnLeft();
                    }

                    if (character == 'A') {
                        adventurer.advance(instance.getMap());
                    }
                }
            }
            displayAdventurers(instance.getAdventurerList());
            displayMap(instance.getMap());
        }

        displayAdventurers(instance.getAdventurerList());
        displayMap(instance.getMap());

        try {
            writeFile(instance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
