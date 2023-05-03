package at.ac.fhcampuswien.fhmdb;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MovieApiException extends Exception {
    @FXML
    Label exceptionMessage;
    public MovieApiException(String message) {
        super(message);
        exceptionMessage.isVisible();
    }
}
