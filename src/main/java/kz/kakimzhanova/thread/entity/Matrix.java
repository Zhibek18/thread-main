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

    private static final int N = 5;
    private static Matrix instance;
    private MatrixField [][] matrix;
    private static Lock lock = new ReentrantLock(true);
    private static AtomicBoolean created = new AtomicBoolean(false);

    private Matrix(){
        matrix = new MatrixField[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = new MatrixField(0);
            }
        }
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

    public void setValue(int i, int j, int threadId) {
        matrix[i][j].setElem(threadId);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            logger.log(Level.WARN, e);
        }
    }

    public int getValue(int i, int j){
        return matrix[i][j].getElem();
    }

    public int getSize(){
        return matrix.length;
    }

    public boolean isModified(int i, int j) {
        lock.lock();
        try {
            return matrix[i][j].isModified();
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
            s.append(isModified(i,i));
            s.append(" ");
        }
        s.append("\n");
        return s.toString();
    }
}
