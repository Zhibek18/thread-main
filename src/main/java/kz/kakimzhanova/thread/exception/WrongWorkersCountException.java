package kz.kakimzhanova.thread.exception;

public class WrongWorkersCountException extends Exception{
    public WrongWorkersCountException() {
    }

    public WrongWorkersCountException(String s) {
        super(s);
    }

    public WrongWorkersCountException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public WrongWorkersCountException(Throwable throwable) {
        super(throwable);
    }
}
