package by.bogdan.lifetivity.exception;

public class FileException extends RuntimeException {
    public FileException(String s) {
        super(s);
    }

    public FileException(Throwable throwable) {
        super(throwable);
    }
}
