package kz.kakimzhanova.thread.reader;

import kz.kakimzhanova.thread.entity.Field;
import kz.kakimzhanova.thread.exception.WrongInputDataException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private static final String WRONG_INPUT_MSG =  "Matrix size should be ";
    private String fileName;

    public Reader ( String dataFileName){
        this.fileName = dataFileName;
    }
    public Field[][] readMatrix() throws IOException, WrongInputDataException {
        BufferedReader br;
        br = new BufferedReader(new FileReader("/home/sam/thread/src/main/resources/" + fileName));
        try {
            int size = Integer.parseInt(br.readLine());
            Field[][] matrix;
            matrix = new Field[size][size];
            String tmp;
            int i = 0;
            int j = 0;
            int k;
            while ((tmp = br.readLine()) != null) {
                if (i >= size){
                    throw new WrongInputDataException (WRONG_INPUT_MSG+ size +"x"+ size);
                }
                String[] s = tmp.split("\\s");
                j = 0;
                for (String res : s){
                    if (j >= size){
                        throw new WrongInputDataException (WRONG_INPUT_MSG+ size +"x"+ size);
                    }
                    k = Integer.parseInt(res);
                    matrix[i][j] = new Field(k, i, j);
                    j++;
                }
                i++;
            }
            if ((i < size) || (j < size)) {
                throw new WrongInputDataException (WRONG_INPUT_MSG+ size +"x"+ size);
            }
            return matrix;
        }finally {
            br.close();
        }
    }
}
