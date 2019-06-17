package kz.kakimzhanova.thread.entity;

import java.util.ArrayList;
import java.util.List;

public class FieldsQueue {
    private List<Field> matrixFieldList = new ArrayList<Field>();

    public FieldsQueue(){
        Matrix matrix = Matrix.getInstance();
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = 0; j < matrix.getSize(); j++){
                matrixFieldList.add(new Field(matrix.getValue(i,j), i, j));
            }
        }
    }

    public Field popNextField(){
        Field field = null;
        if (!matrixFieldList.isEmpty()) {
            field = matrixFieldList.get(0);
            matrixFieldList = matrixFieldList.subList(1, matrixFieldList.size());
        }
        return field;
    }
}
