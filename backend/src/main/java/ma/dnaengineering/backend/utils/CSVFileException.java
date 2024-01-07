package ma.dnaengineering.backend.utils;

public class CSVFileException extends RuntimeException {
    public CSVFileException(String message) {
        super(message);
    }
    public CSVFileException(String message, Throwable cause) {
        super(message, cause);
    }
}