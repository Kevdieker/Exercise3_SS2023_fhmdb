package at.ac.fhcampuswien.fhmdb;

public class Database {

    private String DB_URL;
    private String username;
    private String password;
    private Movie connectionsource; // soll die "ConnectionSource connectionSource" sein

    //_________________________ GETTER & SETTER ______________________//

    public String getDB_URL() {
        return DB_URL;
    }

    public void setDB_URL(String DB_URL) {
        this.DB_URL = DB_URL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
