package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.Genre;
import at.ac.fhcampuswien.fhmdb.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieAPI{

    private static String urlBuilder(String query, Genre genre, String releaseYear, String ratingFrom) {

        StringBuilder url = new StringBuilder("http://localhost:8080/movies");

        if ((query != null) || genre != null && releaseYear != null && ratingFrom != null) {
            url.append("?");
        }
        if (query != null) {
            url.append("query=").append(query).append("&");
        }
        if (genre != null) {
            url.append("genre=").append(genre).append("&");
        }
        if (releaseYear != null) {
            url.append("releaseYear=").append(releaseYear).append("&");
        }
        if (ratingFrom != null) {
            url.append("ratingFrom=").append(ratingFrom);
        }
        return url.toString();

    }

    public static List<Movie> getAllMovies(){
        return getAllMovies(null,null,null,null);
    }
    public static List<Movie> getAllMovies(String query, Genre genre, String releaseYear, String ratingFrom){
        String url = urlBuilder(query, genre, releaseYear, ratingFrom);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try(Response response = client.newCall(request).execute()) {
            //List<Movie> movies = new Gson().fromJson(response.body().string(),new TypeToken<List<Movie>>(){}.getType());
            List<Movie> movies = Arrays.asList(new GsonBuilder().create().fromJson(response.body().string(), Movie[].class));
            return movies;

        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
       System.out.println(getAllMovies());
    }



}
