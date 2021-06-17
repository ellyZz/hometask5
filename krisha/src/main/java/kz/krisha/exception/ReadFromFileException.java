package kz.krisha.exception;

public class ReadFromFileException extends RuntimeException {
    public ReadFromFileException() {
    }

    public ReadFromFileException(String message) {
        super(message);
    }

    public ReadFromFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadFromFileException(Throwable cause) {
        super(cause);
    }
}
