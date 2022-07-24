package leetcode.arrays;

//public class Array {
//    public int findMaxConsecutiveOnes(int[] nums) {
//        int counter = 0,total = 0;
//        for (int num : nums) {
//            if (num == 1) {
//                counter++;
//            } else {
//                if (total < counter) {
//                    total = counter;
//                }
//                counter = 0;
//            }
//        }
//        return Math.max(total, counter);
//    }
//}
//

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Array {
    public static void main(String[] args) {
        HashMap<List<Integer>, String> map = new HashMap<>();
        List<Integer> listInt = Arrays.asList(1,2,3,4);
        List<Integer> listInt1 = Arrays.asList(1,2,3,4);


        map.put(listInt, "RRRRR");
        listInt = Arrays.asList(5,5,6);

        System.out.println("result: " + map.get(listInt));
        map.put(listInt1, "AAAAA");

    }
}