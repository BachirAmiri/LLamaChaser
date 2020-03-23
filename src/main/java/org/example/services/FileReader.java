package org.example.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.enums.Orientation;
import org.example.exceptions.InvalidDataException;
import org.example.exceptions.InvalidFileFormatException;
import org.example.models.Adventurer;
import org.example.models.Instance;
import org.example.models.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.example.constants.Constants.*;
import static org.example.utils.Utils.displayAdventurers;
import static org.example.utils.Utils.displayMap;

public class FileReader {

    private static final Logger logger = LogManager.getLogger(FileReader.class);

    public static Instance readFile(String pathToFile) throws InvalidFileFormatException, InvalidDataException {

        Instance instance = new Instance();
        Map[][] map = new Map[0][];
        List<Adventurer> adventurers = new ArrayList<>();

        Pattern MAP_SIZE = Pattern.compile(MAP_SIZE_REGEX);
        Pattern MOUNTAIN = Pattern.compile(MOUNTAIN_REGEX);
        Pattern TREASURE = Pattern.compile(TREASURE_REGEX);
        Pattern ADVENTURER = Pattern.compile(ADVENTURER_REGEX);

        int maxX = 0;
        int maxY = 0;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(pathToFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                // Le # défini une ligne de commentaire
                if (Character.toString(line.charAt(0)).equals(COMM_FLAG)) {
                    continue;
                }

                line = line.replace(SPACE, "");

                if (MAP_SIZE.matcher(line).matches()) {
                    String[] split = line.split(DASH);

                    maxX = Integer.parseInt(split[1]);
                    maxY = Integer.parseInt(split[2]);

                    map = new Map[maxX][maxY];

                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map[i].length; j++) {
                            map[i][j] = new Map();
                        }
                    }

                } else if (MOUNTAIN.matcher(line).matches()) {
                    String[] split = line.split(DASH);

                    int x = Integer.parseInt(split[1]);
                    int y = Integer.parseInt(split[2]);

                    checkInitPos(x, y, maxX, maxY, line);
                    checkInitPosCollision(x, y, map, line);
                    map[x][y].setMountain(true);

                } else if (TREASURE.matcher(line).matches()) {
                    String[] split = line.split(DASH);

                    int x = Integer.parseInt(split[1]);
                    int y = Integer.parseInt(split[2]);

                    checkInitPos(x, y, maxX, maxY, line);
                    checkInitPosCollision(x, y, map, line);

                    map[x][y].setTreasureCount(Integer.parseInt(split[3]));

                } else if (ADVENTURER.matcher(line).matches()) {
                    String[] split = line.split(DASH);

                    String name = split[1];
                    int initX = Integer.parseInt(split[2]);
                    int initY = Integer.parseInt(split[3]);

                    Orientation orientation = Orientation.valueOf(split[4]);
                    String chars = split[5];

                    List<Character> instructions = chars
                            .chars()
                            .mapToObj(e -> (char) e)
                            .collect(Collectors.toList());

                    checkInitPos(initX, initY, maxX, maxY, line);
                    checkInitPosCollision(initX, initY, map, line);

                    Adventurer adventurer = new Adventurer(initX, initY, orientation, maxX, maxY, name, instructions);

                    map[initX][initY].setAdventurer(name);
                    adventurers.add(adventurer);

                } else {
                    throw new InvalidFileFormatException("Erreur a la lecture de la ligne: " + line);
                }
            }

        } catch (IOException e) {
            logger.error("Erreur lors de la récupération du fichier d'instructions", e);
        }

        instance.setMap(map);
        instance.setAdventurerList(adventurers);

        displayAdventurers(adventurers);
        displayMap(map);

        return instance;
    }

    public static void checkInitPos(int initX, int initY, int maxX, int maxY, String line) throws InvalidDataException {
        if (initX > maxX || initX < 0 || initY > maxY || initY < 0) {
            throw new InvalidDataException("Erreur dans les données en entrée, position de départ en dehors" +
                    "des limites du terrain a la ligne : " + line);
        }
    }

    public static void checkInitPosCollision(int initX, int initY, Map[][] map, String line) throws InvalidDataException {
        if (map[initX][initY].blocked()) {
            throw new InvalidDataException("Erreur dans les données en entrée, position de départ déjà occupée" +
                    " a la ligne : " + line);
        }
    }

}
