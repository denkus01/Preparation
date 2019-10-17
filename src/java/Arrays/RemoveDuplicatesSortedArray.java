package Arrays;

import java.util.Arrays;

public class RemoveDuplicatesSortedArray {

    private int[] removeDuplicates(int[] nums) {
        if (nums.length == 0) return nums;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        RemoveDuplicatesSortedArray removeDuplicatesSortedArray = new RemoveDuplicatesSortedArray();
        int[] sortedArray = {1,1,2,3,3,3,3,4,5,5,6};
        removeDuplicatesSortedArray.removeDuplicates(sortedArray);
        System.out.println(Arrays.toString(sortedArray));
    }
}
