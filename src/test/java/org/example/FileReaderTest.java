package org.example;

import org.example.enums.Orientation;
import org.example.exceptions.InvalidDataException;
import org.example.exceptions.InvalidFileFormatException;
import org.example.models.Adventurer;
import org.example.models.Instance;
import org.example.services.FileReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileReaderTest {

    @Test
    @DisplayName("Fichier valide, Adventurer valide")
    public void validFileCheck() throws InvalidFileFormatException, InvalidDataException {
        Instance instance = FileReader.readFile("src/test/resources/validFile.txt");
        assertEquals(instance.getAdventurerList().size(), 1);

        assertThat(instance.getAdventurerList().get(0), samePropertyValuesAs(
                new Adventurer(1, 1, Orientation.S, 3, 4, "Lara",
                        Arrays.asList('A', 'A', 'D', 'A', 'D', 'A', 'G', 'G', 'A'))));
    }

    @Test
    @DisplayName("Fichier invalide, format première ligne incorrecte")
    public void invalidFileCheck_MAP_SIZE() {
        assertThrows(InvalidFileFormatException.class,
                () -> FileReader.readFile("src/test/resources/fileReader/invalidFile_FirstLineInvalid.txt"));
    }

    @Test
    @DisplayName("Fichier invalide, position de départ montagne invalide")
    public void invalidFileCheck_INITIAL_POS_MOUNTAIN() {
        assertThrows(InvalidDataException.class,
                () -> FileReader.readFile
                        ("src/test/resources/fileReader/invalidFile_InitPositionOutOfBoundsMountain.txt"));
    }

    @Test
    @DisplayName("Fichier invalide, position de départ invalide")
    public void invalidFileCheck_INITIAL_POS_ADVENTURER() {
        assertThrows(InvalidDataException.class,
                () -> FileReader.readFile
                        ("src/test/resources/fileReader/invalidFile_InitPositionOutOfBoundsAdventurer.txt"));
    }

    @Test
    @DisplayName("Fichier invalide, position de départ invalide")
    public void invalidFileCheck_INITIAL_POS_TREASURE() {
        assertThrows(InvalidDataException.class,
                () -> FileReader.readFile
                        ("src/test/resources/fileReader/invalidFile_InitPositionOutOfBoundsTreasure.txt"));
    }

    @Test
    @DisplayName("Fichier invalide, instructions au mauvais format")
    public void invalidFileCheck_INSTRUCTIONS() {
        assertThrows(InvalidFileFormatException.class,
                () -> FileReader.readFile("src/test/resources/fileReader/invalidFile_InstructionsFormat.txt"));
    }

    @Test
    @DisplayName("Fichier invalide, instructions fausses")
    public void invalidFileCheck_INSTRUCTIONS2() {
        assertThrows(InvalidFileFormatException.class,
                () -> FileReader.readFile("src/test/resources/fileReader/invalidFile_InstructionsValues.txt"));
    }

    @Test
    @DisplayName("Fichier invalide, position de départ déjà occupée par montagne ou autre aventurier")
    public void invalidFileCheck_INSTRUCTIONS_INIT_POS_TAKEN() {
        assertThrows(InvalidDataException.class,
                () -> FileReader.readFile
                        ("src/test/resources/fileReader/invalidFile_InstructionsValues_InvalidInitPos.txt"));
    }
}
