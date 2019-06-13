package kz.kakimzhanova.thread.action.finder;

import kz.kakimzhanova.thread.entity.Matrix;
import kz.kakimzhanova.thread.entity.Field;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixFieldFinder {
    private static Lock lock = new ReentrantLock();
    public Field findNextField(){
        Matrix matrix = Matrix.getInstance();
        lock.lock();
        try{
            return matrix.popListElem();
        } finally {
            lock.unlock();
        }

    }
}
