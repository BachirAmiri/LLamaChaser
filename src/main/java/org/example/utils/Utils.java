package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.models.Adventurer;
import org.example.models.Map;
import org.example.models.Treasure;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final Logger logger = LogManager.getLogger(Utils.class);


    public static void displayMap(Map[][] map) {
        logger.info("\n");
        for (int i = 0; i < map[0].length; i++) {
            String line = "";
            for (Map[] maps : map) {
                line = line.concat(maps[i].toString() + "\t");
            }
            logger.info(line);
        }
    }

    public static void displayAdventurers(List<Adventurer> adventurers) {
        for (Adventurer adventurer : adventurers) {
            logger.info(adventurer);
        }
    }

    public static void displayTreasures(List<Treasure> treasures) {
        for (Treasure treasure : treasures) {
            logger.info(treasure);
        }
    }

    public static List<String> getTreasuresRemaining(Map[][] maps) {
        List<String> treasures = new ArrayList<>();
        int i = 0;
        for (Map[] map : maps) {
            int j = 0;
            for (Map area : map) {
                if (area.getTreasureCount() > 0) {
                    treasures.add("T-" + i + "-" + j + "-" + area.getTreasureCount());
                }
                j++;
            }
            i++;
        }

        return treasures;
    }

    public static String getMapContent(Map[][] maps) {
        String content = "";
        content = content.concat("C-" + maps.length + "-" + maps[0].length + "\n");
        int i = 0;
        for (Map[] map : maps) {
            int j = 0;
            for (Map area : map) {
                if (area.isMountain()) {
                    content = content.concat("M-" + i + "-" + j + "\n");
                }

                if (area.getTreasureCount() > 0) {
                    content = content.concat("T-" + i + "-" + j + "-" + area.getTreasureCount() + "\n");
                }
                j++;
            }
            i++;
        }

        return content;
    }

    public static String getAdventurers(List<Adventurer> adventurers) {
        String content = "";
        for (Adventurer adventurer : adventurers) {
            content = content.concat("A-" + adventurer.getName() + "-"
                    + adventurer.getX() + "-" + adventurer.getY() + "-"
                    + adventurer.getOrientation() + "-" + adventurer.getTreasureCount()
                    + "\n");
        }

        return content;
    }

    /*
        Dans le cas ou les aventuriers n'ont pas le meme nombre d'instructions
     */
    public static int getMaxInstructionsCount(List<Adventurer> adventurers) {
        int max = 0;
        for (Adventurer adventurer : adventurers) {
            if (max < adventurer.getInstructions().size()) {
                max = adventurer.getInstructions().size();
            }
        }

        return max;
    }
}
