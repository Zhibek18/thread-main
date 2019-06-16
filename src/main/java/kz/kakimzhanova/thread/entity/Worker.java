package kz.kakimzhanova.thread.entity;

import kz.kakimzhanova.thread.action.finder.MatrixFieldFinder;
import kz.kakimzhanova.thread.action.modificator.MatrixModificator;
import kz.kakimzhanova.thread.util.IdGenerator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Worker extends java.lang.Thread{
    private static final int WORKERS_COUNT = 6;
    private static Logger logger = LogManager.getLogger();
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(WORKERS_COUNT);
    private int threadId;

    public Worker (){
        this.threadId = IdGenerator.generateThreadId();
    }

    @Override
    public void run(){
        logger.log(Level.INFO, " " + this.threadId);
        try {
            TimeUnit.SECONDS.sleep(1);

            MatrixModificator matrixModificator = new MatrixModificator();
            MatrixFieldFinder fieldFinder = new MatrixFieldFinder();

            while (matrixModificator.modifyMatrix(this.threadId, fieldFinder.findNextField())) {
                cyclicBarrier.await(1, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            logger.log(Level.WARN, e);
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            logger.log(Level.WARN, e);
        } catch (TimeoutException e) {
            logger.log(Level.WARN, e);
        }
    }
}
