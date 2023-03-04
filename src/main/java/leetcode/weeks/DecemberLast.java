package leetcode.weeks;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class DecemberLast {

    /**
     * 22. Generate Parentheses
     * Time  and space : O(4^n / sq n)
     */

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtracking(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    public void backtracking(List<String> res, StringBuilder sb, int open, int close, int max) {

        if (sb.length() == max * 2) {
            res.add(sb.toString());
            return;
        }

        if (open < max) {
            sb.append("(");
            backtracking(res, sb, open + 1, close, max);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(")");
            backtracking(res, sb, open, close + 1, max);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 739. Daily Temperatures
     * O n
     * O n
     */

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int currDay = 0; currDay < n; currDay++) {
            int currentTemp = temperatures[currDay];
            while (!stack.isEmpty() && temperatures[stack.peek()] < currentTemp) {
                int prevDay = stack.pop();
                answer[prevDay] = currDay - prevDay;
            }
            stack.push(currDay);
        }

        return answer;
    }

    /**
     * 2279. Maximum Bags With Full Capacity of Rocks
     * <p>
     * O n log n
     * O n
     */
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length, bags = 0;

        int[] remainingCapacity = new int[n];
        for (int i = 0; i < n; ++i)
            remainingCapacity[i] = capacity[i] - rocks[i];

        Arrays.sort(remainingCapacity);

        for (int i = 0; i < n; ++i) {
            if (additionalRocks >= remainingCapacity[i]) {
                additionalRocks -= remainingCapacity[i];
                bags++;
            } else
                break;
        }

        return bags;
    }


    /**
     * 16. 3Sum Closest
     */

    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int len = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < len && diff != 0; ++i) {
            int lo = i + 1;
            int hi = len - 1;

            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (Math.abs(target - sum) < Math.abs(diff)) {
                    diff = target - sum;
                }
                if (sum < target) {
                    ++lo;
                } else {
                    --hi;
                }
            }
        }
        return target - diff;
    }


    /**
     * 1574. Shortest Subarray to be Removed to Make Array Sorted
     */
    public int findLengthOfShortestSubarray(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (right > 0 && arr[right - 1] <= arr[right]) {
            right--;
        }

        if (right == 0) return 0;

        int result = right;

        while (left < right) {
            while (right < arr.length && arr[left] > arr[right]) {
                right++;
            }
            result = Math.min(result, right - left - 1);

            if (arr[left] <= arr[left + 1]) {
                result = Math.min(result, arr.length - (left + 2));
            } else {
                break;
            }

            left++;
        }

        return result;
    }

    /**
     * 219. Contains Duplicate II
     * O n
     * O n
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 424. Longest Repeating Character Replacement
     */
    public int characterReplacement(String s, int k) {
        int start = 0;
        int[] map = new int[26];
        int maxFrequency = 0;
        int longestSubstringLength = 0;

        for (int end = 0; end < s.length(); end += 1) {
            int currentChar = s.charAt(end) - 'A';

            map[currentChar] += 1;

            maxFrequency = Math.max(maxFrequency, map[currentChar]);


            if (!(end + 1 - start - maxFrequency <= k)) {
                int outgoingChar = s.charAt(start) - 'A';

                map[outgoingChar] -= 1;

                start += 1;
            }
            longestSubstringLength = end + 1 - start;
        }

        return longestSubstringLength;
    }


    /**
     * 438. Find All Anagrams in a String
     */
    public List<Integer> findAnagrams(String s, String p) {
        int firstLen = s.length(), secondLen = p.length();
        if (firstLen < secondLen) return new ArrayList<>();

        Map<Character, Integer> firstCount = new HashMap<>();
        Map<Character, Integer> secondCount = new HashMap<>();

        for (char ch : p.toCharArray()) {
            if (firstCount.containsKey(ch)) {
                firstCount.put(ch, firstCount.get(ch) + 1);
            } else {
                firstCount.put(ch, 1);
            }
        }

        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < firstLen; i++) {
            char ch = s.charAt(i);
            if (secondCount.containsKey(ch)) {
                secondCount.put(ch, secondCount.get(ch) + 1);
            } else {
                secondCount.put(ch, 1);
            }
            if (i >= secondLen) {
                ch = s.charAt(i - secondLen);
                if (secondCount.get(ch) == 1) {
                    secondCount.remove(ch);
                } else {
                    secondCount.put(ch, secondCount.get(ch) - 1);
                }
            }
            if (firstCount.equals(secondCount)) {
                output.add(i - secondLen + 1);
            }
        }
        return output;
    }

    /**
     * 1672. Richest Customer Wealth
     */
    public int maximumWealth(int[][] accounts) {
        int row = accounts[0].length, column = accounts.length;
        int max = 0;

        for (int i = 0; i < row; i++) {
            int sum = 0;
            for (int j = 0; j < column; j++) {
                sum += accounts[i][j];
            }
            max = Math.max(max, sum);
        }
        return max;
    }


    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int res : map.values()) {
            if (!set.add(res)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 658. Find K Closest Elements
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, r = arr.length - k;
        List<Integer> result = new ArrayList<>();

        while (l < r) {
            int m = (l + r) / 2;
            if (x - arr[m] > arr[m + k] - x) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        for (int i = l; i < l + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    /**
     * 1002. Find Common Characters
     */

    public List<String> commonChars(String[] words) {
        final int SIZE = 26;
        List<String> res = new ArrayList<>();
        int[] min = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            min[i] = Integer.MAX_VALUE;
        }
        for (String s : words) {
            int[] cur = new int[SIZE];

            for (char c : s.toCharArray()) {
                cur[c - 'a']++;
            }
            for (int i = 0; i < SIZE; i++) {
                min[i] = Math.min(min[i], cur[i]);
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < min[i]; j++) {
                res.add(String.valueOf((char) (i + 'a')));
            }
        }
        return res;
    }


    /**
     * 496. Next Greater Element I
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums2) {
            while (!stack.empty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }

        while (!stack.empty()) {
            map.put(stack.pop(), -1);
        }

        int[] res = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }

        return res;
    }


    /**
     * 1268. Search Suggestions System
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> finalResult = new ArrayList<>();
        if (products == null || products.length == 0 || searchWord == null || searchWord.isEmpty()) {
            return finalResult;
        }
        Arrays.sort(products);
        List<String> possibleProductList = new ArrayList<>(Arrays.asList(products));

        for (int i = 0; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);
            List<String> filteredList = new ArrayList<>();
            for (String product : possibleProductList) {
                if (i < product.length() && c == product.charAt(i)) {
                    filteredList.add(product);
                }
            }
            List<String> intermediateResult = new ArrayList<>();
            for (int j = 0; j < 3 && j < filteredList.size(); j++) {
                intermediateResult.add(filteredList.get(j));
            }

            finalResult.add(intermediateResult);

            possibleProductList = filteredList;
        }
        return finalResult;
    }

    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        String[] animals = s.split(" ");
        if (pattern.length() != animals.length) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String animal = animals[i];
            if (map.containsKey(c)) {
                if (!map.get(c).equals(animal)) {
                    return false;
                }
            } else {
                if (map.containsValue(animal)) {
                    return false;
                }
                map.put(c, animal);
            }
        }
        return true;
    }

    /**
     * 520. Detect Capital
     */
    public boolean detectCapitalUse(String word) {
        int len = word.length();
        if (Character.isUpperCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1))) {
            for (int i = 2; i < len; i++) {
                if (Character.isLowerCase(i)) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < len; i++) {
                if (Character.isUpperCase(word.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }


    public int dominantIndex(int[] nums) {

        int max = 0, second = 0, maxIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                second = max;
                max = nums[i];
                maxIndex = i;
            } else if (second < nums[i]) {
                second = nums[i];
            }
        }
        return max >= 2 * second ? maxIndex : -1;
    }

    /**
     * 498. Diagonal Traverse
     * O m*n
     * O1
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null) {
            return null;
        }
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] result = new int[rows * cols];
        int r = 0;
        int c = 0;

        for (int i = 0; i < result.length; i++) {
            result[i] = matrix[r][c];
            if ((r + c) % 2 == 0) {
                if (c == cols - 1) {
                    r++;
                } else if (r == 0) {
                    c++;
                } else {
                    r--;
                    c++;
                }
            } else {
                if (r == rows - 1) {
                    c++;
                } else if (c == 0) {
                    r++;
                } else {
                    r++;
                    c--;
                }
            }
        }

        return result;
    }

    /**
     * 54. Spiral Matrix
     * O m *n
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int ROW = matrix.length;
        int COLUMN = matrix[0].length;
        int up = 0;
        int left = 0;
        int right = COLUMN - 1;
        int down = ROW - 1;

        while (result.size() < ROW * COLUMN) {
            for (int col = left; col <= right; col++) {
                result.add(matrix[up][col]);
            }
            for (int row = up + 1; row <= down; row++) {
                result.add(matrix[row][right]);
            }
            if (up != down) {
                for (int col = right - 1; col >= left; col--) {
                    result.add(matrix[down][col]);
                }
            }
            if (left != right) {
                for (int row = down - 1; row > up; row--) {
                    result.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            up++;
            down--;
        }

        return result;
    }


    /**
     * 67. Add Binary
     * Input: a = "11", b = "1"
     * Output: "100"
     */
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            carry = sum > 1 ? 1 : 0;
            res.append(sum % 2);
        }
        if (carry != 0) {
            res.append(carry);
        }
        return res.reverse().toString();
    }


    /**
     * 944. Delete Columns to Make Sorted
     */
    public int minDeletionSize(String[] strs) {
        int rows = strs[0].length();

        int answer = 0;
        for (int col = 0; col < rows; col++) {
            for (int row = 1; row < strs.length; row++) {
                System.out.println( "row:" + strs[row]);
                System.out.println( "row - 1:" + strs[row - 1]);
                if (strs[row].charAt(col) < strs[row - 1].charAt(col)) {
                    answer++;
                    break;
                }
            }
        }

        return answer;
    }
}
