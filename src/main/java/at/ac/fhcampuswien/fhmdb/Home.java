package at.ac.fhcampuswien.fhmdb;
import at.ac.fhcampuswien.fhmdb.database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Home extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
        stage.setTitle("MovieCampus");
        stage.setScene(new Scene(root,1215,790));
        stage.setResizable(false);
        stage.show();

        /*try {
            Database.getDatabase().testDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }
    public static void main(String[] args) {
        launch();
    }
}