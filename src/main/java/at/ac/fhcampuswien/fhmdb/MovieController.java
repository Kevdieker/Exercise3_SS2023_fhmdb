package at.ac.fhcampuswien.fhmdb;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MovieController {
    @FXML
    private Label description;
    @FXML
    private Label genres;
    @FXML
    private Label releaseYear;
    @FXML
    private Label rating;
    @FXML
    private Label title;
    public void setData(Movie movie) {
        title.setText(movie.getTitle());
        description.setText(movie.getDescription());
        genres.setText(movie.getGenresInStringFormat());
        releaseYear.setText("Release Year: " + String.valueOf(movie.getReleaseYear()));
        rating.setText("Rating: " + String.valueOf(movie.getRating()));

    }
}
