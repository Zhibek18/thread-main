package kz.kakimzhanova.thread.entity;

import kz.kakimzhanova.thread.exception.WrongInputDataException;
import kz.kakimzhanova.thread.reader.Reader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Matrix {
    private static Logger logger = LogManager.getLogger();
    private static Matrix instance;
    private static Lock lock = new ReentrantLock(true);
    private static AtomicBoolean created = new AtomicBoolean(false);

    private Field[][] numbers;

    private Matrix() {
        Reader reader = new Reader("res.txt");
        try {
            numbers = reader.readMatrix();
            int size = numbers[0].length;
            if  ((size > 12) || (size < 8))
                throw new WrongInputDataException("Matrix size N should be 8 <= N <= 12");
        } catch (WrongInputDataException e) {
            logger.log(Level.WARN, e);
        } catch (IOException e) {
            logger.log(Level.WARN, e);
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

    public int getValue(int i, int j){
        return numbers[i][j].getElem();
    }

    public int getSize() {
        if (numbers != null) {
            return numbers.length;
        } else {
            return 0;
        }
    }

    public void setMatrixField(int i, int j, int workerId){
        numbers[i][j].setElem(workerId);
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\n");
        for (int i = 0; i < this.getSize(); i++){
            for (int j = 0; j < this.getSize(); j++) {
                s.append(numbers[i][j]);
                s.append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
