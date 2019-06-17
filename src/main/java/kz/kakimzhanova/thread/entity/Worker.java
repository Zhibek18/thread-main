package kz.kakimzhanova.thread.entity;

import kz.kakimzhanova.thread.action.finder.IndexFinder;
import kz.kakimzhanova.thread.action.finder.IndexFinderImpl;
import kz.kakimzhanova.thread.action.modifier.MatrixModifier;
import kz.kakimzhanova.thread.action.modifier.MatrixModifierImpl;
import kz.kakimzhanova.thread.exception.WrongWorkersCountException;
import kz.kakimzhanova.thread.util.IdGenerator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Worker extends Thread{
    private static final int MIN_FIELDS_COUNT = 2;
    private static final int WORKERS_COUNT = 6;
    private static Logger logger = LogManager.getLogger();
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(WORKERS_COUNT);
    private int workerId;

    public Worker () throws WrongWorkersCountException{
        this.workerId = IdGenerator.generateWorkerId();
        if ((WORKERS_COUNT >  6) || (WORKERS_COUNT < 4)){
            throw new WrongWorkersCountException("Workers count M should be 4 <= M <= 6");
        }
    }

    @Override
    public void run(){
        try {
            logger.log(Level.INFO, " " + this.workerId + " started");
            int counter = 0;

            cyclicBarrier.await(1, TimeUnit.SECONDS);
            MatrixModifier matrixModifier = new MatrixModifierImpl();
            IndexFinder indexFinder = new IndexFinderImpl();
            while (matrixModifier.modifyMatrix(this.workerId, indexFinder.findNextIndex())) {
                counter++;
                if (counter <= MIN_FIELDS_COUNT) {
                    cyclicBarrier.await(2, TimeUnit.SECONDS);
                }
            }
        } catch (InterruptedException e) {
            logger.log(Level.WARN, " " + this.workerId + " " + e);
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            logger.log(Level.WARN, " " + this.workerId + " " + e);
        } catch (TimeoutException e) {
            logger.log(Level.WARN, " " + this.workerId + " " + e);
        }
    }
}
