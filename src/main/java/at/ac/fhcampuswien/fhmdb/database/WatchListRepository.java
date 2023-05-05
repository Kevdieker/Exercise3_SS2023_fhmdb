package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.database.WatchListEntity;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class WatchListRepository {
    Dao <WatchListEntity,Long> dao;

    public WatchListRepository() {
        this.dao = Database.getDatabase().getDao();
    }

    public void addToWatchList(WatchListEntity movie) throws SQLException {
        dao.create(getAll(movie));
    }

    public void removeFromWatchList(WatchListEntity movie) throws SQLException {
        dao.delete(getAll(movie));
    }

    public WatchListEntity getAll(WatchListEntity movie){
        WatchListEntity wl = new WatchListEntity(movie.getTitle(),
                movie.getDescription(),
                movie.getGenres(),
                movie.getReleaseYear(),
                movie.getImgUrl(),
                movie.getLengthInMinutes(),
                movie.getRating());
        return wl;
    }
}
