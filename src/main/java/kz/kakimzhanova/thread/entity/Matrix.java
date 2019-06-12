package kz.kakimzhanova.thread.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Matrix {
    private static Logger logger = LogManager.getLogger();

    private final static int N = 5;
    private static Matrix instance;
    private int[][] matrix;
    private boolean [] modified;
    private static Lock lock = new ReentrantLock(true);
    private static AtomicBoolean created = new AtomicBoolean(false);

    private Matrix(){
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = 0;
            }
        }
        modified = new boolean[N];
    }

    public static Matrix getInstance(){
        if (!created.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Matrix();
                    created.set(true);

                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public void setValue(int i, int j, int value){
        lock.lock();
        try {
            matrix[i][j] = value;
            modified[i] = true;
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            logger.log(Level.WARN, e);
        }finally {
            lock.unlock();
        }
    }

    public int getValue(int i, int j){
        return matrix[i][j];
    }

    public int getSize(){
        return matrix.length;
    }

    public boolean isModified(int i) {
        lock.lock();
        try {
            //return modified[i].get();
            return modified[i];
        }finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\n");
        for (int i = 0; i < this.getSize(); i++){
            for (int j = 0; j < this.getSize(); j++) {
                s.append(instance.getValue(i, j));
                s.append(" ");
            }
            s.append("\n");
        }
        s.append("modified: ");
        for (int i = 0; i < this.getSize(); i++){
            s.append(isModified(i));
            s.append(" ");
        }
        s.append("\n");
        return s.toString();
    }
}
