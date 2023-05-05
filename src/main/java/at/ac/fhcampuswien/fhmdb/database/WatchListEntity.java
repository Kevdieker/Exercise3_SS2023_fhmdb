package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.Genre;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "movieTable")

public class WatchListEntity {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField()
    private String title;

    @DatabaseField()
    private String description;

    @DatabaseField()
    private Genre genres;

    @DatabaseField()
    private int releaseYear;

    @DatabaseField()
    private String imgUrl;

    @DatabaseField()
    private int lengthInMinutes;

    @DatabaseField()
    private double rating;

    public WatchListEntity(){}
    public WatchListEntity(String title, String description, Genre genres, int releaseYear, String imgUrl, int lengthInMinutes, double rating) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Genre getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }
}
