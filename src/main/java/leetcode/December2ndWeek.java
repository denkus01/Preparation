package leetcode;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.PriorityQueue;

public class December2ndWeek {

    /**
     * 171. Excel Sheet Column Number
     * O n
     * O 1
     */

    public int titleToNumber(String columnTitle) {
        int sum = 0;
        for (char c : columnTitle.toCharArray()) {
            int digit = c - 'A' + 1;
            sum = sum * 26 + digit;
        }
        return sum;
    }

    /**
     * Binary Search
     */
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) return mid;

            else if (target > nums[mid]) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    /**
     * 278. First Bad Version
     * O log n
     * O 1
     */
    public int firstBadVersion(int n) {
        int left = 1;
//        int right = n;
//        while (left < right) {
//            int mid = left + (right - left) / 2;
//            if (isBadVersion(mid)) {
//                right = mid;
//            } else {
//                left = mid + 1;
//            }
//        }
        return left;
    }

    /**
     * 35. Search Insert Position
     * O log n
     * O 1
     */
    public int searchInsert(int[] nums, int target) {
        int pivot, left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (nums[pivot] == target) return pivot;
            if (target < nums[pivot]) {
                right = pivot - 1;
            } else {
                left = pivot + 1;
            }
        }
        return left;
    }


    /**
     * 977. Squares of a Sorted Array
     * O n
     * O n
     */

    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        int left = 0;
        int right = len - 1;

        for (int i = len - 1; i >= 0; i--) {
            int square;
            if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                square = nums[right];
                right--;
            } else {
                square = nums[left];
                left++;
            }
            result[i] = square * square;
        }
        return result;
    }

    /**
     * 189. Rotate Array
     * Input: nums = [1,2,3,4,5,6,7], k = 3
     * Output: [5,6,7,1,2,3,4]
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int l = 0, r = nums.length - 1;
        extracted(nums, l, r);

        extracted(nums, l, r = k - 1);

        extracted(nums, k, nums.length - 1);
    }

    private static void extracted(int[] nums, int l, int r) {
        while (l < r) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }

    /**
     * 283. Move Zeroes
     * O n
     * O 1
     */
    public void moveZeroes(int[] nums) {
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] != 0) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                l++;
            }
        }
    }

    /**
     * 167. Two Sum II - Input Array Is Sorted
     * Input: numbers = [2,7,11,15], target = 9
     * Output: [1,2]
     * O n
     * O 1
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        int[] res = new int[]{-1, -1};
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                return res;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }


    /**
     * 74. Search a 2D Matrix
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;
        int columns = matrix[0].length;


        int left = 0, right = rows * columns - 1;
        int pivotIdx, pivotElement;

        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / columns][pivotIdx % columns];
            if (target == pivotElement) {
                return true;
            } else {
                if (target < pivotElement) {
                    right = pivotIdx - 1;
                } else {
                    left = pivotIdx + 1;
                }

            }
        }
        return false;
    }


    public void reverseString(char[] s) {
        int left = 0, right = s.length + 1;

        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 186. Reverse Words in a String II
     * O n
     * O 1
     */
    public void reverseWords(char[] s) {
        int len = s.length;
        int start = 0, end = 0;

        reverse(s, 0, s.length - 1);

        while (start < len) {
            while (end < len && s[end] != ' ') {
                ++end;
            }
            reverse(s, start, end - 1);
            start = end + 1;
            ++end;
        }
    }

    public void reverse(char[] s, int left, int right) {
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }

    /**
     * 557. Reverse Words in a String III
     * O n
     * O 1
     */
    public String reverseWords(String s) {
        int lastSpaceIndex = -1;
        char[] chars = s.toCharArray();
        int len = s.length();
        for (int i = 0; i <= len; i++) {
            if (i == len || chars[i] == ' ') {
                int left = lastSpaceIndex + 1;
                int right = i - 1;
                reverse(chars, left, right);
                lastSpaceIndex = i;
            }
        }
        return new String(chars);
    }

    /**
     * 703. Kth Largest Element in a Stream
     */
    final PriorityQueue<Integer> heap = new PriorityQueue<>();
//    final int k;
//
//    public KthLargest(int k, int[] nums) {
//        this.k = k;
//        for (int n : nums) {
//            add(n);
//        }
//    }

//    public int add(int val) {
//        if (heap.size() < k) {
//            heap.offer(val);
//        } else if (val > heap.peek()) {
//            heap.poll();
//            heap.add(val);
//        }
//        return heap.peek();
//    }


    /**
     * 387. First Unique Character in a String
     * O n
     * O 1
     */
    public int firstUniqChar(String s) {
        char[] chars = new char[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (chars[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 567. Permutation in String
     * O(l1 + (l2-l1)) l1 - len of string 1 l2 - len os string 2
     * O 1
     */
    public boolean checkInclusion(String s1, String s2) {
        int symbolsAmount = 26;
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] mapForFirstStr = new int[symbolsAmount];
        int[] secondMapStr = new int[symbolsAmount];
        for (int i = 0; i < s1.length(); i++) {
            mapForFirstStr[s1.charAt(i) - 'a']++;
            secondMapStr[s2.charAt(i) - 'a']++;
        }

        int count = 0;
        for (int i = 0; i < symbolsAmount; i++) {
            if (mapForFirstStr[i] == secondMapStr[i])
                count++;
        }

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            int r = s2.charAt(i + s1.length()) - 'a';
            int l = s2.charAt(i) - 'a';
            if (count == symbolsAmount) {
                return true;
            }

            secondMapStr[r]++;
            if (secondMapStr[r] == mapForFirstStr[r]) {
                count++;
            } else if (secondMapStr[r] == mapForFirstStr[r] + 1) {
                count--;
            }
            secondMapStr[l]--;
            if (secondMapStr[l] == mapForFirstStr[l]) {
                count++;
            } else if (secondMapStr[l] == mapForFirstStr[l] - 1) {
                count--;
            }
        }
        return count == symbolsAmount;
    }

    /**
     * 875. Koko Eating Bananas
     * Binary search
     * O n * log m
     * O 1
     */
    public int minEatingSpeed(int[] piles, int h) {

        int left = 1, right = 1;

        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            int mid = (left + right) / 2;
            int hours = 0;
            for (int pile : piles) {
                hours += (pile + mid - 1) / mid;
            }
            if (hours <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }


    /**
     * 33. Search in Rotated Sorted Array
     * O log n
     * O 1
     */
    public int searchRotatedArray(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target <= nums[right] && target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}


