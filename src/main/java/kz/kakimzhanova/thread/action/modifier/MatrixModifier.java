package kz.kakimzhanova.thread.action.modifier;

public interface MatrixModifier {

    boolean modifyMatrix(int workerId, int[] index);

    /*
     * Method modifyMatrix(int workerId, int[] index)
     * returns true if matrix was modified, otherwise returns false
     */

}
