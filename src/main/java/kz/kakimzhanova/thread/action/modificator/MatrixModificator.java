package kz.kakimzhanova.thread.action.modificator;

import kz.kakimzhanova.thread.entity.Field;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatrixModificator {
    private static Logger logger = LogManager.getLogger();

    public boolean modifyMatrix(int threadId, Field field){ // returns true if matrix was modified, otherwise returns false
        if ((field != null) && (!field.isModified())){
            field.setElem(threadId);
            field.setModified(true);
            field.setModifiedBy(threadId);
            logger.log(Level.INFO, "Worker: " + threadId + " matrix[" + field.getI() + "][" + field.getJ() + "] was modified");
            return true;
        }
        logger.log(Level.INFO, "Worker: " + threadId + " no fields to be modified");
        return false;
    }
}
