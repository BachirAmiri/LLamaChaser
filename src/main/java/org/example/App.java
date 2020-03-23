package org.example;

import org.example.exceptions.InvalidDataException;
import org.example.exceptions.InvalidFileFormatException;
import org.example.models.Instance;
import org.example.services.FileReader;
import org.example.services.RunSimulation;

import static org.example.constants.Constants.DEFAULT_FILE_PATH;

public class App 
{
    public static void main( String[] args ) throws InvalidDataException, InvalidFileFormatException {
        Instance instance = FileReader.readFile(DEFAULT_FILE_PATH);
        RunSimulation.run(instance);
    }
}
