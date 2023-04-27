package at.ac.fhcampuswien.fhmdb;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static at.ac.fhcampuswien.fhmdb.Movie.*;

public class Home extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
        stage.setTitle("MovieCampus");
        stage.setScene(new Scene(root,1215,790));
        stage.setResizable(false);
        stage.show();

    }
    public static void main(String[] args) throws IOException {

        //System.out.println(gettingJsontoString());
       // System.out.println(gettingJsontoString());
        //System.out.println(gettingMovietoJsonString());
        //initializeMovies2(gettingMovietoJsonString());
        //initializeMovies2(gettingJsontoString());
        launch();
    }
}