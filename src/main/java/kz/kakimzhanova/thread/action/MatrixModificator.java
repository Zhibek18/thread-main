package kz.kakimzhanova.thread.action;

import kz.kakimzhanova.thread.entity.Matrix;
import kz.kakimzhanova.thread.entity.MatrixField;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixModificator {
    private static Logger logger = LogManager.getLogger();
    private static Lock lock = new ReentrantLock();

    public boolean modifyMatrix(int threadId, MatrixField field){ // returns true if matrix was modified, otherwise returns false

        Matrix matrix = Matrix.getInstance();
        if ((field != null) && (!field.isModified())){
            field.setElem(threadId);
            field.setModified(true);
            field.setModifiedBy(threadId);
            logger.log(Level.INFO, " " + threadId + " matrix[" + field.getI() + "][" + field.getJ() + "] was modified");
            return true;
        }
        logger.log(Level.INFO, " " + threadId + " no fields to be modified");
        return false;
    }

    public MatrixField findNextField(){
        Matrix matrix = Matrix.getInstance();
        lock.lock();
        try{
           return matrix.popListElem();
        } finally {
            lock.unlock();
        }

    }
}
