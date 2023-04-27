package at.ac.fhcampuswien.fhmdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.Genre.*;
import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    @Test
    void movies_are_filtered_after_genre_All() {
        //Given
        List<Movie> movieList = new ArrayList<>();
        List<Movie> expectedMovies = new ArrayList<>();
        Movie movie = new Movie("Movie1");
        expectedMovies.add(movie);
        movieList.add(movie);
        movie = new Movie("Movie2");
        expectedMovies.add(movie);
        movieList.add(movie);
        movie = new Movie("Movie3");
        expectedMovies.add(movie);
        movieList.add(movie);
        //When
        List<Movie> actualMovies = HomeController.filterAfterGenre(ALL, movieList);
        //Then
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void movies_are_filtered_after_genre_sport() {
        //Given
        Genre wantedGenre = SPORT;
        List<Movie> movieList = new ArrayList<>();
        List<Movie> expectedMovies = new ArrayList<>();
        Movie movie = new Movie("Sportmovie");

        movie.setGenres(List.of(wantedGenre));
        movieList.add(movie);
        expectedMovies.add(movie);

        movie = new Movie("No_Sportmovie");
        movie.setGenres(List.of(ACTION));
        movieList.add(movie);

        movie = new Movie("Sportmovie_And_Something_Different");
        movie.setGenres(List.of(FAMILY,wantedGenre));
        movieList.add(movie);
        expectedMovies.add(movie);
        //When
        List<Movie> actualMovies = HomeController.filterAfterGenre(wantedGenre, movieList);
        //Then
        assertEquals(expectedMovies, actualMovies);
    }

    //testing filtering two times in a row isn´t necessary as we always take AllMovies as a basis
    @Test
    void string_is_getting_uniformed_by_upperLowerCase_and_empty_spaces() {
        //Given
        String polyformString = "   thIs is A random TeXT!";
        //When
        String actualString = HomeController.makeStringUniform(polyformString);
        //Then
        assertEquals("thisisarandomtext!", actualString);
    }

    @Test
    void empty_search_shows_all_given_movies() {
        //Given
        String wantedString = "";
        List<Movie> movieList = new ArrayList<>();
        List<Movie> expectedMovies = new ArrayList<>();
        Movie movie = new Movie("Contains word");
        expectedMovies.add(movie);
        movieList.add(movie);
        movie = new Movie("Contains not");
        expectedMovies.add(movie);
        movieList.add(movie);
        movie = new Movie("Contains word also");
        expectedMovies.add(movie);
        movieList.add(movie);
        //When
        List<Movie> actualMovies = HomeController.searchAfterString(wantedString, movieList);
        //Then
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void movies_are_searched_correctly_after_a_string_in_title() {
        //Given
        String wantedString = "word";
        List<Movie> movieList = new ArrayList<>();
        List<Movie> expectedMovies = new ArrayList<>();
        Movie movie = new Movie("Contains word");
        movieList.add(movie);
        expectedMovies.add(movie);
        movie = new Movie("Contains not");
        movieList.add(movie);
        movie = new Movie("Contains word also");
        movieList.add(movie);
        expectedMovies.add(movie);
        //When
        List<Movie> actualMovies = HomeController.searchAfterString(wantedString, movieList);
        //Then
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void movies_are_searched_correctly_after_a_string_in_description() {
        //Given
        String wantedString = "word";
        List<Movie> movieList = new ArrayList<>();
        List<Movie> expectedMovies = new ArrayList<>();
        Movie movie = new Movie("empty");
        movie.setDescription("contains word");
        movieList.add(movie);
        expectedMovies.add(movie);
        movie = new Movie("empty");
        movie.setDescription("contains not");
        movieList.add(movie);
        movie = new Movie("empty");
        movie.setDescription("contains word also");
        movieList.add(movie);
        expectedMovies.add(movie);
        //When
        List<Movie> actualMovies = HomeController.searchAfterString(wantedString, movieList);
        //Then
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    public void movies_get_sorted_ascending() {
        // given
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("A"));
        movies.add(new Movie("D"));
        movies.add(new Movie("B"));
        movies.add(new Movie("E"));
        movies.add(new Movie("C"));
        // when
        List<Movie> sortedMovies = HomeController.sortMovies("A-Z", movies);
        // then
        assertEquals("A", sortedMovies.get(0).getTitle());
        assertEquals("B", sortedMovies.get(1).getTitle());
        assertEquals("C", sortedMovies.get(2).getTitle());
        assertEquals("D", sortedMovies.get(3).getTitle());
        assertEquals("E", sortedMovies.get(4).getTitle());
    }

    @Test
    public void movies_get_sorted_descending() {
        // Given
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("A"));
        movies.add(new Movie("D"));
        movies.add(new Movie("B"));
        movies.add(new Movie("E"));
        movies.add(new Movie("C"));
        // When
        List<Movie> sortedMovies = HomeController.sortMovies("Z-A", movies);
        // Then
        assertEquals("E", sortedMovies.get(0).getTitle());
        assertEquals("D", sortedMovies.get(1).getTitle());
        assertEquals("C", sortedMovies.get(2).getTitle());
        assertEquals("B", sortedMovies.get(3).getTitle());
        assertEquals("A", sortedMovies.get(4).getTitle());
    }

    @Test
    void get_back_the_movies_that_are_in_both_lists() {
        // Given
        Movie movie1 = new Movie("A");
        Movie movie2 = new Movie("B");
        Movie movie3 = new Movie("C");
        List<Movie> list1 = new ArrayList<>(Arrays.asList(movie1, movie2));
        List<Movie> list2 = new ArrayList<>(Arrays.asList(movie2, movie3));
        // When
        List<Movie> intersectingMovies = HomeController.intersectingMovies(list1, list2);
        // Then
        assertEquals(movie2, intersectingMovies.get(0));
    }

    @Test
    public void get_most_popular_actor() {
        // Given
        Movie movie1 = new Movie("Movie 1");
        movie1.setMainCast(List.of("Actor A","Actor B","Actor C"));

        Movie movie2 = new Movie("Movie 2");
        movie2.setMainCast(List.of("Actor A","Actor B"));

        Movie movie3 = new Movie("Movie 3");
        movie3.setMainCast(List.of("Actor B","Actor C","Actor D"));

        // When
        List<Movie> movies = new ArrayList<>(Arrays.asList(movie1, movie2, movie3));

        // Then
        assertEquals("Actor B", HomeController.getMostPopularActor(movies));
        assertNull(HomeController.getMostPopularActor(new ArrayList<>()));
    }

    @Test
    public void get_longest_movie_title() {
        // Given
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("1234"));
        movies.add(new Movie("123456789"));
        movies.add(new Movie("123456"));

        // When
        int longestTitleLength = HomeController.getLongestMovieTitle(movies);

        // Then
        assertEquals(9, longestTitleLength);
        assertNull(HomeController.getMostPopularActor(new ArrayList<>()));
    }

    @Test
    public void count_movies_from_director() {
        // Given
        Movie movie1 = new Movie("Movie 1");
        movie1.setDirectors(List.of("Director A","Director B","Director C"));

        Movie movie2 = new Movie("Movie 2");
        movie2.setDirectors(List.of("Director A","Director B"));

        Movie movie3 = new Movie("Movie 3");
        movie3.setDirectors(List.of("Director B","Director C","Director D"));

        List<Movie> movies = new ArrayList<>(Arrays.asList(movie1, movie2, movie3));

        // When
        long moviesFromDirector = HomeController.countMoviesFrom(movies, "Director C");

        // Then
        assertEquals(2, moviesFromDirector);
        assertNull(HomeController.getMostPopularActor(new ArrayList<>()));
    }

    @Test
    public void get_movies_between_years() {
        // Given
        Movie movie1 = new Movie("Movie 1");
        movie1.setReleaseYear(1979);

        Movie movie2 = new Movie("Movie 2");
        movie2.setReleaseYear(1983);

        Movie movie3 = new Movie("Movie 3");
        movie3.setReleaseYear(1986);

        Movie movie4 = new Movie("Movie 4");
        movie4.setReleaseYear(1990);

        Movie movie5 = new Movie("Movie 5");
        movie5.setReleaseYear(2000);


        List<Movie> movies = new ArrayList<>(Arrays.asList(movie1, movie2, movie3, movie4, movie5));

        // When
        List<Movie> expectedMovies = new ArrayList<>(Arrays.asList(movie2, movie3, movie4));

        List<Movie> moviesBetweenYears = HomeController.getMoviesBetweenYears(movies, 1980, 1992);

        // Then
        assertEquals(expectedMovies, moviesBetweenYears);
    }


}