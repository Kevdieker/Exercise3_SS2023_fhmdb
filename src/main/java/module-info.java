module at.ac.fhcampuswien.fhmdb{
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires okhttp3;
    requires ormlite.jdbc;
    requires java.sql;

    opens at.ac.fhcampuswien.fhmdb to javafx.fxml,com.google.gson;

    exports at.ac.fhcampuswien.fhmdb;
    exports at.ac.fhcampuswien.fhmdb.exception;
    opens at.ac.fhcampuswien.fhmdb.exception to com.google.gson, javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb.controller;
    opens at.ac.fhcampuswien.fhmdb.controller to com.google.gson, javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb.database;
    opens at.ac.fhcampuswien.fhmdb.database to com.google.gson, javafx.fxml, ormlite.jdbc;
}