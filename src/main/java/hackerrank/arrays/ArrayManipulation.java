package hackerrank.arrays;

import java.util.Arrays;

/**
 * Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value to each of the array
 * element between two given indices, inclusive. Once all operations have been performed, return the maximum value in
 * your array.
 * For example, the length of your array of zeros n = 10. Your list of queries is as follows:
 * a b k
 * 1 5 3
 * 4 8 7
 * 6 9 1
 * <p>
 * Add the values of between the indices and inclusive:
 * index->
 * 1  2  3  4  5  6  7  8  9  10
 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
 * [3, 3, 3, 3, 3, 0, 0, 0, 0, 0]
 * [3, 3, 3,10,10, 7, 7, 7, 0, 0]
 * [3, 3, 3,10,10, 8, 8, 8, 1, 0]
 */

public class ArrayManipulation {

    static long arrayManipulation(int n, int[][] queries) {
        long[] array = new long[n + 1];

        for (int[] query : queries) {
            int a = query[0], b = query[1], k = query[2];
            array[a] += k;
            if (b + 1 <= n) {
                array[b + 1] -= k;
            }
        }
        long x = 0, max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            x += array[i];
            if (max < x) max = x;
        }
        return max;
    }


    public static void main(String[] args) {
        int[][] arr = {{1, 5, 3}, {4, 8, 7}, {6, 9, 1}};

        int result = (int) arrayManipulation(10, arr);

        System.out.print("Array: " + Arrays.deepToString(arr));
        System.out.println("result: " + result);
    }
}
