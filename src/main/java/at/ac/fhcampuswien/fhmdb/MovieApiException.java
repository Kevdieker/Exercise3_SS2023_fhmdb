package at.ac.fhcampuswien.fhmdb;

public class MovieApiException extends  Exception {             //MovieApiException
    private final int statusCode;

    public MovieApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;

    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return "DatabaseException{" +
                "message='" + getMessage() + '\'' +
                '}';
    }

    public boolean isClientError() {
        return statusCode >= 400 && statusCode < 500;
    }

    public String getMessageWithStatusCode() {
        return "[" + statusCode + "] " + getMessage();
    }

    public boolean isServerError() {
        return statusCode >= 500 && statusCode < 600;
    }
}

