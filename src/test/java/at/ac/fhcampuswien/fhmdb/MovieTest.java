package at.ac.fhcampuswien.fhmdb;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class MovieTest {
    @Test
    void initialized_movielist_contains_anything(){
        //When
        List<Movie> movieList = Movie.initializeMovies();
        //Then
        assertFalse(movieList.isEmpty());
    }
}