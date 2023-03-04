package leetcode.weeks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class February {

    /**
     * 904. Fruit Into Baskets
     */
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0, maxPicked = 0;

        for (int right = 0; right < fruits.length; right++) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0)
                    basket.remove(fruits[left]);
                left++;
            }

            maxPicked = Math.max(maxPicked, right - left + 1);
        }

        return maxPicked;
    }


    /**
     * 45. Jump Game II
     */
    public int jump(int[] nums) {
        int ans = 0, end = 0, fast = 0;

        for (int i = 0; i < nums.length - 1; ++i) {
            fast = Math.max(fast, i + nums[i]);
            if (fast >= nums.length - 1) {
                ans++;
                break;
            }
            if (i == end) {
                ans++;
                end = fast;
            }
        }

        return ans;
    }


    /**
     * 314. Binary Tree Vertical Order Traversal
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> output = new ArrayList<>();
            if (root == null) {
                return output;
            }

            Map<Integer, ArrayList> columnTable = new HashMap<>();
            // Pair of node and its column offset
            Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
            int column = 0;
            queue.offer(new Pair(root, column));

            int minColumn = 0, maxColumn = 0;

            while (!queue.isEmpty()) {
                Pair<TreeNode, Integer> p = queue.poll();
                root = p.getKey();
                column = p.getValue();

                if (root != null) {
                    if (!columnTable.containsKey(column)) {
                        columnTable.put(column, new ArrayList<Integer>());
                    }
                    columnTable.get(column).add(root.val);
                    minColumn = Math.min(minColumn, column);
                    maxColumn = Math.max(maxColumn, column);

                    queue.offer(new Pair(root.left, column - 1));
                    queue.offer(new Pair(root.right, column + 1));
                }
            }

            for(int i = minColumn; i < maxColumn + 1; ++i) {
                output.add(columnTable.get(i));
            }

            return output;
        }



    public long distinctNames(String[] ideas) {
        Set<String>[] sets = new Set[26];
        for (int i = 0; i < 26; i++) {
            sets[i] = new HashSet();
        }
        for (String s : ideas) {
            sets[s.charAt(0) - 'a'].add(s.substring(1));
        }
        int[][] same = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (String s : sets[i]) {
                for (int j = i + 1; j < 26; j++) {
                    if (sets[j].contains(s)) {
                        same[i][j]++;
                    }
                }
            }
        }
        long res = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = i + 1; j < 26; j++) {
                res += (long) (sets[i].size() - same[i][j]) * (sets[j].size() - same[i][j]) * 2;
            }
        }
        return res;
    }
}
