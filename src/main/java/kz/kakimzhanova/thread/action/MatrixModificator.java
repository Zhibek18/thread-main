package kz.kakimzhanova.thread.action;

import kz.kakimzhanova.thread.entity.Matrix;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixModificator {
    private static Logger logger = LogManager.getLogger();
    private static Lock lock = new ReentrantLock();

    public boolean modifyMatrix(int threadId){ // returns true if matrix was modified, otherwise returns false

        Matrix matrix = Matrix.getInstance();
        lock.lock();
        try {
            for (int i = 0; i < matrix.getSize(); i++) {
                if (!matrix.isModified(i, i)) {
                    matrix.setValue(i, i, threadId);
                    logger.log(Level.INFO, " " + threadId + " matrix[" + i + "][" + i + "] was modified");
                    return true;
                }
            }
            logger.log(Level.INFO, " " + threadId + " no fields to be modified");
            return false;
        } finally {
        lock.unlock();
        }
    }
}
