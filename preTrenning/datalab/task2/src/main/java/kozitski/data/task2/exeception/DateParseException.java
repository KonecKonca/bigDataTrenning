package kozitski.data.task2.exeception;

public class DateParseException extends RuntimeException{
    public DateParseException() {
        super();
    }

    public DateParseException(String message) {
        super(message);
    }

    public DateParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateParseException(Throwable cause) {
        super(cause);
    }
}
