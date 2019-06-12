package kz.kakimzhanova.thread.main;

import kz.kakimzhanova.thread.entity.Worker;
import kz.kakimzhanova.thread.report.MatrixReport;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6);
        Thread firstThread = new Worker(cyclicBarrier);
        Thread secondThread = new Worker(cyclicBarrier);
        Thread thirdThread = new Worker(cyclicBarrier);
        Thread fourthThread = new Worker(cyclicBarrier);
        Thread fifthThread = new Worker(cyclicBarrier);
        Thread sixthThread = new Worker(cyclicBarrier);

        firstThread.start();
        secondThread.start();
        thirdThread.start();
        fourthThread.start();
        fifthThread.start();
        sixthThread.start();

        try {
            firstThread.join();
            secondThread.join();
            thirdThread.join();
            fourthThread.join();
            fifthThread.join();
            sixthThread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MatrixReport report = new MatrixReport();
        report.printReport();
    }
}
