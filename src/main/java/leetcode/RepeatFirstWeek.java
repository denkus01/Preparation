package leetcode;

import leetcode.DECEMBER_1_WEEK.ListNode;

import java.io.CharArrayReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.DelayQueue;

public class RepeatFirstWeek {

    //217. Contains Duplicate
    public boolean containsDuplicate(int[] nums) {
        Set set = new HashSet(nums.length);
        for (int x : nums) {
            if (set.contains(x)) return true;
            set.add(x);
        }
        return false;
    }

    //242. Valid Anagram
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] counter = new int[26];

        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    // 1. Two Sum
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int needValue = target - nums[i];
            if (map.containsKey(needValue)) {
                return new int[]{map.get(needValue), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
    //______________________________DAY_________________2__________________________


    /**
     * 1480. Running Sum of 1d Array
     * Input: nums = [1,2,3,4]
     * Output: [1,3,6,10]
     */
    public int[] runningSum(int[] nums) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return sum;
    }

    /**
     * 724. Find Pivot Index
     * Input: nums = [1,7,3,6,5,6]
     * Output: 3
     * Explanation:
     * The pivot index is 3.
     * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
     * Right sum = nums[4] + nums[5] = 5 + 6 = 11
     */
    public int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int leftSum = 0, rightSum = 0;

        for (int i = 0; i < nums.length; i++) {
            rightSum = sum - nums[i] - leftSum;
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }


    /**
     * 841. Keys and Rooms
     *
     * @param rooms
     * @return
     */
    boolean[] visited;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        visited = new boolean[rooms.size()];
        visited[0] = true;

        dfs(rooms, 0);
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    public void dfs(List<List<Integer>> rooms, int index) {
        for (int i : rooms.get(index)) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(rooms, i);
            }
        }
    }

    //DAY--------3-------------

    /**
     * 205. Isomorphic Strings
     * Input: s = "egg", t = "add"
     * Output: true
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        HashMap<Character, Character> firstMap = new HashMap();
        HashMap<Character, Character> secondMap = new HashMap();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if ((firstMap.containsKey(c1) && firstMap.get(c1) != c2) ||
                    (secondMap.containsKey(c2) && secondMap.get(c2) != c1)) {
                return false;
            }
            firstMap.put(c1, c2);
            secondMap.put(c2, c1);
        }
        return true;
    }

    //392. Is Subsequence
    public boolean isSubsequence(String s, String t) {
        int leftBound = s.length(), rightBound = t.length();
        int left = 0, right = 0;

        while (left < leftBound && right < rightBound) {
            if (s.charAt(left) == t.charAt(right)) {
                left++;
            }
            right++;
        }
        return left == leftBound;
    }

    //-----------------DAY---4------------------------

    /**
     * 347. Top K Frequent Elements
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Output: [1,2]
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Integer exists = map.get(nums[i]);
                map.put(nums[i], exists++);
            } else {
                map.put(nums[i], 1);
            }
        }


        for (int i = 0; i < map.size(); i++) {
            result[i] = map.get(i);
        }

    }

    /**
     * 760. Find Anagram Mappings
     * Input: nums1 = [12,28,46,32,50], nums2 = [50,12,32,46,28]
     * Output: [1,4,3,2,0]
     * Explanation: As mapping[0] = 1 because the 0th element of nums1 appears at nums2[1], and mapping[1] = 4 because the 1st element of nums1 appears at nums2[4], and so on.
     */
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[nums1.length];

        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }
        return result;

    }


    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> res = new ArrayList<>();
        int ar = 0, ar2 = 0, ar3 = 0;

        while (ar < arr1.length && ar2 < arr2.length && ar3 < arr3.length) {

            if (arr1[ar] == arr2[ar2] && arr2[ar2] == arr3[ar3]) {
                res.add(arr1[ar]);
                ar++;
                ar2++;
                ar3++;
            } else {
                if (arr1[ar] < arr2[ar2]) {
                    ar++;
                } else if (arr2[ar2] < arr3[ar3]) {
                    ar2++;
                } else {
                    ar3++;
                }

            }
        }
        return res;
    }


    public int mySqrt(int x) {
        if (x < 2) return x;

        double x0 = x;
        double x1 = (x0 + x / x0) / 2.0;
        while (Math.abs(x0 - x1) >= 1) {
            x0 = x1;
            x1 = (x0 + x / x0) / 2.0;
        }

        return (int) x1;
    }

    /**
     * 88. Merge Sorted Array
     * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * Output: [1,2,2,3,5,6]
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len1 = m - 1;
        int len2 = n - 1;

        for (int i = m + n - 1; i >= 0; i--) {
            if (len2 < 0) {
                break;
            }
            if (len1 >= 0 && nums1[len1] > nums2[len2]) {
                nums1[i] = nums1[len1--];
            } else {
                nums1[i] = nums2[len2--];
            }
        }
    }

    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

    /**
     * 349. Intersection of Two Arrays
     * O m + n in worst case
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int j : nums1) {
            set1.add(j);
        }
        for (int j : nums2) {
            set2.add(j);
        }

        set1.retainAll(set2);

        int[] output = new int[set1.size()];
        int idx = 0;
        for (int num : set1) {
            output[idx++] = num;
        }

        return output;

    }

    /**
     * 1710. Maximum Units on a Truck
     */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int count = 0;
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);

        for (int[] boxType : boxTypes) {
            int boxCount = Math.min(truckSize, boxType[0]);
            count += boxCount * boxType[1];
            truckSize -= boxCount;
            if (truckSize == 0) break;
        }
        return count;
    }


    public boolean isPalindrome(String s) {
        List<Character> list = new ArrayList<>();
        for (char ch : s.toLowerCase().toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                list.add(ch);
            }
        }

        for (int i = 0, j = list.size() - 1; i >= 0 && j > 0; i++, j--) {
            if (list.get(i) != list.get(j)) {
                return false;
            }
        }
        return true;

    }


    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');

        int start = 0;
        int end = s.length() - 1;
        char[] chars = s.toCharArray();

        while (start < end) {
            while (start < s.length() && !vowels.contains(chars[start])) {
                start++;
            }
            while (end >= 0 && !vowels.contains(chars[end])) {
                end--;
            }
            if (start < end) {
                swap(chars, start++, end--);

            }
        }

        return new String(chars);
    }

    void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    public int removeElement(int[] nums, int val) {
        int writer = 0;
        for (int reader = 0; reader < nums.length; reader++) {
            if (nums[reader] != val) {
                nums[writer] = nums[reader];
                writer++;
            }
        }
        return writer;
    }


    /**
     * 55. Jump Game
     */

    public boolean canJump(int[] nums) {
        int goal = nums.length - 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }
        return goal == 0;
    }


    /**
     * 61. Rotate List
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode tail = head;
        int len = 1;

        while (tail.next != null) {
            tail = tail.next;
            len++;
        }
        k = k % len;

        if (k == 0) {
            return head;
        }
        ListNode curr = head;
        for (int i = len - k - 1; i > 0; i--) {
            curr = curr.next;
        }
        ListNode newHead = curr.next;
        curr.next = null;
        tail.next = head;
        return newHead;
    }
}


class MovingAverage {
    private final int size;
    private int currSize;
    int windowSum = 0;
    private Queue queue = new ArrayDeque();

    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        if (currSize < size) {
            windowSum += val;
            currSize++;
        } else {
            int deleted = (int) queue.poll();
            windowSum += (val - deleted);
        }
        queue.add(val);
        return (windowSum + 0.0) / currSize;
    }
}
