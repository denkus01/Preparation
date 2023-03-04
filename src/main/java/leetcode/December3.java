package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static leetcode.DATA_STRUCTURES.*;

public class December3 {

    /**
     * 733. Flood Fill
     * Time complexity: O(m*n), space complexity: O(1).
     */

    int width, height;

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        width = image[0].length;
        height = image.length;
        if (image[sr][sc] != color) {
            dfs(image, sr, sc, color, image[sr][sc]);
        }

        return image;
    }

    public void dfs(int[][] image, int sr, int sc, int color, int pattern) {
        if (pattern == image[sr][sc]) {
            image[sr][sc] = color;
            if (sr < height - 1) {
                dfs(image, sr + 1, sc, color, pattern);
            }
            if (sc < width - 1) {
                dfs(image, sr, sc + 1, color, pattern);
            }
            if (sr > 0) {
                dfs(image, sr - 1, sc, color, pattern);
            }
            if (sc > 0) {
                dfs(image, sr, sc - 1, color, pattern);
            }
        }
    }


    /**
     * 695. Max Area of Island
     * Depth-First Search
     * Time Complexity: O(R∗C), where R is the number of rows in the given grid, and C is the number of columns. We visit every square once.
     * Space complexity: O(R∗C), the space used by seen to keep track of visited squares, and the space used by the call stack during our recursion.
     */
    int[][] grid;
    boolean[][] seen;

    public int area(int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || seen[r][c] || grid[r][c] == 0) {
            return 0;
        }

        seen[r][c] = true;
        return (1 + area(r + 1, c) +
                area(r - 1, c) +
                area(r, c - 1) +
                area(r, c + 1));
    }

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        int ans = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                ans = Math.max(ans, area(r, c));
            }
        }
        return ans;
    }

    /**
     * 70. Climbing Stairs
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    /**
     * 617. Merge Two Binary Trees
     * O(m) - m - nodes amount
     * O(m)
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }


    public Node connect(Node root) {

        if (root == null) {
            return root;
        }

        Node leftmost = root;

        while (leftmost.left != null) {
            Node head = leftmost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }

        return root;
    }

    /**
     * 931. Minimum Falling Path Sum
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        int[] directPath = new int[matrix.length + 1];
        for (int row = matrix.length - 1; row >= 0; row--) {
            int[] currentRow = new int[matrix.length + 1];
            for (int col = 0; col < matrix.length; col++) {
                if (col == 0) {
                    currentRow[col] = Math.min(directPath[col], directPath[col + 1]) + matrix[row][col];
                } else if (col == matrix.length - 1) {
                    currentRow[col] = Math.min(directPath[col], directPath[col - 1]) + matrix[row][col];
                } else {
                    currentRow[col] = Math.min(
                            directPath[col],
                            Math.min(directPath[col + 1], directPath[col - 1])
                    ) + matrix[row][col];
                }
            }
            directPath = currentRow;
        }
        int minFallingSum = Integer.MAX_VALUE;
        for (int startCol = 0; startCol < matrix.length; startCol++) {
            minFallingSum = Math.min(minFallingSum, directPath[startCol]);
        }
        return minFallingSum;
    }


    /**
     * 542. 01 Matrix
     * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
     * The distance between two adjacent cells is 1.
     */
    public int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return mat;
        }

        int rows = mat.length;
        int cols = mat[0].length;
        if (rows == 1 && cols == 1) {
            return mat;
        }

        int[][] result = new int[rows][cols];
        int maxDistance = rows + cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    continue;
                }
                result[i][j] = maxDistance;
                if (i > 0) {
                    result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
                }
                if (j > 0) {
                    result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
                }
            }
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (mat[i][j] == 0) {
                    continue;
                }
                if (i < rows - 1) {
                    result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
                }
                if (j < cols - 1) {
                    result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
                }
            }
        }

        return result;
    }

    /**
     * 994. Rotting Oranges
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        for (int i = 0; i < m; i += 1) {
            for (int j = 0; j < n; j += 1) {
                if (grid[i][j] == 2) queue.offer(new int[]{i, j});
                else if (
                        grid[i][j] == 1
                ) fresh += 1;
            }
        }

        int count = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty() && fresh != 0) {
            count += 1;
            int sz = queue.size();
            for (int i = 0; i < sz; i += 1) {
                int[] rotten = queue.poll();
                int r = rotten[0], c = rotten[1];
                for (int[] dir : dirs) {
                    int x = r + dir[0], y = c + dir[1];
                    if (0 <= x && x < m && 0 <= y && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.offer(new int[]{x, y});
                        fresh -= 1;
                    }
                }
            }
        }
        return fresh == 0 ? count : -1;
    }

    /**
     * 198. House Robber
     * O n
     * O 1
     */
    public int rob(int[] nums) {

        int len = nums.length;

        if (len == 0) {
            return 0;
        }

        int robNext, robNextPlusOne;

        robNextPlusOne = 0;
        robNext = nums[len - 1];

        for (int i = len - 2; i >= 0; --i) {
            int current = Math.max(robNext, robNextPlusOne + nums[i]);
            robNextPlusOne = robNext;
            robNext = current;
        }

        return robNext;
    }

    /**
     * 153. Find Minimum in Rotated Sorted Array
     */
    public int findMin(int[] nums) {
        int res = nums[0];
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            if (nums[l] < nums[r]) {
                res = Math.min(res, nums[l]);
                break;
            }
            int middle = (l + r) / 2;
            res = Math.min(res, nums[middle]);
            if (nums[middle] >= nums[l]) {
                l = middle + 1;
            } else {
                r = middle - 1;
            }
        }
        return res;
    }

    /**
     * 1143. Longest Common Subsequence
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {

        if (text2.length() < text1.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        int[] previous = new int[text1.length() + 1];

        for (int col = text2.length() - 1; col >= 0; col--) {
            int[] current = new int[text1.length() + 1];
            for (int row = text1.length() - 1; row >= 0; row--) {
                if (text1.charAt(row) == text2.charAt(col)) {
                    current[row] = 1 + previous[row + 1];
                } else {
                    current[row] = Math.max(previous[row], current[row + 1]);
                }
            }
            previous = current;
        }

        return previous[0];
    }


    /**
     * 77. Combinations
     * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
     */
    List<List<Integer>> res = new LinkedList<>();
    int n;
    int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        backtrack(1, new LinkedList<>());
        return res;
    }

    public void backtrack(int first, LinkedList<Integer> curr) {
        if (curr.size() == k)
            res.add(new LinkedList<>(curr));

        for (int i = first; i < n + 1; ++i) {
            curr.add(i);
            backtrack(i + 1, curr);
            curr.removeLast();
        }
    }


    /**
     * 784. Letter Case Permutation
     */
    public List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, 0, s.toCharArray());
        return ans;
    }

    public void backtrack(List<String> ans, int i, char[] str) {
        if (i == str.length) {
            ans.add(new String(str));
        } else {
            if (Character.isLetter(str[i])) {
                str[i] = Character.toUpperCase(str[i]);
                backtrack(ans, i + 1, str);
                str[i] = Character.toLowerCase(str[i]);
                backtrack(ans, i + 1, str);
            } else
                backtrack(ans, i + 1, str);
        }
    }


    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        return ((long) n & ((long) n - 1)) == 0;
    }

    /**
     * 1971. Find if Path Exists in Graph
     * @return
     */

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph.computeIfAbsent(a, val -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, val -> new ArrayList<>()).add(a);
        }

        boolean[] seen = new boolean[n];
        seen[source] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            int currNode = queue.poll();
            if (currNode == destination) {
                return true;
            }

            for (int nextNode : graph.get(currNode)) {
                if (!seen[nextNode]) {
                    seen[nextNode] = true;
                    queue.offer(nextNode);
                }
            }
        }

        return false;
    }

    // Fibonaci
//    int num1 = 0, num2 = 1, counter = 0;
//
//        while (counter < N) {
//
//        int num3 = num2 + num1;
//        num1 = num2;
//        num2 = num3;
//        counter = counter + 1;
//    }
}
