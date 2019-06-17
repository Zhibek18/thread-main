package kz.kakimzhanova.thread.util;

public class IdGenerator {
    private static int workerId = 1;

    private IdGenerator(){ }
    public static int generateWorkerId(){
        return workerId++;
    }
}
