package kz.kakimzhanova.thread.main;

import kz.kakimzhanova.thread.entity.Worker;
import kz.kakimzhanova.thread.report.MatrixReport;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CyclicBarrier;


public class Main {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6);
        Thread firstThread = new Worker(cyclicBarrier);
        Thread secondThread = new Worker(cyclicBarrier);
        Thread thirdThread = new Worker(cyclicBarrier);
        Thread fourthThread = new Worker(cyclicBarrier);
        Thread fifthThread = new Worker(cyclicBarrier);
        Thread sixthThread = new Worker(cyclicBarrier);
        try{
            firstThread.start();
            secondThread.start();
            thirdThread.start();
            fourthThread.start();
            fifthThread.start();
            sixthThread.start();

            firstThread.join();
            secondThread.join();
            thirdThread.join();
            fourthThread.join();
            fifthThread.join();
            sixthThread.join();

        } catch (InterruptedException e) {
            logger.log(Level.WARN, e);
        }
        MatrixReport report = new MatrixReport();
        report.printReport();
    }
}
