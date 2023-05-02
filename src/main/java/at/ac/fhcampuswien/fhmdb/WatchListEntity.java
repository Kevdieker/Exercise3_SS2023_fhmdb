package at.ac.fhcampuswien.fhmdb;

public class WatchListEntity {

    private long id;
    private String apild;
    private String title;
    private String description;
    private Genre genres;
    private int releaseYear;
    private String imgUrl;
    private int lengthInMinutes;
    private double rating;

    //_________________________ GETTER & SETTER ______________________//

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApild() {
        return apild;
    }

    public void setApild(String apild) {
        this.apild = apild;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenres() {
        return genres;
    }

    public void setGenres(Genre genres) {
        this.genres = genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
