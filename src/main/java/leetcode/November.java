package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.parseInt;

public class November {

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int neededInt = target - nums[i];
            if (map.containsKey(neededInt)) {
                return new int[]{map.get(neededInt), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * 56. Merge Intervals
     * Time complexity : O(n log n)
     * Space complexity : O(log N) (or O(n))
     */
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> answers = new LinkedList<>();

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (answers.isEmpty() || answers.getLast()[1] < interval[0]) {
                answers.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                answers.getLast()[1] = Math.max(answers.getLast()[1], interval[1]);
            }
        }
        return answers.toArray(new int[answers.size()][]);
    }

    /**
     * 57. Insert Interval
     * Time complexity : O(N) since it's one pass along the input array.
     * Space complexity : O(N) to keep the output.
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int start = newInterval[0];
        int end = newInterval[1];
        int i = 0;
        int m = intervals.length;
        List<int[]> res = new ArrayList<>();

        while (i < m && intervals[i][1] < start) {
            res.add(intervals[i++]);
        }

        while (i < m && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        res.add(new int[]{start, end});
        while (i < m) {
            res.add(intervals[i++]);
        }
        int[][] result = new int[res.size()][];

        for (int j = 0; j < res.size(); j++) {
            result[j] = res.get(j);
        }
        return result;
    }


    //-----2 day-------

    //58. Length of Last Word
    public int lengthOfLastWord(String s) {
        int len = s.length();
        int counter = 0;

        while (len > 0) {
            len--;
            if (s.charAt(len) != ' ') {
                counter++;
            } else if (counter > 0) {
                return counter;
            }
        }
        return counter;
    }

    /**
     * 59. Spiral Matrix II
     * Time : O(n2)
     * Space : O(1)
     */
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int cnt = 1;
        for (int layer = 0; layer < (n + 1) / 2; layer++) {
            for (int ptr = layer; ptr < n - layer; ptr++) {
                result[layer][ptr] = cnt++;
            }
            for (int ptr = layer + 1; ptr < n - layer; ptr++) {
                result[ptr][n - layer - 1] = cnt++;
            }
            for (int ptr = layer + 1; ptr < n - layer; ptr++) {
                result[n - layer - 1][n - ptr - 1] = cnt++;
            }
            for (int ptr = layer + 1; ptr < n - layer - 1; ptr++) {
                result[n - ptr - 1][layer] = cnt++;
            }
        }
        return result;
    }

    /**
     * 53. Maximum Subarray
     * Time complexity: O(N),
     * Space complexity: O(1)
     */

    public int maxSubArray(int[] nums) {
        // Initialize our variables using the first element.
        int currentSubarray = nums[0];
        int maxSubarray = nums[0];

        // Start with the 2nd element since we already used the first one.
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            // If current_subarray is negative, throw it away. Otherwise, keep adding to it.
            currentSubarray = Math.max(num, currentSubarray + num);
            maxSubarray = Math.max(maxSubarray, currentSubarray);
        }

        return maxSubarray;
    }

    /**
     * 66. plusOne
     * Time complexity: O(N),
     * Space complexity: O(1)
     */

    public int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }

        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;

    }

    /**
     * 812. Largest Triangle Area
     * on3
     * on1
     */
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double max = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    double currArea = area(points[i], points[j], points[k]);
                    max = Math.max(max, currArea);

                }
            }
        }
        return max;
    }

    public double area(int[] p1, int[] p2, int[] p3) {
        return Math.abs(p1[0] * (p2[1] - p3[1]) + p2[0] * (p3[1] - p1[1]) + p3[0] * (p1[1] - p2[1])) / 2.0;

    }


    /**
     * 995. Minimum Number of K Consecutive Bit Flips
     */

    public int minKBitFlips(int[] nums, int K) {
        int n = nums.length, flipped = 0, res = 0;
        int[] isFlipped = new int[n];

        for (int i = 0; i < nums.length; ++i) {
            if (i >= K) flipped ^= isFlipped[i - K];
            if (flipped == nums[i]) {
                if (i + K > nums.length) return -1;
                isFlipped[i] = 1;
                flipped ^= 1;
                res++;
            }
        }
        return res;
    }

    /**
     * 1877. Minimize Maximum Pair Sum in Array
     */

    public int minPairSum(int[] nums) {

        Arrays.sort(nums);
        int maxPair = 0;
        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            maxPair = Math.max(maxPair, nums[i] + nums[j]);
            i += 1;
            j -= 1;
        }
        return maxPair;
    }

    /**
     * 453. Minimum Moves to Equal Array Elements
     * On
     * O1
     */

    public int minMoves(int[] nums) {
        int moves = 0;
        int min = MAX_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
        }

        for (int num : nums) {
            moves += num - min;
        }
        return moves;
    }


    //-----------------------------

    /**
     * 118. Pascal's Triangle
     * <p>
     * On2
     * On2
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> row, pre = null;

        for (int i = 0; i < numRows; ++i) {
            row = new ArrayList<>();

            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = row;
            res.add(row);
        }
        return res;
    }


    /**
     * 119. Pascal's Triangle II
     * Given an integer rowIndex, return the row Indexth (0-indexed) row of the Pascal's triangle.
     */

    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        long sum = 1;
        res.add((int) sum);
        for (int i = 1; i <= rowIndex; i++) {
            sum = sum * (rowIndex - i + 1);
            sum /= i;
            res.add((int) sum);
        }
        return res;
    }

    /**
     * 442. Find All Duplicates in an Array
     * On
     * O1
     */

    public List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> duplicates = new ArrayList<>();
        boolean[] map = new boolean[nums.length + 1];
        for (int num : nums) {
            if (!map[num]) {
                map[num] = true;
            } else {
                duplicates.add(num);
            }

        }
        return duplicates;
    }

    /**
     * 179 Largest number
     * Time complexity : O(nlgn)
     * Space complexity : O(n)
     */
    public String largestNumber(int[] nums) {

        if (nums.length == 0) {
            return "";
        }

        String[] arr = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            arr[i] = Integer.toString(nums[i]);
        }

        Arrays.sort(arr, (o1, o2) -> {
            String a = o1 + o2;
            String b = o2 + o1;
            return b.compareTo(a);
        });

        if (arr[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        for (String s : arr) {
            sb.append(s);
        }
        return sb.toString();

    }

//-----repeat
    //------Sunday 13-------

    /**
     * 46. Permutations return all the possible permutations.
     * Time:
     * Space: O N!
     */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used;
        if (nums.length == 0) return res;
        used = new boolean[nums.length];
        List<Integer> permutation = new ArrayList<>();
        helper(nums, permutation, used, res);
        return res;
    }

    private void helper(int[] nums, List<Integer> perm, boolean[] used, List<List<Integer>> res) {
        if (perm.size() == nums.length) {
            res.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            perm.add(nums[i]);
            helper(nums, perm, used, res);
            perm.remove(perm.size() - 1);
            used[i] = false;
        }
    }


    /**
     * 624. Maximum Distance in Arrays
     * O (n)
     * O 1
     */
    public int maxDistance(List<List<Integer>> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return -1;
        }
        int min = arrays.get(0).get(0);
        int max = arrays.get(0).get(arrays.get(0).size() - 1);
        int result = MIN_VALUE;

        for (int i = 1; i < arrays.size(); i++) {
            int curMin = arrays.get(i).get(0);
            int curMax = arrays.get(i).get(arrays.get(i).size() - 1);
            result = Math.max(result, Math.abs(curMin - max));
            result = Math.max(result, Math.abs(curMax - min));
            max = Math.max(max, curMax);
            min = Math.min(min, curMin);
        }
        return result;
    }

    /**
     * 581. Shortest Unsorted Continuous Subarray
     * O n
     * O 1
     */

    public int findUnsortedSubarray(int[] nums) {
        int left = nums.length - 1;
        int right = 0;
        int max = nums[0];
        int min = nums[nums.length - 1];

        for (int i = 0; i < nums.length; ++i) {
            max = Math.max(max, nums[i]);
            if (nums[i] < max) {
                right = i;
            }
        }

        for (int i = nums.length - 1; i >= 0; --i) {

            min = Math.min(min, nums[i]);
            if (nums[i] > min) {
                left = i;
            }
        }
        if (left == nums.length - 1) return 0;
        return right - left + 1;
    }

    //--------15--------11------2022

    /**
     * 73. Set Matrix Zeroes
     * O (M * N)
     * O 1
     */
    public void setZeroes(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean rowZero = false;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 0) {
                    matrix[0][c] = 0;
                    if (r > 0) {
                        matrix[r][0] = 0;
                    } else {
                        rowZero = true;
                    }
                }
            }
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (matrix[0][c] == 0 || matrix[r][0] == 0) {
                    matrix[r][c] = 0;
                }
            }
        }

        if (matrix[0][0] == 0) {
            for (int r = 0; r < rows; r++) {
                matrix[r][0] = 0;
            }
        }

        if (rowZero) {
            for (int c = 0; c < cols; c++) {
                matrix[0][c] = 0;
            }
        }

    }


    /**
     * 268. Missing Number
     * On
     * O1
     */
    public int missingNumber(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }


    /**
     * 49. Group Anagrams
     * O(M*N)
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        for (String s : strs) {
            char[] ca = new char[26];
            for (char c : s.toCharArray()) {
                ca[c - 'a']++;
            }
            String keyStr = String.valueOf(ca);

            if (!map.containsKey(keyStr)) {
                map.put(keyStr, new ArrayList<>());
            }
            map.get(keyStr).add(s);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * 347. Top K Frequent Elements
     * Given an integer array nums and an integer k, return the k most frequent elements.
     * You may return the answer in any order.
     */

    public int[] topKFrequent(int[] nums, int k) {

        List<List<Integer>> buckets = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }

        int[] res = new int[k];
        int j = 0;

        for (int i = buckets.size() - 1; i > 0 && j <= k; i--) {
            if (buckets.get(i).size() != 0) {
                for (int curr : buckets.get(i)) {
                    if (j == k) break;
                    res[j] = curr;
                    j++;
                }
            }
        }
        return res;
    }

    /**
     * 238. Product of Array Except Self
     * O n
     * O 1
     */

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];

        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = length - 1; i >= 0; i--) {
            answer[i] = answer[i] * right;
            right *= nums[i];
        }
        return answer;
    }

    /**
     * 36. Valid Sudoku
     * On
     * O1
     */
    public static boolean isValidSudoku(char[][] board) {
        Set seen = new HashSet();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char number = board[i][j];
                if (number != '.') {
                    if (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i / 3 + "-" + j / 3)) {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    /**
     * 271. Encode and Decode Strings
     */
    public static class Codec {
        public String encode(List<String> strs) {
            StringBuilder out = new StringBuilder();
            for (String s : strs) {
                out.append(s.replace("#", "##")).append(" # ");
            }

            return out.toString();
        }

        public List<String> decode(String s) {
            List strs = new ArrayList();
            String[] array = s.split(" # ", -1);
            for (int i = 0; i < array.length - 1; ++i) {
                strs.add(array[i].replace("##", "#"));
            }

            return strs;
        }
    }

    /**
     * # 128 Longest Consecutive Sequence
     * On
     * On
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    /**
     * 125. Valid Palindrome
     * (deleting spaces and symbols + lower case  changes)
     * <p>
     * On
     * O1
     */
    public boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;
        }

        return true;
    }

    /**
     * 167. Two Sum II - Input Array Is Sorted
     */
    public int[] twoSum2(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;

        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                low++;
            } else {
                high--;
            }
        }
        return new int[]{-1, -1};
    }


    ///-----------------------

    /**
     * 11. Container With Most Water
     * On
     * O1
     */

    public int maxArea(int[] height) {
        int res = 0, left = 0, area;
        int right = height.length - 1;

        while (left < right) {
            area = (right - left) * Math.min(height[left], height[right]);
            res = Math.max(res, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }

        }
        return res;
    }

    /**
     * 42. Trapping rain Water
     * On
     * O1
     */
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }

        int l = 0;
        int res = 0;
        int r = height.length - 1;
        int leftMax = height[l];
        int rightMax = height[r];

        while (l < r) {
            if (leftMax < rightMax) {
                l++;
                leftMax = Math.max(leftMax, height[l]);
                res += leftMax - height[l];
            } else {
                r--;
                rightMax = Math.max(rightMax, height[r]);
                res += rightMax - height[r];
            }
        }
        return res;

    }

    //-----------

    /**
     * 121. Best Time to Buy and Sell Stock
     * On
     * O1
     */

    public int maxProfit(int[] prices) {
        int l = 0, r = 1, profit = 0;
        int maxPrice = 0;
        while (r < prices.length) {
            if (prices[l] < prices[r]) {
                profit = prices[r] - prices[l];
                maxPrice = Math.max(maxPrice, profit);
            } else {
                l = r;
            }
            r++;
        }
        return maxPrice;
    }

    /**
     * 3. Longest Substring Without Repeating Characters
     * Om
     * Omin(n,m)
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;

    }

    public int lengthOfLongestSubstring_2(String s) {
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int l = 0, res = 0;
        for (int i = 0; i < chars.length; i++) {
            while (set.contains(chars[i])) {
                set.remove(chars[l]);
                l++;
            }
            set.add(chars[i]);
            res = Math.max(res, i - l + 1);
        }
        return res;

    }
    //---------

    /**
     * 567. Permutation in String
     * Time complexity: O(l1+26∗(l2−l1)
     * Space complexity: O(1) Constant space is used.
     */

    public boolean checkInclusion(String s1, String s2) {
        int lens1 = s1.length();
        int lens2 = s2.length();
        if (lens1 > lens2) return false;

        // two arrays for match check

        int[] mapForS1 = new int[26];
        int[] mapForS2 = new int[26];

        for (int i = 0; i < lens1; i++) {
            mapForS1[s1.charAt(i) - 'a']++;
            mapForS2[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < lens2 - lens1; i++) {
            if (matches(mapForS1, mapForS2)) {
                return true;
            }
            // add new element from right
            mapForS2[s2.charAt(i + lens1) - 'a']++;
            // delete eft one
            mapForS2[s2.charAt(i) - 'a']--;
        }
        return matches(mapForS1, mapForS2);
    }

    public boolean matches(int[] map1, int[] map2) {
        for (int i = 0; i < 26; i++) {
            if (map1[i] != map2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 20. Valid Parentheses
     * O n
     * O n
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                char p = stack.pop();
                if (c - p != 1 && c - p != 2) {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    /**
     * 150. Evaluate Reverse Polish Notation
     * O n
     * O n
     */
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (final String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(String.valueOf(Integer.parseInt(stack.pop()) + Integer.parseInt(stack.pop())));
                    break;
                case "-":
                    String s1 = stack.pop();
                    String s2 = stack.pop();
                    stack.add(String.valueOf(Integer.parseInt(s2) - Integer.parseInt(s1)));
                    break;
                case "*":
                    stack.push(String.valueOf(Integer.parseInt(stack.pop()) * Integer.parseInt(stack.pop())));
                    break;
                case "/":
                    String a = stack.pop();
                    String b = stack.pop();
                    stack.add(String.valueOf(Integer.parseInt(b) / Integer.parseInt(a)));
                    break;
                default:
                    stack.push(token);
            }
        }
        return Integer.parseInt(stack.get(0));
    }

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

}

