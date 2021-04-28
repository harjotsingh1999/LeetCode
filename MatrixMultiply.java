import java.util.Arrays;

public class MatrixMultiply {

    public long[][] createMatrix(long[] arr) {
        int size = (int) Math.sqrt(arr.length);
        long[][] mat = new long[size][size];
        int pos = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                mat[i][j] = arr[pos];
                pos += 1;
            }
        }
        return mat;
    }

    public long sumMatrix(long[][] mat) {
        int num = 1000000007;
        long sum = 0L;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                sum = (sum % num + mat[i][j] % num) % num;
            }
        }
        return sum;
    }

    public void rotate(long[] arr) {
        // long[] a = new long[arr.length];
        long last = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = last;
    }

    public long[][] matrixMultiplication(long[][] matrix1, int rows1, int cols1, long[][] matrix2, int rows2,
            int cols2) {
        int num = 1000000007;

        // create a result matrix
        long resultMatrix[][] = new long[rows1][cols2];

        // Core logic for 2 matrices multiplication
        for (int i = 0; i < resultMatrix.length; i++) {
            for (int j = 0; j < resultMatrix[i].length; j++) {
                for (int k = 0; k < cols1; k++) {
                    resultMatrix[i][j] = (resultMatrix[i][j] % num + (matrix1[i][k] % num * matrix2[k][j] % num) % num)
                            % num;
                }
            }
        }
        return resultMatrix;
    }

    public static void main(String[] args) {
        MatrixMultiply matrixMultiply = new MatrixMultiply();
        int n = 5;
        long[] arr = { 2, 3, 4, 5, 6 };
        long k = 2;
        int size = (int) Math.sqrt(n);
        long[][] firstMatrix = matrixMultiply.createMatrix(arr);
        for (int i = 1; i <= k; i++) {
            matrixMultiply.rotate(arr);
            System.out.println("rotated array= " + Arrays.toString(arr));
            long[][] secondMatrix = matrixMultiply.createMatrix(arr);
            firstMatrix = matrixMultiply.matrixMultiplication(firstMatrix, firstMatrix.length, size, secondMatrix, size,
                    size);
        }
        System.out.println(matrixMultiply.sumMatrix(firstMatrix));
    }
}
