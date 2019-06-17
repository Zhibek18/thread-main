package kz.kakimzhanova.thread.finder;

import kz.kakimzhanova.thread.entity.Field;
import kz.kakimzhanova.thread.entity.FieldsQueue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IndexFinderImpl implements IndexFinder {
    private static FieldsQueue fieldsQueue = new FieldsQueue();
    private static Lock lock = new ReentrantLock(true);

    public int[] findNextIndex(){
        lock.lock();
        try {
            int[] index = null;
            Field field = fieldsQueue.popNextField();
            if (field != null) {
                index = new int[2];
                index[0] = field.getI();
                index[1] = field.getJ();
            }
            return index;
        }finally {
            lock.unlock();
        }
    }
}
