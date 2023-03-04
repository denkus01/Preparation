package leetcode.weeks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class Trees {

    /**
     * 226. Invert Binary Tree
     */
    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }


    // BFS

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            count++;
        }
        return count;
    }

    //DFS
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();
        stack.push(root);
        value.push(1);
        int max = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int temp = value.pop();
            max = Math.max(temp, max);
            if (node.left != null) {
                stack.push(node.left);
                value.push(temp + 1);
            }
            if (node.right != null) {
                stack.push(node.right);
                value.push(temp + 1);
            }
        }
        return max;
    }


    /**
     * 543. Diameter of Binary Tree
     */
    private int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        longestPath(root);
        return diameter;
    }

    private int longestPath(TreeNode node) {
        if (node == null) return 0;

        int leftPath = longestPath(node.left);
        int rightPath = longestPath(node.right);

        diameter = Math.max(diameter, leftPath + rightPath);

        return Math.max(leftPath, rightPath) + 1;
    }


    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] temp = Arrays.copyOf(dist, n);
            for (int[] flight : flights) {
                if (dist[flight[0]] != Integer.MAX_VALUE) {
                    temp[flight[1]] = Math.min(temp[flight[1]], dist[flight[0]] + flight[2]);
                }
            }
            dist = temp;
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }


    /**
     * 101. Symmetric Tree
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }

        if (left.val != right.val) {
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    /**
     * 110. Balanced Binary Tree
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    // bottom -> up
    private int getHeight(TreeNode node) {
        if (node == null) return 0;

        int left = getHeight(node.left);
        int right = getHeight(node.right);

        // left, right subtree is unbalanced or cur tree is unbalanced
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }


    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (isTheSame(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isTheSame(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        if (root.val != subRoot.val) return false;

        return isTheSame(root.left, subRoot.left) && isTheSame(root.right, subRoot.right);
    }

    /**
     * 938. Range Sum of BST
     */
    public int rangeSumBST_origin(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current;
        stack.push(root);
        while (!stack.isEmpty()) {
            current = stack.pop();

            if (current.val >= low && current.val <= high) {
                res += current.val;
            }

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        return res;
    }

    //DFS recursive
    int sum = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        dfs(root, low, high);
        return sum;
    }

    public void dfs(TreeNode root, int low, int high) {
        if (root == null) {
            return;
        }
        if (low <= root.val && root.val <= high) {
            sum += root.val;
        }
        dfs(root.left, low, high);
        dfs(root.right, low, high);
    }


    /**
     * 199. Binary Tree Right Side View
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs2(res, root, 0);
        return res;
    }

    public void dfs2(List<Integer> res, TreeNode root, int height) {
        if (root == null) {
            return;
        }
        if (res.size() <= height) {
            res.add(height, root.val);
        } else {
            res.set(height, root.val);
        }
        dfs2(res, root.left, height + 1);
        dfs2(res, root.right, height + 1);
    }

    /**
     * 872. Leaf-Similar Trees
     */


    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> firstTree = new ArrayList<>();
        List<Integer> secondTree = new ArrayList<>();

        dfs3(root1, firstTree);
        dfs3(root2, secondTree);

        if (firstTree.size() != secondTree.size()) {
            return false;
        } else {
            for (int i = 0; i < firstTree.size(); i++) {
                if (!Objects.equals(firstTree.get(i), secondTree.get(i))) {
                    return false;
                }
            }
        }

    }

    public void dfs3(TreeNode root, List<Integer> list) {
        if (root != null) {
            if (root.right == null && root.left == null) {
                list.add(root.val);
            }
            dfs3(root.left, list);
            dfs3(root.right, list);
        }
    }

    /**
     * 463. Island Perimeter
     */
    public int islandPerimeter(int[][] grid) {

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    return getPerimeterDFS(grid, visited, row, col);
                }
            }
        }

        return 0;

    }

    private int getPerimeterDFS(int[][] grid, boolean[][] visited, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return 1;
        }
        if (grid[row][col] == 0) {
            return 1;
        }
        if (visited[row][col]) {
            return 0;
        }

        visited[row][col] = true;

        int count = getPerimeterDFS(grid, visited, row + 1, col) + getPerimeterDFS(
                grid,
                visited,
                row - 1,
                col
        ) + getPerimeterDFS(grid, visited, row, col - 1) + getPerimeterDFS(
                grid,
                visited,
                row,
                col + 1
        );

        return count;
    }

    /**
     * 200. Number of Islands
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int row = grid.length, column = grid[0].length;
        int numberOfIslands = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1') {
                    numberOfIslands++;
                    dfsIslands(grid, i, j);
                }
            }
        }
        return numberOfIslands;

    }

    private void dfsIslands(char[][] grid, int row, int column) {

        int gridRow = grid.length, gridColumn = grid[0].length;
        if (row < 0 || column < 0 || row >= gridRow || column >= gridColumn || grid[row][column] == '0') {
            return;
        }
        grid[row][column] = '0';

        dfsIslands(grid, row - 1, column);
        dfsIslands(grid, row + 1, column);
        dfsIslands(grid, row, column - 1);
        dfsIslands(grid, row, column + 1);

    }


    /**
     * 352. Data Stream as Disjoint Intervals
     */
    class SummaryRanges {

        TreeMap<Integer, Integer> map;

        public SummaryRanges() {
            map = new TreeMap<>();
        }

        public void addNum(int value) {
            Map.Entry<Integer, Integer> entry = map.floorEntry(value);
            int left = value, right = value;
            if (entry != null) {
                final int previous = entry.getValue();
                if (previous >= value) {
                    return;
                }
                if (previous == value - 1) {
                    left = entry.getKey();
                }
            }

            Map.Entry<Integer, Integer> maxEntry = map.higherEntry(value);
            if (maxEntry != null && maxEntry.getKey() == value + 1) {
                right = maxEntry.getValue();
                map.remove(value + 1);
            }
            map.put(left, right);
        }

        public int[][] getIntervals() {
            final int[][] answer = new int[map.size()][2];
            int index = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                answer[index][0] = entry.getKey();
                answer[index++][1] = entry.getValue();
            }
            return answer;
        }
    }


    /**
     * 235. Lowest Common Ancestor of a Binary Search Tree
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;

    }


    /**
     * 102. Binary Tree Level Order Traversal
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            levels.add(new ArrayList<Integer>());

            int level_length = queue.size();
            for (int i = 0; i < level_length; ++i) {
                TreeNode node = queue.remove();

                levels.get(level).add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            level++;
        }
        return levels;

    }


    private int findMax(int[][] ageScorePair) {
        int n = ageScorePair.length;
        int answer = 0;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = ageScorePair[i][1];
            answer = Math.max(answer, dp[i]);
        }


        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (ageScorePair[i][1] >= ageScorePair[j][1]) {
                    dp[i] = Math.max(dp[i], ageScorePair[i][1] + dp[j]);
                }
            }
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }

    public int bestTeamScore(int[] scores, int[] ages) {
        int N = ages.length;
        int[][] ageScorePair = new int[N][2];

        for (int i = 0; i < N; i++) {
            ageScorePair[i][0] = ages[i];
            ageScorePair[i][1] = scores[i];
        }

        Arrays.sort(ageScorePair, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        return findMax(ageScorePair);
    }


    /**
     * 199. Binary Tree Right Side View
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        List<Integer> rightSide = new ArrayList<>();

        while (!queue.isEmpty()) {
            int levelLength = queue.size();

            for (int i = 0; i < levelLength; ++i) {
                TreeNode node = queue.poll();
                if (i == levelLength - 1) {
                    rightSide.add(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return rightSide;
    }


    /**
     * 1448. Count Good Nodes in Binary Tree
     */

    int res = 0;

    public int goodNodes(TreeNode root) {
        if (root == null) return 0;
        dfs4(root, Integer.MIN_VALUE);
        return res;
    }


    public void dfs4(TreeNode root, int value) {
        if (root != null) {
            if (root.val >= value) {
                res++;
                value = root.val;
            }
            dfs4(root.left, value);
            dfs4(root.right, value);
        }
    }

    /**
     * 98. Validate Binary Search Tree
     */
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValid(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val >= max || root.val <= min) return false;

        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (node != null && root.val <= node.val) return false;
            node = root;
            root = root.right;
        }
        return true;
    }


    /**
     * 230. Kth Smallest Element in a BST
     */
    public int kthSmallest(TreeNode root, int k) {
        int n = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }

            root = stack.pop();
            n += 1;
            if (n == k) {
                return root.val;
            }
            root = root.right;
        }
        return -1;
    }


    /**
     * 105. Construct Binary Tree from Preorder and Inorder Traversal
     */
    int preorderIndex;
    Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return arrayToTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        if (left > right) return null;

        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
        root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
        return root;
    }


    /**
     * 1339. Maximum Product of Splitted Binary Tree
     */
    private List<Integer> allSums = new ArrayList<>();

    public int maxProduct(TreeNode root) {
        long totalSum = treeSum(root);
        long best = 0;
        for (long sum : allSums) {
            best = Math.max(best, sum * (totalSum - sum));
        }
        // We have to cast back to an int to match return value.
        return (int) (best % 1000000007);

    }

    private int treeSum(TreeNode subroot) {
        if (subroot == null) return 0;
        int leftSum = treeSum(subroot.left);
        int rightSum = treeSum(subroot.right);
        int totalSum = leftSum + rightSum + subroot.val;
        allSums.add(totalSum);
        return totalSum;
    }


    public TreeNode pruneTree(TreeNode root) {

        if (root == null) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) return null;
        return root;

    }


    /**
     * 1129. Shortest Path with Alternating Colors
     */
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] redAdj = buildAdj(redEdges, n);
        List<Integer>[] blueAdj = buildAdj(blueEdges, n);

        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        boolean[] vRed = new boolean[n];
        boolean[] vBlue = new boolean[n];
        vRed[0] = vBlue[0] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, "none"));

        while (!q.isEmpty()) {
            Node curr = q.poll();
            int currNode = curr.node;
            int length = curr.length;
            String edgeColor = curr.colour;
            if (answer[currNode] == -1) {
                answer[currNode] = length;
            }

            if (!edgeColor.equals("red")) {
                for (int child : redAdj[currNode]) {
                    if (vRed[child]) {
                        continue;
                    }
                    vRed[child] = true;
                    q.add(new Node(child, length + 1, "red"));
                }
            }
            if (!edgeColor.equals("blue")) {
                for (int child : blueAdj[currNode]) {
                    if (vBlue[child]) {
                        continue;
                    }
                    vBlue[child] = true;
                    q.add(new Node(child, length + 1, "blue"));
                }
            }
        }

        return answer;
    }

    // builds ajacency list
    private List<Integer>[] buildAdj(int[][] edges, int n) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
        }

        return adj;
    }

    // Node class
    private class Node {
        int node, length;
        String colour;

        Node(int node, int length, String colour) {
            this.node = node;
            this.length = length;
            this.colour = colour;
        }
    }


    long ans = 0;
    int s;

    public long minimumFuelCost(int[][] roads, int seats) {
        List<List<Integer>> graph = new ArrayList<>();
        s = seats;
        for (int i = 0; i < roads.length + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] r : roads) {
            graph.get(r[0]).add(r[1]);
            graph.get(r[1]).add(r[0]);
        }
        dfs(0, 0, graph);
        return ans;
    }

    private int dfs(int i, int prev, List<List<Integer>> graph) {
        int people = 1;
        for (int x : graph.get(i)) {
            if (x == prev) continue;
            people += dfs(x, i, graph);
        }
        if (i != 0) {
            ans += (people + s - 1) / s;
        }
        return people;
    }

    /**
     * 695. Max Area of Island
     */
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;

        return 1 + dfs(grid, i + 1, j) + dfs(grid, i - 1, j) + dfs(grid, i, j + 1) + dfs(grid, i, j - 1);
    }


    int minDistance = Integer.MAX_VALUE;
    TreeNode prevValue;

    public int minDiffInBST(TreeNode root) {
        inorderTraversal(root);
        return minDistance;
    }


    void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        } else {
            inorderTraversal(root.left);
            if (prevValue != null) {
                minDistance = Math.min(minDistance, root.val - prevValue.val);
            }
            prevValue = root;
            inorderTraversal(root.right);
        }
    }


    /**
     * 103. Binary Tree Zigzag Level Order Traversal
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            ArrayList<Integer> nodeLevel = new ArrayList<>();
            int size = queue.size();

            while (size-- > 0) {
                TreeNode curr = queue.poll();

                if (level % 2 == 0) {
                    nodeLevel.add(curr.val);

                } else {
                    nodeLevel.add(0, curr.val);
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                } else {
                    queue.offer(curr.right);
                }
            }
            level++;
            res.add(nodeLevel);
        }
        return res;
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> levelNodes = new ArrayList<>();
            while (size-- > 0) {
                TreeNode curr = q.poll();
                if (level % 2 == 0) {
                    levelNodes.add(curr.val);
                } else {
                    levelNodes.add(0, curr.val);
                }
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
            level++;
            ans.add(levelNodes);
        }
        return ans;
    }


    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        int m = rooms.length;
        int n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();

        // Add all gates to the queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // Define directions
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];

            // Update neighboring rooms with distance to current gate
            for (int[] dir : directions) {
                int r = row + dir[0];
                int c = col + dir[1];
                if (r < 0 || r >= m || c < 0 || c >= n || rooms[r][c] != Integer.MAX_VALUE) {
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1;
                queue.offer(new int[]{r, c});
            }
        }
    }


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (isCyclic(adj, visited, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isCyclic(List<List<Integer>> adj, int[] visited, int curr) {
        if (visited[curr] == 2) {
            return true;
        }

        visited[curr] = 2;
        for (int i = 0; i < adj.get(curr).size(); i++) {
            if (visited[adj.get(curr).get(i)] != 1) {
                if (isCyclic(adj, visited, adj.get(curr).get(i))) {
                    return true;
                }
            }
        }
        visited[curr] = 1;
        return false;
    }


    /**
     * 1011. Capacity To Ship Packages Within D Days
     */
    public int shipWithinDays(int[] weights, int days) {
        int totalLoad = 0, maxLoad = 0;

        for (int weight : weights) {
            totalLoad += weight;
            maxLoad = Math.max(maxLoad, weight);
        }

        int l = maxLoad, r = totalLoad;

        while (l < r) {
            int mid = (l + r) / 2;
            if (feasible(weights, mid, days)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }


    boolean feasible(int[] weights, int c, int days) {
        int daysNeeded = 1, currentLoad = 0;
        for (int weight : weights) {
            currentLoad += weight;
            if (currentLoad > c) {
                daysNeeded++;
                currentLoad = weight;
            }
        }

        return daysNeeded <= days;
    }


    public int minDistance(String word1, String word2) {
        int lenWord1 = word1.length();
        int lenWord2 = word2.length();

        int[][] dp = new int[lenWord1 + 1][lenWord2 + 1];


        for (int i = 1; i <= lenWord1; ++i)
            dp[i][0] = i;

        for (int j = 1; j <= lenWord2; ++j)
            dp[0][j] = j;

        for (int i = 1; i <= lenWord1; ++i) {
            for (int j = 1; j <= lenWord2; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[lenWord1][lenWord2];
    }

    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int x : nums)
            if (x % 2 == 0) {
                set.add(x);
            } else {
                set.add(x * 2);
            }
        int ans = Integer.MAX_VALUE;

        while (!set.isEmpty()) {
            int val = set.last();
            ans = Math.min(ans, val - set.first());
            if (val % 2 == 0) {
                set.remove(val);
                set.add(val / 2);
            } else break;
        }
        return ans;
    }


    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        preOrder(root, new HashMap<>(), res);
        return res;
    }

    public String preOrder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
        if (cur == null) return "#";
        String serial = cur.val + ",";
        serial += preOrder(cur.left, map, res) + ",";
        serial += preOrder(cur.right, map, res);

        map.put(serial, map.getOrDefault(serial, 0) + 1);
        if (map.get(serial) == 2) {
            res.add(cur);
        }
        return serial;
    }


    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int result = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] curr = q.poll();
                int x = curr[0], y = curr[1];
                for (int[] d : dir) {
                    int newX = x + d[0];
                    int newY = y + d[1];
                    if (newX < 0 || newY < 0 || newX >= n || newY >= n || grid[newX][newY] != 0) {
                        continue;
                    }
                    grid[newX][newY] = 1 + grid[x][y];
                    result = Math.max(result, grid[newX][newY]);
                    q.add(new int[]{newX, newY});
                }
            }
        }

        return result == 0 ? -1 : --result;
    }


    // merge sort
    public int[] sortArray(int[] nums) {
        mergeSort(nums, nums.length - 1);
        return nums;
    }

    public void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }


    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }


    public int compress(char[] chars) {
        int n = chars.length;
        if (n == 1) {
            return 1;
        }

        int i = 0, j = 0;
        while (i < n) {
            int count = 1;
            while (i < n - 1 && chars[i] == chars[i + 1]) {
                count++;
                i++;
            }

            chars[j++] = chars[i++];
            if (count > 1) {
                String countStr = String.valueOf(count);
                for (int k = 0; k < countStr.length(); k++) {
                    chars[j++] = countStr.charAt(k);
                }
            }
        }

        return j;
    }
}
}

