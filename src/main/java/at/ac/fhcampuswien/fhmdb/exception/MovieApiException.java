package at.ac.fhcampuswien.fhmdb.exception;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MovieApiException extends Exception {
    public MovieApiException(String message) {
        super(message);
    }
}
