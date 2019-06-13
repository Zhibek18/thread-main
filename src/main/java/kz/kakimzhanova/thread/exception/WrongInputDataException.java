package kz.kakimzhanova.thread.exception;

public class WrongInputDataException extends Exception {
    public WrongInputDataException() {
    }

    public WrongInputDataException(String s) {
        super(s);
    }

    public WrongInputDataException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public WrongInputDataException(Throwable throwable) {
        super(throwable);
    }
}
