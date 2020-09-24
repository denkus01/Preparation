package hackerrank.arrays;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class HourGlassSumTask {

    static int hourglassSum(int[][] arr) {
        int sum = 0;
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                int arrSumm = arr[i][j] + arr[i][j + 1] + arr[i][j + 2]
                        + arr[i + 1][j + 1] + arr[i + 2][j]
                        + arr[i + 2][j + 1] + arr[i + 2][j + 2];
                if (i == 0 && j == 0) {
                    sum = arrSumm;
                } else if (arrSumm > sum) {
                    sum = arrSumm;
                }
            }
        }
        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int max = 100;
        int min = 0;
        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = (int) ((Math.random() * ++max) + min);
                ;
            }
        }

        int result = hourglassSum(arr);

        System.out.print("Array: " + Arrays.deepToString(arr));
        System.out.println("result: " + result);
    }
}
