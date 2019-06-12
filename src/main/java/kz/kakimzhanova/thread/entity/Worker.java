package kz.kakimzhanova.thread.entity;

import kz.kakimzhanova.thread.action.MatrixModificator;
import kz.kakimzhanova.thread.util.IdGenerator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class Worker extends java.lang.Thread{
    private static Logger logger = LogManager.getLogger();
    private static CyclicBarrier cyclicBarrier;
    private static AtomicBoolean finish = new AtomicBoolean(false);
    private int threadId;

    public Worker (CyclicBarrier barrier){
        this.threadId = IdGenerator.generateThreadId();
        this.cyclicBarrier = barrier;
    }

    public void run(){
        logger.log(Level.INFO, " " + this.threadId);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            logger.log(Level.WARN, e);
        }
        MatrixModificator matrixModificator = new MatrixModificator();
        while (!finish.get()) {
            finish.set(!matrixModificator.modifyMatrix(this.threadId));
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                logger.log(Level.WARN, e);
            } catch (BrokenBarrierException e) {
                logger.log(Level.WARN, e);
            }
        }
    }
}
