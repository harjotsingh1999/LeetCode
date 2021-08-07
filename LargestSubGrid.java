public class LargestSubGrid {
    static final int N = 3;
    static final int M = 3;

    static int maximumSquareSize(int[][] mat, int K) {
        int[][] aux = new int[N][M];

        preProcess(mat, aux);

        // Search space
        int low = 1, high = Math.min(N, M);
        int mid;

        // Binary search for size
        while (high - low > 1) {
            mid = (low + high) / 2;

            // Check if the mid satisfies
            // the given condition
            if (check(mid, aux, K)) {
                low = mid;
            } else
                high = mid;
        }
        if (check(high, aux, K))
            return high;
        return low;
    }

    static boolean check(int mid, int[][] aux, int K) {

        boolean satisfies = true;

        // Iterating throught all possible
        // submatrices of given size
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (x + mid - 1 <= N - 1 && y + mid - 1 <= M - 1) {
                    if (sumQuery(aux, x, y, x + mid - 1, y + mid - 1) > K)
                        satisfies = false;
                }
            }
        }
        return (satisfies == true);
    }

    static int sumQuery(int[][] aux, int tli, int tlj, int rbi, int rbj) {

        // Overall sum from the top to
        // right corner of matrix
        int res = aux[rbi][rbj];

        // Removing the sum from the top
        // corer of the matrix
        if (tli > 0)
            res = res - aux[tli - 1][rbj];

        // Remove the overlapping sum
        if (tlj > 0)
            res = res - aux[rbi][tlj - 1];

        // Add the sum of top corner
        // which is substracted twice
        if (tli > 0 && tlj > 0)
            res = res + aux[tli - 1][tlj - 1];

        return res;

    }

    // Function to preprocess the matrix
    // for computing the sum of every
    // possible matrix of the given size
    static void preProcess(int[][] mat, int[][] aux) {

        // Loop to copy the first row of
        // the matrix into the aux matrix
        for (int i = 0; i < M; i++)
            aux[0][i] = mat[0][i];

        // Computing the sum column-wise
        for (int i = 1; i < N; i++)
            for (int j = 0; j < M; j++)
                aux[i][j] = mat[i][j] + aux[i - 1][j];

        // Computing row wise sum
        for (int i = 0; i < N; i++)
            for (int j = 1; j < M; j++)
                aux[i][j] += aux[i][j - 1];
    }

    public static void main(String[] args) {
        int K = 3;
        int[][] mat = { { 2, 2, 2 }, { 3, 3, 3 }, { 4, 4, 4 } };
        System.out.print(maximumSquareSize(mat, K));
    }
}
