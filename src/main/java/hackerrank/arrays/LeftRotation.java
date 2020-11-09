package hackerrank.arrays;

public class LeftRotation {

    static int[] rotLeft(int[] inputArray, int positions) {
        int[] result = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            result[(i + (inputArray.length - positions)) % inputArray.length] = inputArray[i];
        }
        return result;
    }
}
