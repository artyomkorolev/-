package ru.vsu.cs.korolevae;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import static ru.vsu.cs.util.ArrayUtils.readIntArray2FromFile;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
       int[][] arr = readIntArray2FromFile("test");
        PrintStream out= new PrintStream("result");
        out.print(matrixDet(arr));

        out.close();
        System.out.println (ru.vsu.cs.util.ArrayUtils.toString( obratMatrix(dopMatrix(transponMatrix(arr)),matrixDet(arr))));
        System.out.println("--------------------------------------------------------------------");
        System.out.println (ru.vsu.cs.util.ArrayUtils.toString(proverka(arr,obratMatrix(dopMatrix(transponMatrix(arr)),matrixDet(arr))))) ;
    }


    //находит детерминант матрицы
    public static int matrixDet(int [][]matrix) {
        int size= matrix.length;
        int det = 0;
        if(size == 1) {
            return matrix[0][0];
        }
        else if(size == 2) {
            return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        }
        else {
            int [][]newMatrix = new int[size-1][size-1];
            for(int j = 0; j < size; j++) {
                getMatrixWithoutRowAndCol(matrix, size, 0, j, newMatrix);
                det = (int) (det + (Math.pow(-1,j) * matrix[0][j] * matrixDet(newMatrix)));
            }
        }
        return det;
    }

    public static void getMatrixWithoutRowAndCol(int [][]matrix, int size, int row, int col, int [][]newMatrix) {
        int offsetRow = 0;
        int offsetCol = 0;
        for(int i = 0; i < size-1; i++) {
            if(i == row) {
                offsetRow = 1;
            }
            offsetCol = 0;
            for(int j = 0; j < size-1; j++) {

                if(j == col) {
                    offsetCol = 1;
                }
                newMatrix[i][j] = matrix[i + offsetRow][j + offsetCol];
            }
        }
    }
    public static int [][] transponMatrix(int [][] matrix){
        int [][] matrix1= new int [matrix.length][matrix[0].length];
        for (int i = 0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                matrix1[i][j]=matrix[j][i];
            }
        }
    return matrix1;
    }
    public static int [][] dopMatrix( int [][] matrix){
        int [][] matrix1= new int [matrix.length][matrix[0].length];
        int [][] matrix2= new int [matrix.length-1][matrix[0].length-1];
        for (int i = 0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                getMatrixWithoutRowAndCol(matrix,matrix.length,i,j,matrix2);
                matrix1[i][j]= (int)(Math.pow(-1,j+i)  * matrixDet(matrix2 ) );

            }
        }

        return matrix1;
    }

    public static double [][] obratMatrix(int [][] matrix, int detMatrix){
        double [][] matrix1= new double [matrix.length][matrix[0].length];
        for (int i = 0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++) {
                matrix1[i][j]=(double)matrix[i][j]/detMatrix;
            }
            }



        return matrix1;
    }

    public static int [][] proverka(int [][]matrix,double [][] obratmatrix){
        int [][] mama= new int [matrix.length][matrix.length];
        int m = matrix.length;
        int n = obratmatrix[0].length;
        int o = obratmatrix.length;
        double[][] res = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < o; k++) {
                    res[i][j] += matrix[i][k] * obratmatrix[k][j];
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mama[i][j]=(int)res[i][j];
            }
            }



        return mama;
    }
}


