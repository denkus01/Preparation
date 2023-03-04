package leetcode_new_approach;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class September {

    /**
     * 58. Length of Last Word
     * 29.08.2022
     * Given a string s consisting of words and spaces, return the length of the last word in the string.
     */

    public int lengthOfLastWord(String s) {
        int p = s.length();
        int length = 0;

        while (p > 0) {
            p--;
            System.out.println(s.charAt(p));
            if (s.charAt(p) != ' ') {
                length++;
            } else if (length > 0) {
                return length;
            }
        }
        return length;
    }

    /**
     * 13. Roman to Integer
     * 30/08/2022
     * O(1)
     * O(1)
     */
    private static final Map<Character, Integer> map = new HashMap<Character, Integer>() {
        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };

    public int romanToInt(String s) {
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            int firstNum = map.get(s.charAt(i));

            if (i + 1 != s.length()) {
                int secondNum = map.get(s.charAt(i + 1));

                if (secondNum > firstNum) {
                    sum += secondNum - firstNum;

                    i++;
                } else {
                    sum += firstNum;
                }
            } else {
                sum += firstNum;
            }
        }

        return sum;
    }

    /**
     * 344. Reverse String
     * O(n) O(1) - Space
     */
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            System.out.println(s);
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }
//--------------------------------------------31.08.2022------------------------------

    /**
     * 198. House Robber
     */
    public int rob(int[] nums) {

        int N = nums.length;

        // Special handling for empty array case.
        if (N == 0) {
            return 0;
        }

        int robNext = nums[N - 1];
        int robNextPlusOne = 0;

        // DP table calculations. Note: we are not using any
        // table here for storing values. Just using two
        // variables will suffice.
        for (int i = N - 2; i >= 0; --i) {
            int data = nums[i];
            // Same as the recursive solution.
            int current = Math.max(robNext, robNextPlusOne + data);

            // Update the variables
            robNextPlusOne = robNext;
            robNext = current;
        }

        return robNext;
    }

//----------------------------------02.09.2022

    /**
     * 238. Product of Array Except Self
     * Given an integer array nums, return an array answer such that answer[i]
     * is equal to the product of all the elements of nums except nums[i].
     */
    public int[] productExceptSelf(int[] nums) {
            int len = nums.length;
            int[] result = new int[len];

            result[0] = 1;
            for (int i = 1; i < len; i++) {
                result[i] = result[i - 1] * nums[i - 1];
            }

            int right = 1;
            for (int i = len - 1; i >= 0; i--) {
                result[i] *= right;
                right *= nums[i];
            }

            return result;
        }
// ------- 03.09.2022

    /**
     * 48. Rotate Image
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i++) {
            System.out.println("n;" + (n + 1) / 2);
            for (int j = 0; j < n / 2; j++) {
                System.out.println("n2: " + n / 2);
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

    /**
     * 134. Gas Station
     * O(n)
     * O(1)
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curGas = 0;
        int totalGas = 0;
        int star = 0;

        for (int i = 0; i < gas.length; i++) {
            final int curGas1 = gas[i] - cost[i];
            curGas += curGas1;
            totalGas += curGas1;

            if (curGas < 0) {
                star = i + 1;
                curGas = 0;
            }

        }
        return totalGas >= 0 ? star : -1;
    }

    /**
     * 390. Elimination Game
     * log (n)
     */
    public int lastRemaining(int n) {
        int head = 1;
        int res = n;
        boolean left = true;
        int step = 1;

        while (res > 1) {
            if (left || res % 2 == 1) {
                head += step;
            }
            res /= 2;
            step *= 2;
            left = !left;
        }
        return head;
    }

//----------05.09.2022

    /**
     * 846. Hand of Straights
     * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize,
     * return true if she can rearrange the cards, or false otherwise.
     * O(n log n)
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        if (hand.length % groupSize != 0) return false;

        for (int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
        }

        while (map.size() > 0) {
            int val = map.firstKey();
            for (int i = val; i < val + groupSize; i++) {
                if (!map.containsKey(i)) {
                    return false;
                }
                map.put(i, map.get(i) - 1);

                if (map.get(i) == 0) {
                    map.remove(i);
                }
            }
        }

        return true;
    }

    /**
     * 1833. Maximum Ice Cream Bars
     */
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        for (int coin : costs) {
            if (coins - coin >= 0) {
                coins = coins - coin;
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    //--12.09.2022------

    /**
     * 953. Verifying an Alien Dictionary
     * O(M) where M total characters in the word
     * O(1)
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] orderMap = new int[26];
        for (int i = 0; i < order.length(); i++) {
            orderMap[order.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < words.length - 1; i++) {

            for (int j = 0; j < words[i].length(); j++) {
                // If we do not find a mismatch letter between words[i] and words[i + 1],
                // we need to examine the case when words are like ("apple", "app").
                if (j >= words[i + 1].length()) return false;

                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    int current = words[i].charAt(j) - 'a';
                    int next = words[i + 1].charAt(j) - 'a';
                    if (orderMap[current] > orderMap[next]) {
                        return false;
                    }
                    // if we find the first different letter and they are sorted,
                    // then there's no need to check remaining letters
                    else break;
                }
            }
        }

        return true;
    }

    /**
     * Kth Largest Element in an Array
     * Input: nums = [3,2,1,5,6,4], k = 2
     * Output: 5
     * O(N)
     * O(1)
     */

    public int findKthLargest(int[] nums, int k) {
        int start = 0, end = nums.length - 1;
        k = nums.length - k;
        return kthLargest(nums, start, end, k);
    }

    private int kthLargest(int[] arr, int start, int end, int k) {
        //if (k > end) return -1;
        //if (start == end) return arr[k];

        int pivotIndex = start + (end - start) / 2;

        int pivot = arr[pivotIndex];
        int left = start, right = end;

        while (left <= right) {
            if (arr[left] < pivot) {
                left++;
                continue;
            }
            if (arr[right] > pivot) {
                right--;
                continue;
            }
            swap(arr, left, right);
            left++;
            right--;
        }

        if (k <= right) {
            return kthLargest(arr, start, right, k);
        } else if (k >= left)
            return kthLargest(arr, left, end, k);
        else
            return arr[k];
    }

    private void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }


}

