package kz.kakimzhanova.thread.entity;

import kz.kakimzhanova.thread.modifier.MatrixModifier;
import kz.kakimzhanova.thread.util.IdGenerator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Worker extends Thread{
    private static final int WORKERS_COUNT = 6;
    private static Logger logger = LogManager.getLogger();
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(WORKERS_COUNT);
    private int workerId;

    public Worker (){
        this.workerId = IdGenerator.generateWorkerId();
    }

    @Override
    public void run(){
        logger.log(Level.INFO, " " + this.workerId + " started");

        try {
            TimeUnit.SECONDS.sleep(1);
            Matrix matrix = Matrix.getInstance();
            MatrixModifier matrixModifier = new MatrixModifier();
            while (matrixModifier.modifyMatrix(this.workerId, matrix.findNextIndex())) {
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
