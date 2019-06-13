package kz.kakimzhanova.thread.reader;

import kz.kakimzhanova.thread.entity.MatrixField;
import kz.kakimzhanova.thread.exception.WrongInputDataException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private String fileName;
    private static int N;

    public Reader (int N, String dataFileName){
        this.fileName = dataFileName;
        this.N = N;
    }
    public MatrixField[][] readMatrix() throws IOException, WrongInputDataException {
        BufferedReader br;
        br = new BufferedReader(new FileReader("/home/sam/thread/src/main/resources/" + fileName));

        MatrixField[][] matrix;
        matrix = new MatrixField[N][N];

        try {
            String tmp;
            int i = 0;
            int j = 0;
            int k;
            while ((tmp = br.readLine()) != null) {
                if (i >= N){
                    throw new WrongInputDataException ("matrix size should be "+ N +"x"+ N);
                }
                String[] s = tmp.split("\\s");
                j = 0;
                for (String res : s){
                    if (j >= N){
                        throw new WrongInputDataException ("matrix size should be "+ N +"x"+ N);
                    }
                    k = Integer.parseInt(res);
                    matrix[i][j] = new MatrixField(k, i, j);
                    j++;
                }
                i++;
            }
            if ((i < N) || (j < N)) {
                throw new WrongInputDataException ("matrix size should be "+ N +"x"+ N);
            }
            return matrix;
        }finally {
            br.close();
        }
    }
}
