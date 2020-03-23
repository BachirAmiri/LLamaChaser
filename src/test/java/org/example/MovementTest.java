package org.example;

import org.example.enums.Orientation;
import org.example.exceptions.InvalidDataException;
import org.example.exceptions.InvalidFileFormatException;
import org.example.models.Adventurer;
import org.example.models.Instance;
import org.example.models.Map;
import org.example.services.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovementTest {

    private static Map[][] map;
    private static Adventurer adventurer;

    @BeforeEach
    public void init() throws InvalidDataException, InvalidFileFormatException {
        Instance instance = FileReader.readFile("src/test/resources/validFile.txt");
        map = instance.getMap();
        adventurer = instance.getAdventurerList().get(0);
    }

    @Test
    @DisplayName("Tourner a droite")
    public void turnRight() {
        adventurer.setOrientation(Orientation.N);

        adventurer.turnRight();
        assertEquals(adventurer.getOrientation(), Orientation.E);

        adventurer.turnRight();
        assertEquals(adventurer.getOrientation(), Orientation.S);

        adventurer.turnRight();
        assertEquals(adventurer.getOrientation(), Orientation.W);

        adventurer.turnRight();
        assertEquals(adventurer.getOrientation(), Orientation.N);
    }

    @Test
    @DisplayName("Tourner a gauche")
    public void turnLeft() {
        adventurer.setOrientation(Orientation.N);

        adventurer.turnLeft();
        assertEquals(adventurer.getOrientation(), Orientation.W);

        adventurer.turnLeft();
        assertEquals(adventurer.getOrientation(), Orientation.S);

        adventurer.turnLeft();
        assertEquals(adventurer.getOrientation(), Orientation.E);

        adventurer.turnLeft();
        assertEquals(adventurer.getOrientation(), Orientation.N);
    }

    @Test
    @DisplayName("Avancer sans sortir des bords")
    public void advance() {
        adventurer.setX(0);
        adventurer.setY(0);

        adventurer.setOrientation(Orientation.N);
        adventurer.advance(map);
        assertEquals(adventurer.getX(), 0);
        assertEquals(adventurer.getY(), 0);

        adventurer.setOrientation(Orientation.W);
        adventurer.advance(map);
        assertEquals(adventurer.getX(), 0);
        assertEquals(adventurer.getY(), 0);

        adventurer.setX(1);
        adventurer.setY(1);

        adventurer.setMaxX(1);
        adventurer.setMaxY(1);

        adventurer.setOrientation(Orientation.S);
        adventurer.advance(map);
        assertEquals(adventurer.getX(), 1);
        assertEquals(adventurer.getY(), 1);

        adventurer.setOrientation(Orientation.E);
        adventurer.advance(map);
        assertEquals(adventurer.getX(), 1);
        assertEquals(adventurer.getY(), 1);
    }

    @Test
    @DisplayName("Avancer sur une case occupée montagne ou joueur")
    public void advanceOnOccupied() {
        map[1][2].setAdventurer("MrObstable");

        MovementTest.adventurer.advance(map);
        assertEquals(MovementTest.adventurer.getX(), 1);
        assertEquals(MovementTest.adventurer.getY(), 1);

        MovementTest.adventurer.setOrientation(Orientation.N);

        MovementTest.adventurer.advance(map);
        assertEquals(MovementTest.adventurer.getX(), 1);
        assertEquals(MovementTest.adventurer.getY(), 1);
    }

    @Test
    @DisplayName("Ramasser tresor")
    public void pickTreasure() {
        map[1][2].setTreasureCount(1);
        MovementTest.adventurer.advance(map);
        assertEquals(MovementTest.adventurer.getTreasureCount(), 1);
        assertEquals(map[1][2].getTreasureCount(), 0);
    }
}
