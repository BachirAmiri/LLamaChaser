package org.example.constants;

public class Constants {
    public final static String DEFAULT_FILE_PATH = "src/main/resources/map.txt";
    public final static String DEFAULT_OUTPUT_PATH = "src/main/resources/output.txt";

    public final static String SPACE = " ";
    public final static String DASH = "-";
    public final static String COMM_FLAG = "#";

    public final static String MAP_SIZE_REGEX = "C-[0-9]+-[0-9]+$";
    public final static String MOUNTAIN_REGEX = "M-[0-9]+-[0-9]+$";
    public final static String TREASURE_REGEX = "T-[0-9]+-[0-9]+-[0-9]+$";
    public final static String ADVENTURER_REGEX = "A-[A-Za-z]+-[0-9]+-[0-9]+-(N|W|E|S)-(A|D|G)+$";
}
