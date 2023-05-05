package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.Genre;
import at.ac.fhcampuswien.fhmdb.Movie;
import at.ac.fhcampuswien.fhmdb.controller.MovieController;
import at.ac.fhcampuswien.fhmdb.exception.MovieApiException;
import at.ac.fhcampuswien.fhmdb.models.MovieAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class HomeController implements Initializable {
    @FXML
    private VBox home;
    @FXML
    private ComboBox<Genre> genreBox;
    @FXML
    private ComboBox<Object> ratingBox;
    @FXML
    private ComboBox<Object> yearBox;
    @FXML
    private ComboBox<String> sortBox;
    @FXML
    private TextField searchField;

    @FXML
    private Button wlButton;
    //private List<Movie> allMovies = new ArrayList<>(Movie.initializeMovies());
    private List<Movie> allMovies;

    {
        allMovies = new ArrayList<>(MovieAPI.getAllMovies());
    }


    private List<Movie> searchedMovies = allMovies;
    private List<Movie> filteredMoviesAfterGenre = allMovies;
    private List<Movie> filteredMoviesAfterRating = allMovies;
    private List<Movie> filteredMoviesAfterYear = allMovies;
    private List<Movie> combinedSelectedMovies = allMovies;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genreBox.getItems().addAll(Genre.values());
        sortBox.getItems().addAll("A-Z", "Z-A");
        yearBox.getItems().addAll("no filter", 2020, 2010, 2000, 1990, 1980, 1970, 1960, 1950, 1940);
        ratingBox.getItems().addAll("no filter", 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        loadingMovies(allMovies);
    }

    // __________________ Node active actions _______________________//
    public void OnActionFilterMovies() {
        filteredMoviesAfterGenre = filterAfterGenre(genreBox.getValue(), allMovies);
        combinedSelectedMovies = intersectingMovies(filteredMoviesAfterGenre, searchedMovies);              //nicht gut implementiert
        combinedSelectedMovies = intersectingMovies(combinedSelectedMovies, filteredMoviesAfterRating);
        combinedSelectedMovies = intersectingMovies(combinedSelectedMovies, filteredMoviesAfterYear);
        loadingMovies(combinedSelectedMovies);
    }

    public void OnActionFilterMoviesAfterYear() {
        filteredMoviesAfterYear = filterAfterReleaseYear(yearBox.getValue(), allMovies);
        combinedSelectedMovies = intersectingMovies(filteredMoviesAfterYear, searchedMovies);              //nicht gut implementiert
        combinedSelectedMovies = intersectingMovies(combinedSelectedMovies, filteredMoviesAfterRating);
        combinedSelectedMovies = intersectingMovies(combinedSelectedMovies, filteredMoviesAfterGenre);
        loadingMovies(combinedSelectedMovies);
    }

    public void OnActionFilterMoviesAfterRating() {
        filteredMoviesAfterRating = filterAfterRating(ratingBox.getValue(), allMovies);
        combinedSelectedMovies = intersectingMovies(filteredMoviesAfterRating, searchedMovies);              //nicht gut implementiert
        combinedSelectedMovies = intersectingMovies(combinedSelectedMovies, filteredMoviesAfterYear);
        combinedSelectedMovies = intersectingMovies(combinedSelectedMovies, filteredMoviesAfterGenre);
        loadingMovies(combinedSelectedMovies);
    }

    public void OnActiveSearchMovies() {
        if (!searchField.getText().isEmpty()) {
            searchedMovies = searchAfterString(searchField.getText(), allMovies);
        } else {
            searchedMovies = filteredMoviesAfterGenre;
        }
        combinedSelectedMovies = intersectingMovies(filteredMoviesAfterGenre, searchedMovies);
        combinedSelectedMovies = intersectingMovies(combinedSelectedMovies, filteredMoviesAfterRating);
        combinedSelectedMovies = intersectingMovies(combinedSelectedMovies, filteredMoviesAfterYear);
        loadingMovies(combinedSelectedMovies);
    }

    public void OnActiveSortMovies() {
        allMovies = sortMovies(sortBox.getValue(), allMovies);
        combinedSelectedMovies = sortMovies(sortBox.getValue(), combinedSelectedMovies);
        loadingMovies(combinedSelectedMovies);
    }

    // ____________________________ METHODS ________________________________//
    public static List<Movie> filterAfterGenre(Genre genre, List<Movie> movies) {
        if (genre == Genre.ALL) {
            return movies;
        } else {
            List<Movie> filteredMovies = new ArrayList<>();
            for (Movie movie : movies) {
                if (movie.getGenres().contains(genre)) {
                    filteredMovies.add(movie);
                }
            }
            return filteredMovies;
        }
    }

    public static String makeStringUniform(String polyformString) {
        return polyformString.toLowerCase().trim().replaceAll("\\s", "");
    }

    public static List<Movie> searchAfterString(String searchedWord, List<Movie> movies) {
        searchedWord = makeStringUniform(searchedWord);
        List<Movie> searchedMovies = new ArrayList<>();
        //searchedMovies.add(null); needs to be tested
        for (Movie movie : movies) {
            if (makeStringUniform(movie.getTitle()).contains(searchedWord) ||
                    (movie.getDescription() != null && makeStringUniform(movie.getDescription()).contains(searchedWord))) {
                searchedMovies.add(movie);
            }
        }
        return searchedMovies;
    }

    public static List<Movie> sortMovies(String sortAlgo, List<Movie> movies) {
        if (sortAlgo.equals("A-Z")) {
            movies.sort(Comparator.comparing(Movie::getTitle));
        } else if (sortAlgo.equals("Z-A")) {
            movies.sort(Comparator.comparing(Movie::getTitle).reversed());
        }
        return movies;
    }

    //NEW METHODS
    public static List<Movie> filterAfterRating(Object rating, List<Movie> movies) {
        if (rating == "no filter") {
            return movies;
        } else {
            List<Movie> filteredMovies = new ArrayList<>();
            for (Movie movie : movies) {
                if ((int) movie.getRating() == (int) rating) {
                    filteredMovies.add(movie);
                }
            }
            return filteredMovies;
        }
    }

    public static List<Movie> filterAfterReleaseYear(Object releaseYear, List<Movie> movies) {
        if (releaseYear == "no filter") {
            return movies;
        } else {
            List<Movie> filteredMovies = new ArrayList<>();
            releaseYear = (int) releaseYear / 10;
            for (Movie movie : movies) {
                if (movie.getReleaseYear() / 10 == (int) releaseYear) {
                    filteredMovies.add(movie);
                }
            }
            return filteredMovies;
        }
    }

    public static List<Movie> intersectingMovies(List<Movie> list1, List<Movie> list2) {
        List<Movie> common = new ArrayList<>(list1);
        common.retainAll(list2);
        return common;
    }

    //_____________________________________________________________________________________
    //NEW METHODS
    //should be implemented with Streams!!!!!!!!!!!

    public static String getMostPopularActor(List<Movie> movies) {
        return movies.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static int getLongestMovieTitle(List<Movie> movies) {
        return movies.stream()
                .mapToInt(movie -> movie.getTitle().length())
                .max()
                .orElse(0);
    }

    public static long countMoviesFrom(List<Movie> movies, String directors) {
        /*for (Movie movie : movies) {
            List<String> directors = movie.getDirector();
            System.out.println("Directors for " + movie.getTitle() + ":");
            if (directors == null || directors.isEmpty()) {
                System.out.println("No directors found.");
            } else {
                System.out.println(directors);
        */
        return movies.stream()
                .filter(movie -> movie.getDirectors() != null && movie.getDirectors().contains(directors))
                .count();
    }
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) { //gibt jene Filme zurück, die zwischen zwei gegebenen Jahren veröffentlicht wurden.

        return movies.stream()
                .filter(movie -> movie.getReleaseYear() > startYear && movie.getReleaseYear() < endYear)
                .toList();
    }

    /*public static String getMostPopularActor(List<Movie> movies){  //gibt jene Person zurück, die am öftesten im mainCast der übergebenen Filme vorkommt.
    /*public static String getMostPopularActor(List<Movie> movies) {  //gibt jene Person zurück, die am öftesten im mainCast der übergebenen Filme vorkommt.

        return movies.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        if(movies.isEmpty())return null;

        Collection<String> collection = new ArrayList<>();
        for (Movie movie : movies) {
            String stringOfAllActors = null;
            for (String actor : movie.getMainCast()) {
                collection.add(actor);
            }
        }
        Map<String, Long> countMap = collection.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        Map.Entry<String, Long> mostCommonEntry = Collections.max(countMap.entrySet(), Map.Entry.comparingByValue());
        String mostCommonElement = mostCommonEntry.getKey();        //long maxCount = mostCommonEntry.getValue();
        return mostCommonElement;
    }
    public int getLongestMovieTitle(List<Movie> movies) {   //filtert auf den längsten Filmtitel der übergebenen Filme und gibt die Anzahl der Buchstaben des Titels zurück
        return mostCommonElement;*/


     /*   String longestElement = "";

    /*public static int getLongestMovieTitle(List<Movie> movies) {   //filtert auf den längsten Filmtitel der übergebenen Filme und gibt die Anzahl der Buchstaben des Titels zurück
        return movies.stream()
                .map(Movie::getTitle)
                .max(Comparator.comparing(String::length))
                .orElse("").length();

       /* String longestElement = "";
        for (Movie movie : movies) {
            if (movie.getTitle().length() > longestElement.length()) {
                longestElement = movie.getTitle();
            }
        }
        return longestElement.length();
    }
    public long countMoviesFrom(List<Movie> movies, String director){ //gibt die Anzahl der Filme eines bestimmten Regisseurs zurück.
        int count =0;
        */


     /* public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear()>= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());
    }
    /*public static long countMoviesFrom(List<Movie> movies, String director) { //gibt die Anzahl der Filme eines bestimmten Regisseurs zurück.

        return movies.stream()
                .flatMap(movie -> movie.getDirectors().stream())
                .filter(director::equals)
                .count();
        /*

        long count =0;
        for (Movie movie : movies) {
            for(String aDirector : movie.getDirector()){
                if (aDirector== director) {
                    count++;
                }
            }
        }
        return count;
    }
    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear){ //gibt jene Filme zurück, die zwischen zwei gegebenen Jahren veröffentlicht wurden.
        return count;*/

     /*
        List<Movie> newFilteredList = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getReleaseYear()> startYear &&  movie.getReleaseYear()<endYear) {
                newFilteredList.add(movie);
            }
        }
        return newFilteredList;
    }
        return newFilteredList;*/


    // ____________________________ LOADING SCREEN ________________________________//
    public void loadingMovies(List<Movie> movies) {
        home.getChildren().clear();
        try {
            for (Movie movie : movies) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("movie_card.fxml"));
                VBox movieSpace = fxmlLoader.load();
                MovieController movieController = fxmlLoader.getController();
                movieController.setData(movie);
                home.getChildren().add(movieSpace);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Just to see if the above implemented Methods do what they should
        out.println("most Popular Actors = " + getMostPopularActor(movies));
        out.println("longest title character count = " + getLongestMovieTitle(movies));
        //mit random Paramentern
        out.println("count Movies from Director1 = " + countMoviesFrom(movies, "Frank Darabont"));
        out.println("Movies zwischen 1972 und 1980 = " + getMoviesBetweenYears(movies, 1972, 1980));
    }
}
