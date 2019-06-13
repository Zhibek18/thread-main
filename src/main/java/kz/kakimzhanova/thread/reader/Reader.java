package kz.kakimzhanova.thread.reader;

import kz.kakimzhanova.thread.entity.Field;
import kz.kakimzhanova.thread.exception.WrongInputDataException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private String fileName;
    private static int size;

    public Reader (int n, String dataFileName){
        this.fileName = dataFileName;
        size = n;
    }
    public Field[][] readMatrix() throws IOException, WrongInputDataException {
        BufferedReader br;
        br = new BufferedReader(new FileReader("/home/sam/thread/src/main/resources/" + fileName));

        Field[][] matrix;
        matrix = new Field[size][size];

        try {
            String tmp;
            int i = 0;
            int j = 0;
            int k;
            while ((tmp = br.readLine()) != null) {
                if (i >= size){
                    throw new WrongInputDataException ("matrix size should be "+ size +"x"+ size);
                }
                String[] s = tmp.split("\\s");
                j = 0;
                for (String res : s){
                    if (j >= size){
                        throw new WrongInputDataException ("matrix size should be "+ size +"x"+ size);
                    }
                    k = Integer.parseInt(res);
                    matrix[i][j] = new Field(k, i, j);
                    j++;
                }
                i++;
            }
            if ((i < size) || (j < size)) {
                throw new WrongInputDataException ("matrix size should be "+ size +"x"+ size);
            }
            return matrix;
        }finally {
            br.close();
        }
    }
}
