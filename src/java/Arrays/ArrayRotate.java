package Arrays;

import java.util.Arrays;

public class ArrayRotate {
    private static final int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8};

    private static int[] leftRotate(int[] arr, int n) {
        int[] resultArray = Arrays.copyOf(arr,arr.length);
        for (int i = 0; i < n; i++) {
            int j, first;
            first = resultArray[0];
            for (j = 0; j < resultArray.length - 1; j++) {
                resultArray[j] = resultArray[j + 1];
            }
            resultArray[j] = first;
        }
        return resultArray;
    }

    private  static int[] rightRotate(int[] array, int n) {
        if (n < 0) // rotating left?
        {
            n = -n % array.length; // convert to +ve number specifying how many positions left to rotate & mod
            n = array.length - n;  // rotate left by n = rotate right by length - n
        }
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[(i + n) % array.length] = array[i];
        }
        return result;

    }

    public static void main(String[] args) {

        int[] resultLeftRotate = leftRotate(intArray, 2);
        System.out.println("\n Left rotate :");
        Arrays.stream(resultLeftRotate).forEach(System.out::print);

        int[] resultRightRotate = rightRotate(intArray, -2);
        System.out.println("\n Right rotate :");
        Arrays.stream(resultRightRotate).forEach(System.out::print);
    }
}