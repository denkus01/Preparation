package leetcode.weeks;

import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class January {

    /**
     * 28. Find the Index of the First Occurrence in a String
     */
    public int strStr(String haystack, String needle) {
        if (needle.equals("")) return 0;
        int nedLen = needle.length();
        for (int i = 0; i < haystack.length() + 1 - nedLen; i++) {
            for (int j = 0; j < nedLen; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
                if (j == nedLen - 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 1360. Number of Days Between Two Dates
     */
    public int daysBetweenDates(String date1, String date2) {

    }


    /**
     * 383. Ransom Note
     * <p>
     * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
     * Each letter in magazine can only be used once in ransomNote.
     */

    public boolean canConstruct(String ransomNote, String magazine) {

        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (char c : magazine.toCharArray()) {
            int currentCount = map.getOrDefault(c, 0);
            map.put(c, currentCount + 1);
        }

        for (char c : ransomNote.toCharArray()) {
            int countInMagazine = map.getOrDefault(c, 0);
            if (countInMagazine == 0) {
                return false;
            }
            map.put(c, countInMagazine - 1);
        }

        return true;
    }


    //----

    /**
     * 2068. Check Whether Two Strings are Almost Equivalent
     */
    public boolean checkAlmostEquivalent(String word1, String word2) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < word1.length(); i++) {
            map.put(word1.charAt(i), map.getOrDefault(word1.charAt(i), 0) + 1);
            map.put(word2.charAt(i), map.getOrDefault(word2.charAt(i), 0) - 1);
        }
        for (int count : map.values()) {
            if (Math.abs(count) > 3) {
                return false;
            }
        }
        return true;
    }


    /**
     * 696. Count Binary Substrings
     */
    public int countBinarySubstrings(String s) {
        int res = 0, prev = 0, cur = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) != s.charAt(i)) {
                res += Math.min(prev, cur);
                prev = cur;
                cur = 1;
            } else {
                cur++;
            }
        }
        int min = Math.min(prev, cur);

        return res + min;
    }

    /**
     * 2244. Minimum Rounds to Complete All Tasks
     */
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        int minSolve = 0;

        for (int task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }

        for (int count : map.values()) {
            if (count == 1) {
                return -1;
            }
            if (count % 3 == 0) {
                minSolve += count / 3;
            } else {
                minSolve += count / 3 + 1;
            }
        }

        return minSolve;
    }

    /**
     * 452. Minimum Number of Arrows to Burst Balloons
     * O n log n
     * O log N
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;

        Arrays.sort(points, (point1, point2) -> Integer.compare(point1[1], point2[2]));

        int arrow = 1;
        int position = points[0][1];

        for (int[] point : points) {
            if (position >= point[0]) {
                continue;
            }
            arrow++;
            position = point[1];
        }
        return arrow;
    }

    /**
     * 1133. Largest Unique Number
     * O nlgn
     * O 1
     */
    public int largestUniqueNumber(int[] nums) {
        Arrays.sort(nums);

        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == 0 || nums[i] != nums[i - 1]) return nums[i];

            while (i > 0 && nums[i] == nums[i - 1]) {
                i--;
            }
        }
        return -1;
    }

    /**
     * 134. Gas Station
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length, total = 0, curr = 0, start = 0;

        for (int i = 0; i < len; ++i) {
            total += gas[i] - cost[i];
            curr += gas[i] - cost[i];
            if (curr < 0) {
                start = i + 1;
                curr = 0;
            }
        }
        return total >= 0 ? start : -1;
    }

    /**
     * 243. Shortest Word Distance
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int left = -1, right = -1;
        int distance = words.length;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                left = i;
            } else if (words[i].equals(word2)) {
                right = i;
            }

            if (left != -1 && right != -1) {
                distance = Math.min(distance, Math.abs(left - right));
            }
        }
        return distance;
    }

    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();

        for (int num : nums) {
            if (visited.contains(num)) {
                continue;
            }

            if (minHeap.size() == 3) {
                if (minHeap.peek() < num) {
                    visited.remove(minHeap.poll());

                    minHeap.add(num);
                    visited.add(num);
                }
            } else {
                minHeap.add(num);
                visited.add(num);
            }
        }

        if (minHeap.size() == 1) {
            return minHeap.peek();
        } else if (minHeap.size() == 2) {
            int firstNum = minHeap.poll();
            return Math.max(firstNum, minHeap.peek());
        }

        return minHeap.peek();
    }

    /**
     * 1047. Remove All Adjacent Duplicates In String
     */
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == stack.peek()) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        StringBuilder sb = new StringBuilder();

        for (Character ch : stack) {
            sb.append(ch);
        }

        return sb.toString();
    }

    /**
     * 1544. Make The String Greatv
     * O n
     * O n
     */
    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && Math.abs(stack.lastElement() - ch) == 32) {
                stack.pop();
            } else {
                stack.add(ch);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : stack) {
            sb.append(ch);
        }

        return sb.toString();
    }

    /**
     * 1762. Buildings With an Ocean View
     * On
     * O1
     */
    public int[] findBuildings(int[] heights) {
        int len = heights.length;
        List<Integer> list = new ArrayList<>();
        int maxHeight = -1;

        for (int i = len - 1; i >= 0; i--) {
            if (maxHeight < heights[i]) {
                list.add(i);
                maxHeight = heights[i];
            }
        }

        int[] res = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(list.size() - 1 - i);
        }

        return res;
    }

    /**
     * 100. Same Tree
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;

        return isSameTree(p.right, q.right) && isSameTree(p.left, q.left);
    }


    /**
     * 2214. Minimum Health to Beat Game
     */
    public long minimumHealth(int[] damage, int armor) {
        int max = 0;
        long total = 0;

        for (int dam : damage) {
            total += dam;
            max = Math.max(max, dam);
        }

        return total - Math.min(armor, max) + 1;
    }

    /**
     * 1056. Confusing Number
     * On
     * On
     */
    public boolean confusingNumber(int n) {

        Map<Integer, Integer> invertMap = new HashMap<Integer, Integer>() {{
            put(0, 0);
            put(1, 1);
            put(6, 9);
            put(8, 8);
            put(9, 6);
        }};
        int copy = n, rotatedNumber = 0;

        while (copy > 0) {
            int res = copy % 10;
            if (!invertMap.containsKey(res)) {
                return false;
            }
            rotatedNumber = rotatedNumber * 10 + invertMap.get(res);
            copy /= 10;
        }

        return rotatedNumber != n;
    }


    public boolean IsPalindrome(int x) {
        int revertedNumber = 0;

        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        return x == revertedNumber || x == revertedNumber / 10;
    }


    public void gameOfLife(int[][] board) {

        int[] neighbors = {0, 1, -1};
        int boardLen = 3;

        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                int liveNeighbors = 0;

                for (int i = 0; i < boardLen; i++) {
                    for (int j = 0; j < boardLen; j++) {

                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);

                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }

                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > boardLen)) {
                    board[row][col] = -1;
                }
                // Rule 4
                if (board[row][col] == 0 && liveNeighbors == boardLen) {
                    board[row][col] = 2;
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] > 0) {
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }

    /**
     * 1533. Find the Index of the Large Integer
     */
    public int getIndex(ArrayReader reader) {
        int left = 0;
        int len = reader.length();

        while (len > 1) {
            len /= 2;
            int cmp = reader.compareSub(left, left + len - 1, left + len, left + len + len - 1);

            if (cmp == 0) {
                return left + len + len;
            }
            if (cmp < 0) {
                left += len;
            }
        }
        return left;
    }


    /**
     * 684. Redundant Connection
     */
    int[] parent;

    public int[] findRedundantConnection(int[][] edges) {
        parent = new int[edges.length + 1];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            if (find(edge[0]) == find(edge[1])) {
                return edge;
            }

            union(edge[0], edge[1]);
        }

        return new int[]{-1, -1};
    }

    private int find(int node) {
        while (parent[node] != node) {
            node = parent[node];
        }

        return node;
    }

    private void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        if (rootI != rootJ) {
            parent[rootJ] = rootI;
        }
    }

    /**
     * 374. Guess Number Higher or Lower
     */
    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int res = guess(mid);
            if (res == 0) {
                return mid;
            } else if (res < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }
        return -1;
    }

    public int[] fairCandySwap(int[] A, int[] B) {
        int aSum, bSum;

        aSum = Arrays.stream(A).sum();
        bSum = Arrays.stream(B).sum();
        int mid = (bSum - aSum) / 2;

        Set<Integer> setB = new HashSet<>();
        for (int i : B) {
            setB.add(i);
        }

        for (int i : A) {
            if (setB.contains(i + mid)) {
                return new int[]{i, i + mid};
            }

        }
        return null;
    }
}

