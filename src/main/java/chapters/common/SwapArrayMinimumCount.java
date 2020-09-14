package chapters.common;

public class SwapArrayMinimumCount {

    private static int minimumSwaps(int[] array) {

        int n = array.length - 1;
        int minSwaps = 0;
        for (int i = 0; i < n; i++) {
            if (i < array[i] - 1) {
                swap(array, i, Math.min(n, array[i] - 1));
                minSwaps++;
                i--;
            }
        }
        return minSwaps;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] inputData = {7, 1, 4, 3, 2, 5, 6};
        int countOfSwap = SwapArrayMinimumCount.minimumSwaps(inputData);
        System.out.println("It needs:" + countOfSwap + " times");
    }

}
