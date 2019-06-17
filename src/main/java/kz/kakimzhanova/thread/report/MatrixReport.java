package kz.kakimzhanova.thread.report;

import kz.kakimzhanova.thread.entity.Matrix;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatrixReport {
    private static Logger logger = LogManager.getLogger();
    public void printReport(){
        logger.log(Level.INFO, "Matrix[i][j]: value(workerId)" + Matrix.getInstance().toString());
    }
}
