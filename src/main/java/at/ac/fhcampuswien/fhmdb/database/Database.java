package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.Genre;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Database {

    public static final String DB_URL = "jdbc:h2:file: ./db/moviedb";
    public static final String username = "user";
    public static final String password = "password";

    private static ConnectionSource connectionSource;

    private Dao<WatchListEntity, Long> dao;

    private static Database instance;


    /*public void testDB() throws SQLException {
        WatchListEntity watchList = new WatchListEntity("The thing", "That film is so cool", Genre.ACTION,1999,"img",159,5.0);
        dao.create(watchList);
    } */

    public Dao<WatchListEntity, Long> getDao() {
        return dao;
    }

    private Database(){
        try{
            createConnectionSource();
            dao = DaoManager.createDao(connectionSource,WatchListEntity.class);
            createTables();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, WatchListEntity.class);
    }

    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private static void createConnectionSource() throws SQLException {
        connectionSource = new JdbcConnectionSource(DB_URL,username,password);
    }

}