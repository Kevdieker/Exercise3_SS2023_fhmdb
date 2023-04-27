package at.ac.fhcampuswien.fhmdb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String id;
    private String title;
    private List<Genre> genres;
    private int releaseYear;
    private String description;
    private String imgUrl;
    private int lengthInMinutes;
    private List<String> directors;
    private List<String> writers;
    private List<String> mainCast;
    private double rating;


    public Movie(String title) {
        this.title = title;
    }

    //_________________________ GETTER & SETTER ______________________//
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    public void setMainCast(List<String> mainCast) {
        this.mainCast = mainCast;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    //_________________________ ADDER ______________________//
    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }

    public String getGenresInStringFormat() {
        return genres.toString();
    }

    public void addMainCast(String actor) {
        this.mainCast.add(actor);
    }

    public void addDirectors(String director) {
        this.directors.add(director);
    }


    //_________________________ METHODS ______________________//
    public static List<Movie> initializeMovies() { //adds example movies to a list and returns it
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie("Campus Couple Love Story");
        movie.setDescription("2 students, 1 love. Between lectures, exercises and test there is something exciting and new. Our two main character experience the beauty of love. But not \n" + "only there are struggles in their new found love, but also they have to face the challenges of studying ...\n");
        movie.setGenres(Arrays.asList(Genre.ROMANCE, Genre.WAR, Genre.DRAMA, Genre.COMEDY));
        movie.setRating(10.0);
        movie.setReleaseYear(2022);
        movie.setMainCast(List.of("Nadine","Kevin"));
        movie.setDirectors(List.of("Director1"));
        movies.add(movie);

        movie = new Movie("Matrix Murder");
        movie.setDescription("In Math class, there is a topic, everyone fears! Only the bravest students will survive. They already fought 2 whole battles. Our fighters heavily injured.\n" + "Watch the last ultimate battle of the last possible examination. Will they fail or will they survive?\n");
        movie.setGenres(Arrays.asList(Genre.HORROR, Genre.CRIME, Genre.ACTION, Genre.THRILLER, Genre.MYSTERY, Genre.DRAMA));
        movie.setRating(9.3);
        movie.setReleaseYear(1955);
        movie.setMainCast(List.of("Nadine","Math Teacher"));
        movie.setDirectors(List.of("Director1"));
        movies.add(movie);

        movie = new Movie("Operating Systems o´clock");
        movie.setDescription("It is time for operating systems. Get the laptops out, start the console line and let´s go. Every command will effect something different, every combination\n" + "an individual masterpiece. It is up to the programmer, what he wants to do next. Will all the missing parts be found to succeed? Will all riddles get solved?\n" + "This is an up close movie to the fight of getting all points in the operating system test on Oracle.\n");
        movie.setGenres(Arrays.asList(Genre.ACTION, Genre.ANIMATION, Genre.SCIENCE_FICTION));
        movie.setRating(8.7);
        movie.setReleaseYear(1980);
        movie.setMainCast(List.of("Unknown","Grandma","Grandpa"));
        movie.setDirectors(List.of("Director2"));
        movies.add(movie);

        movie = new Movie("Operating Systems o´clock 2");
        movie.setDescription("They failed. But only so closely. But nevertheless they failed. What do to now? Preparing better, getting better, being better! But will it be enough in the\n" + "end? Or will they have to submit themselves to the powers of the complexity of the operating systems ...\n");
        movie.setGenres(Arrays.asList(Genre.DRAMA, Genre.ACTION, Genre.ANIMATION, Genre.SCIENCE_FICTION));
        movie.setRating(7.2);
        movie.setReleaseYear(1964);
        movie.setMainCast(List.of("Unknown","Grandma","Grandpa"));
        movie.setDirectors(List.of("Director2"));
        movies.add(movie);

        movie = new Movie("Oh Campina Campina!");
        movie.setDescription("Our daily meal as students of FHCW, a mystery. We are visiting the kitchen of Campina for one day. Come and see how we discover and expose all secrets.\n" + "Recipes included!\n");
        movie.setGenres(Arrays.asList(Genre.MYSTERY, Genre.HISTORY, Genre.HORROR, Genre.DOCUMENTARY));
        movie.setRating(7.8);
        movie.setReleaseYear(1943);
        movie.setMainCast(List.of("Unknown"));
        movie.setDirectors(List.of("Director3"));
        movies.add(movie);

        movie = new Movie("Dream Team");
        movie.setDescription("Teamwork can be exhausting, traumatizing and depressing. However, all above it is the most beautiful thing in the world!\n");
        movie.setGenres(Arrays.asList(Genre.MUSICAL, Genre.FAMILY, Genre.ADVENTURE));
        movie.setRating(7.5);
        movie.setReleaseYear(2003);
        movie.setMainCast(List.of("Kevin","Nadine","Fatima","Valentina"));
        movie.setDirectors(List.of("Director1"));
        movies.add(movie);

        movie = new Movie("Rubber duck invasion");
        movie.setDescription("The first rubber duck was found nowhere less special than in David Brek's backpack on his way to a heist. He, most feared leader, and his accomplices\n" + "were the most-wanted criminals in whole Western City. Since that moment, even more and more ducks appeared in their backpacks. No matter how\n" + "much they observed their backpacks, the duck drama didn´t end. If they don´t stop the duck avalanche in time they will be found. The time is ticking.\n");
        movie.setGenres(Arrays.asList(Genre.MYSTERY, Genre.WESTERN, Genre.FAMILY, Genre.COMEDY, Genre.ADVENTURE));
        movie.setRating(5.7);
        movie.setReleaseYear(2005);
        movie.setMainCast(List.of("Nadine","Valentina"));
        movie.setDirectors(List.of("Director1"));

        movies.add(movie);
        movie = new Movie("Howdy Campus");
        movie.setDescription("Explore the Wild West in 'Howdy Campus: Uncovering the Origins of the FH'.\nWitness the struggles of a small group of pioneers wanting to bring education in a land without laws and rules.\nWill they be successful? This captivating documentary is a must-see for anyone who loves history, the Wild West, and the power of knowledge.\"");
        movie.setGenres(Arrays.asList(Genre.DOCUMENTARY, Genre.WESTERN, Genre.HISTORY));
        movie.setRating(1.5);
        movie.setReleaseYear(2005);
        movie.setMainCast(List.of("Kevin","Unknown","Fatima"));
        movie.setDirectors(List.of("DirectorKevin"));

        movies.add(movie);
        movie = new Movie("Finishing the race");
        movie.setDescription("The finish line in vision, only view last miles left. After running for so long the race between students and test are finally to an end. Now the have to face\n" + "their last ultimate enemy. They have to find out if they are up to this task after such long fighting or if they just got exhausted by now? If they are able to\n" + "overtake, what next? What is there after the finishing line, is it even worth it or will there be just another running track till infinity ...\n");
        movie.setGenres(Arrays.asList(Genre.FANTASY, Genre.SPORT, Genre.MYSTERY));
        movie.setRating(3.6);
        movie.setReleaseYear(2018);
        movie.setMainCast(List.of("Fatima","Valentina","Kevin","Nadine","Director3"));
        movie.setDirectors(List.of());
        movies.add(movie);
        return movies;
    }

    public static String gettingJsontoString() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8080/movies")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static String gettingMovietoJsonString() {

        ArrayList<TestClass> movies = new ArrayList<>();
        TestClass movie = new TestClass("The Godfather");
        movie.setId("69f7612c-ec9f-434f-9d4a-25e52d290a32");
        movie.setGenres(List.of(Genre.DRAMA));
        movie.setReleaseYear(1972);
        movie.setDescription("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.");
        movie.setImgUrl("http://www.imdb.com/title/tt0068646/mediaviewer/rm1067457536");
        movie.setLengthInMinutes(175);
        movie.setDirectors(List.of("Francis Ford Coppola"));
        movie.setWriters(List.of("Mario Puzo","Francis Ford Coppola"));
        movie.setMainCast(List.of("Marlon Brando","Al Pacino","James Caan"));
        movie.setRating(9.2);


        movies.add(movie);


        TestClass movie2 = new TestClass("Movie 2");
        movie2.setDescription("Description 2");
        movies.add(movie2);

        Gson gson = new Gson();

        return gson.toJson(movies);


    }


    public static List<Movie> initializeMovies2() {

        List<Movie> movies = Arrays.asList(new GsonBuilder().create().fromJson(gettingJsontoString(), Movie[].class));

        System.out.println(movies);

        return movies;
    }
}