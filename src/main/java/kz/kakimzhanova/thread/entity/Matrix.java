package kz.kakimzhanova.thread.entity;

import kz.kakimzhanova.thread.exception.WrongInputDataException;
import kz.kakimzhanova.thread.reader.Reader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Matrix {
    private static Logger logger = LogManager.getLogger();

    private static final int N = 5;
    private static Matrix instance;
    private Field[][] matrix;
    private List<Field> matrixFieldList = new ArrayList<Field>();
    private static Lock lock = new ReentrantLock(true);
    private static AtomicBoolean created = new AtomicBoolean(false);

    private Matrix() {
        Reader reader = new Reader( N, "res.txt");
        try {
            matrix = reader.readMatrix();
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    logger.log(Level.DEBUG, matrix[i][j]);
                }
            }

            setList();
        } catch (WrongInputDataException e) {
            logger.log(Level.WARN, e);
        } catch (IOException e) {
            logger.log(Level.WARN, e);
        }
    }

    private void setList(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrixFieldList.add(matrix[i][j]);
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

    public int getValue(int i, int j){
        return matrix[i][j].getElem();
    }

    public int getSize() {
        if (matrix != null) {
            return matrix.length;
        } else {
            return 0;
        }
    }

    public Field popListElem(){
        Field res = null;
        if (!matrixFieldList.isEmpty()) {
            res = matrixFieldList.get(0);
            matrixFieldList = matrixFieldList.subList(1, matrixFieldList.size());
        }
        return res;
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
