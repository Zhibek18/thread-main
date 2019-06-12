package kz.kakimzhanova.thread.util;

public class IdGenerator {
    static int threadId = 1;
    public static int generateThreadId(){
        return threadId++;
    }
}
