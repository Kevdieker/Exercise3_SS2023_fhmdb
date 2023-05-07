package at.ac.fhcampuswien.fhmdb;

public class DatabaseException extends Exception {
    private final String query;

    public DatabaseException(String message, String query) {
        super(message);
        this.query = query;
    }
    public String getQuery() {
        return query;

    } @Override
    public String toString() {
        return "DatabaseException{" +
                "message='" + getMessage() + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
    public boolean isSyntaxError() {
        return getMessage().contains("syntax error");
    }
    public boolean isDuplicateEntryError() {
        return getMessage().contains("Duplicate entry");
    }
    public String getErrorMessageWithQuery() {
        return "Database error occurred while executing query: " + query + "\nError message: " + getMessage();
    }
}

