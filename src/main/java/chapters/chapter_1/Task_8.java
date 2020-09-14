package chapters.chapter_1;

public class Task_8 {
    public static void main(String[] args) {
        int[][] matrix = {
                { 0, 2, 3, 4 },
                { 5, 0, 7, 8 },
                { 9, 10, 1, 12 },
                { 13, 14, 15, 16 },
        };

        printMatrix(nullify(matrix));
    }

    // Complexity: n^2 + n^2 -> O(n^2), Memory: n + n + const -> O(n)
    private static int[][] nullify(int[][] matrix) {
        int n = matrix.length;
        // We can eliminate additional arrays by using first row and column for storing mark about nullification
        boolean[] rows = new boolean[n];
        boolean[] columns = new boolean[n];

        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                if (matrix[row][column] == 0) {
                    rows[row] = true;
                    columns[column] = true;
                }
            }
        }

        for (int row = 0; row < n; row++) {
            if (rows[row]) {
                matrix[row] = new int[n];
            } else {
                for (int column = 0; column < n; column++) {
                    if (columns[column]) {
                        matrix[row][column] = 0;
                    }
                }
            }
        }

        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] i : matrix) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
