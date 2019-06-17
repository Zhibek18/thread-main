package kz.kakimzhanova.thread.main;

import kz.kakimzhanova.thread.entity.Worker;
import kz.kakimzhanova.thread.exception.WrongWorkersCountException;
import kz.kakimzhanova.thread.report.MatrixReport;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        try{
            Thread firstThread = new Worker();
            Thread secondThread = new Worker();
            Thread thirdThread = new Worker();
            Thread fourthThread = new Worker();
            Thread fifthThread = new Worker();
            Thread sixthThread = new Worker();

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
            Thread.currentThread().interrupt();
        } catch (WrongWorkersCountException e) {
            logger.log(Level.WARN, e);
        }
        MatrixReport report = new MatrixReport();
        report.printReport();
    }
}
