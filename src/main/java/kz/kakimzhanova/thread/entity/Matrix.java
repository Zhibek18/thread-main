package kz.kakimzhanova.thread.entity;

import kz.kakimzhanova.thread.exception.WrongInputDataException;
import kz.kakimzhanova.thread.reader.Reader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Matrix {
    private static Logger logger = LogManager.getLogger();
    private static final int N = 5;
    private static Matrix instance;
    private static Lock lock = new ReentrantLock(true);
    private static AtomicBoolean created = new AtomicBoolean(false);

    private Field[][] numbers;
    private List<Field> matrixFieldList = new ArrayList<Field>();

    private Matrix() {
        Reader reader = new Reader( N, "res.txt");
        try {
            numbers = reader.readMatrix();
            setList();
        } catch (WrongInputDataException e) {
            logger.log(Level.WARN, e);
        } catch (IOException e) {
            logger.log(Level.WARN, e);
        }
    }

    private void setList(){
        for (int i = 0; i < N; i++) {
            Collections.addAll(matrixFieldList,numbers[i]);
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

    private int getValue(int i, int j){
        return numbers[i][j].getElem();
    }

    private int getSize() {
        if (numbers != null) {
            return numbers.length;
        } else {
            return 0;
        }
    }

    int[] findNextIndex(){
        lock.lock();
        try {
            int[] index = null;
            Field field;
            if (!matrixFieldList.isEmpty()) {
                field = matrixFieldList.get(0);
                matrixFieldList = matrixFieldList.subList(1, matrixFieldList.size());
                index = new int[2];
                index[0] = field.getI();
                index[1] = field.getJ();
            }
            return index;
        }finally {
            lock.unlock();
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
                s.append(instance.getValue(i, j));
                s.append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
