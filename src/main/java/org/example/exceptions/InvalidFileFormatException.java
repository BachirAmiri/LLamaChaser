package org.example.exceptions;

public class InvalidFileFormatException extends Exception {

    public InvalidFileFormatException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidFileFormatException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
