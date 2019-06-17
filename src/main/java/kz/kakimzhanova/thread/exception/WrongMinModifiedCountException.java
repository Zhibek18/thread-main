package kz.kakimzhanova.thread.exception;

public class WrongMinModifiedCountException extends Exception{
    public WrongMinModifiedCountException() {
    }

    public WrongMinModifiedCountException(String s) {
        super(s);
    }

    public WrongMinModifiedCountException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public WrongMinModifiedCountException(Throwable throwable) {
        super(throwable);
    }
}
