package kz.kakimzhanova.thread.util;

public class IdGenerator {
    private static int threadId = 1;

    private IdGenerator(){ }
    public static int generateThreadId(){
        return threadId++;
    }
}
