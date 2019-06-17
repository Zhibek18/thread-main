package kz.kakimzhanova.thread.modifier;

import kz.kakimzhanova.thread.entity.Matrix;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatrixModifierImpl implements MatrixModifier{
    private static Logger logger = LogManager.getLogger();

    public boolean modifyMatrix(int workerId, int[] index){ // returns true if matrix was modified, otherwise returns false
        if (index != null) {
            Matrix.getInstance().setMatrixField(index[0], index[1], workerId);
            logger.log(Level.INFO, "Worker: " + workerId + " matrix[" + index[0] + "][" + index[1] + "] was modified");
            return true;
        }
        logger.log(Level.INFO, "Worker: " + workerId + " no fields to be modified");
        return false;
    }
}
