module at.ac.fhcampuswien.fhmdb{
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires okhttp3;

    opens at.ac.fhcampuswien.fhmdb to javafx.fxml,com.google.gson;

    exports at.ac.fhcampuswien.fhmdb;
}