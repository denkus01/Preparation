package LEETCODE_GOOGLE;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

public class ArraysAndString {
    // 27.07/2022
    // 159. Longest Substring with At Most Two Distinct Characters
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if (n < 3) return n;

        int i = 0;
        int j = -1;
        int maxLen = 2;

        for (int k = 1; k < n; k++) {
            if (s.charAt(k) == s.charAt(k - 1)) continue;

            if (j != -1 && s.charAt(k) != s.charAt(j)) {
                maxLen = Math.max(maxLen, k - i);
                i = j + 1;
            }

            j = k - 1;
        }

        return Math.max(maxLen, n - i);
    }

    //163. Missing Ranges
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int prev = lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            int curr = (i < nums.length) ? nums[i] : upper + 1;
            if (prev + 1 <= curr - 1) {
                result.add(formatRange(prev + 1, curr - 1));
            }
            prev = curr;
        }

        return result;
    }

    private String formatRange(int lower, int upper) {
        if (lower == upper) {
            return String.valueOf(lower);
        }
        return lower + "->" + upper;
    }

    //681. Next Closest Time
    public String nextClosestTime(String time) {
        /* Add all digits to a set */
        HashSet<Integer> s = new HashSet<>();
        for (int i = 0; i < time.length(); ++i) {
            if (i != 2) {
                s.add(Character.getNumericValue(time.charAt(i)));
            }
        }

        /* get the numeric value of hour and minute */
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));

        for (int i = 0; i < 24 * 60; ++i) {
            /* increase one minute to the current time */
            if (++minute > 59) {
                minute = 0;
                hour++;
            }
            if (hour > 23) {
                hour = 0;
            }

            /* if the set contains every digit of the current time,
                then the current time is the next closest time */
            if (s.contains(hour % 10) && s.contains(hour / 10) &&
                    s.contains(minute % 10) && s.contains(minute / 10)) {
                break;
            }

        }

        /* format the answer to string */
        return String.format("%02d:%02d", hour, minute);
    }

    // ------ 26/07/2022
//    809. Expressive Words
    public int expressiveWords(String S, String[] words) {
        int result = 0;
        for (String word : words) {
            if (isStretchy(S, word)) result++;
        }
        return result;
    }

    private boolean isStretchy(String S, String word) {
        int i = 0, j = 0;
        while (i < S.length() && j < word.length()) {
            System.out.println(S.charAt(i));
            System.out.println(word.charAt(j));

            if (S.charAt(i) != word.charAt(j)) return false;
            int sBlockSize = 1;
            i++;
            while (i < S.length() && S.charAt(i - 1) == S.charAt(i)) {
                System.out.println(S.charAt(i - 1));
                sBlockSize++;
                i++;
            }
            int wordBlockSize = 1;
            j++;
            while (j < word.length() && word.charAt(j - 1) == word.charAt(j)) {
                System.out.println(word.charAt(j));
                System.out.println(word.charAt(j - 1));
                wordBlockSize++;
                j++;
            }

            if (sBlockSize < wordBlockSize || (wordBlockSize < sBlockSize && sBlockSize < 3)) {
                return false;
            }
        }
        return i == S.length() && j == word.length();
    }

    //    833. Find And Replace in String
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        Map<Integer, Integer> table = new HashMap<>();
        for (int i = 0; i < indices.length; i++) {
            if (s.startsWith(sources[i], indices[i])) {
                table.put(indices[i], i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ) {
            if (table.containsKey(i)) {
                sb.append(targets[table.get(i)]);
                i += sources[table.get(i)].length();
            } else {
                sb.append(s.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    //  ----28 07 2022 -----
//    849. Maximize Distance to Closest Person
    public int maxDistToClosest(int[] seats) {
        int left = -1, maxDis = 0;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) continue;

            if (left == -1) {
                maxDis = i;
            } else {
                maxDis = Math.max(maxDis, (i - left) / 2);
            }

            left = i;
        }

        if (seats[seats.length - 1] == 0) {
            maxDis = Math.max(maxDis, seats.length - 1 - left);
        }
        return maxDis;
    }

    //20  Valid Parentheses
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                char p = stack.pop();
                System.out.println(c - p);
                if (c - p != 1 && c - p != 2) {
                    return false;
                }
            }
        }
        return stack.size() == 0;

    }

    //23. Merge k Sorted Lists

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });
        ListNode head = new ListNode(0), tail = head;
        for (ListNode node : lists) {
            if (node != null) heap.offer(node);
        }
        while (!heap.isEmpty()) {
            tail.next = heap.poll();
            tail = tail.next;
            if (tail.next != null) heap.offer(tail.next);
        }
        return head.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //31.07.2022
    //42. Trapping Rain Water
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;

            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;

            }
        }
        return water;
    }

    // 08.01.2022
    // Kth Largest Element in an Array
    // Input: nums = [3,2,1,5,6,4], k = 2
    //  Output: 5
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

    //02.08.2022
    // 253. Meeting Rooms II
    public int minMeetingRooms(int[][] intervals) {

        if (intervals.length == 0) {
            return 0;
        }

        PriorityQueue<Integer> allocator =
                new PriorityQueue<>(
                        intervals.length,
                        Comparator.comparingInt(a -> a)
                );

        Arrays.sort(
                intervals,
                Comparator.comparingInt(a -> a[0])
        );
        allocator.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }
            allocator.add(intervals[i][1]);
        }

        return allocator.size();
    }


    public int minMeetingRooms2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Get starts and ends separately
        int len = intervals.length;
        int[] starts = new int[len];
        int[] ends = new int[len];
        for (int i = 0; i < len; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        // Sort starts and ends array
        Arrays.sort(starts);
        Arrays.sort(ends);

        // Find min needed rooms
        int rooms = 0;
        for (int startIndex = 0, endIndex = 0; startIndex < len; startIndex++) {
            if (starts[startIndex] < ends[endIndex]) {
                // Start is smaller than end, add one room
                rooms++;
            } else {
                // Otherwise, move endIndex
                endIndex++;
            }
        }

        return rooms;
    }

    //03.08.2022
//    844. Backspace String Compare
    public boolean backspaceCompare(String S, String T) {
        return getValue(S).equals(getValue(T));
    }

    public String getValue(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        int count = 0;
        while (i >= 0) {
            if (s.charAt(i) == '#') {
                count++;
                i--;
                continue;
            }
            if (count == 0) {
                sb.append(s.charAt(i));
            } else {
                count--;
            }
            i--;
        }

        return sb.toString();
    }

    //04.08.2022
    //
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int N = quality.length;
        Worker[] workers = new Worker[N];
        for (int i = 0; i < N; ++i) {
            workers[i] = new Worker(quality[i], wage[i]);
            System.out.println("Worker quality:" + workers[i].quality + "Worker wage" + workers[i].wage);
        }

        Arrays.sort(workers);
        for (int i = 0; i < N; ++i) {
            System.out.println("Worker quality sort:" + workers[i].quality + "Worker wagesort" + workers[i].wage);
        }

        double ans = Double.MAX_VALUE;
        int sumq = 0;
        PriorityQueue<Integer> pool = new PriorityQueue();
        for (Worker worker : workers) {
            pool.offer(-worker.quality);
            sumq += worker.quality;
            if (pool.size() > K && !pool.isEmpty())
                sumq += pool.poll();
            if (pool.size() == K)
                ans = Math.min(ans, sumq * worker.ratio());
        }

        return ans;
    }

    //07.08.2022

    /**
     * 973. K Closest Points to Origin
     * 1st - Time Complexity: O(N \log N)O(NlogN), where NN is the length of points
     * Space Complexity: O(N)O(N).
     * <p>
     * 2nd - Time Complexity: O(N) in average case and O(N^2) in the worst case, where NN is the length of points
     * Space Complexity: O(N).
     */

    public int[][] kClosest(int[][] points, int K) {
        int N = points.length;
        int[] dists = new int[N];
        for (int i = 0; i < N; ++i)
            dists[i] = dist(points[i]);

        Arrays.sort(dists);
        int distK = dists[K - 1];

        int[][] ans = new int[K][2];
        int t = 0;
        for (int[] point : points)
            if (dist(point) <= distK)
                ans[t++] = point;
        return ans;
    }

    public int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    //2nd
    public int[][] kClosest3(int[][] points, int K) {
        int n = points.length;
        quickSelect(points, K - 1, 0, new Random()); // index from 0
        int[][] result = new int[K][];
        for (int i = 0; i < K; ++i) {
            result[i] = points[i];
        }
        return result;
    }

    // find the k-th element (from 0 ~ hi - 1)
    public int[][] kClosest2(int[][] points, int k) {

        if (points.length == k) return points;

        //To randomize choice of pivot and likelyness of worst O(n^2) appearing
        Random random = new Random();
        int start = 0, end = points.length - 1;
        while (start < end) {

            int mid = quickSelect(points, start, end, random);

            //k smallest elements found
            if (mid == k - 1) break;

            //not enough elements
            if (mid < k - 1)
                start = mid + 1;

            //too many elements
            if (mid > k - 1)
                end = mid - 1;
        }

        //copyOfRange end index is exclusive
        return Arrays.copyOfRange(points, 0, k);
    }

    private int quickSelect(int[][] arr, int left, int right, Random random) {
        //get pivot at random location in unsorted part of arr
        int pivotI = left + random.nextInt(right - left);
        int pivotDist = dist(arr[pivotI]);

        //use pointer to keep track of where next the next arr that should be swapped should go
        int pointer = left;

        //place pivot on right side as a place holder to later be swapped into its respective location with < on left and > right
        swap(arr, pivotI, right);

        for (int i = left; i < right; i++) {

            if (dist(arr[i]) <= pivotDist) {
                if (i != pointer) swap(arr, i, pointer);
                pointer++;
            }
        }

        //place pivot in its respective spot
        swap(arr, pointer, right);

        return pointer;
    }

    private void swap(int[][] arr, int a, int b) {
        int[] temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 754. Reach a Number
     * Time Complexity: O(sqrt (target)
     * <p>
     * Space Complexity: O(1)O(1).
     */

    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0)
            target -= ++k;
        System.out.println(k + 1 + k % 2);
        System.out.println(target % 2);
        return target % 2 == 0 ? k : k + 1 + k % 2;
    }

    /**
     * 53. Maximum Subarray
     * <p>
     * Time complexity: O(N),
     * Space complexity: O(1)
     */

    public int maxSubArray(int[] nums) {
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
     * 1131. Maximum of Absolute Value Expression
     * O(n^2)
     */

    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int ans = 0;
        int cur;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1.length - i; j++) {
                cur = Math.abs(arr1[j] - arr1[j + i]) + Math.abs(arr2[j] - arr2[j + i]) + i;
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }

    /**
     * 1131. Maximum of Absolute Value Expression
     * O(n)
     */
    public int maxAbsValExpr2(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return 0;
        }

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int max4 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int min3 = Integer.MAX_VALUE;
        int min4 = Integer.MAX_VALUE;
        for (int i = 0; i < arr1.length; i++) {
            max1 = Math.max(arr1[i] + arr2[i] + i, max1);
            min1 = Math.min(arr1[i] + arr2[i] + i, min1);
            max2 = Math.max(i - arr1[i] - arr2[i], max2);
            min2 = Math.min(i - arr1[i] - arr2[i], min2);
            max3 = Math.max(arr1[i] - arr2[i] + i, max3);
            min3 = Math.min(arr1[i] - arr2[i] + i, min3);
            max4 = Math.max(arr2[i] - arr1[i] + i, max4);
            min4 = Math.min(arr2[i] - arr1[i] + i, min4);
        }
        return Math.max(Math.max(max1 - min1, max2 - min2), Math.max(max3 - min3, max4 - min4));
    }

    /**
     * 7. Reverse Integer
     */

    public int reverse(int x) {
        int res = 0;
        int pop;
        while (x != 0) {
            pop = x % 10;
            if (pop < 0) {
                if (res < (Integer.MIN_VALUE - pop) / 10) {
                    return 0;
                }
            } else if (res > (Integer.MAX_VALUE - pop) / 10) {
                return 0;
            }
            res = res * 10 + pop;
            x /= 10;
        }
        return res;
    }

    /**
     * 56. Merge Intervals
     * Time complexity : O(n log n)
     * Space complexity : O(log N) (or O(n))
     */
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> answer = new LinkedList<>();

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (answer.isEmpty() || answer.getLast()[1] < interval[0]) {
                answer.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {

                answer.getLast()[1] = Math.max(answer.getLast()[1], interval[1]);
            }
        }
        return answer.toArray(new int[answer.size()][]);
    }

    /** 11/08/2022
     * 46. Permutations
     *
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used ;
        if(nums.length == 0) return res;
        used = new boolean[nums.length];
        List<Integer> permutation = new ArrayList<>();
        helper(nums, permutation, used, res);
        return res;
    }

    private void helper(int[] nums, List<Integer> perm, boolean[] used, List<List<Integer>> res) {
        if(perm.size() == nums.length){
            res.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            perm.add(nums[i]);
            helper(nums,perm, used, res);
            perm.remove(perm.size() - 1);
            used[i] = false;
        }
    }

}

class Worker implements Comparable<Worker> {
    public int quality, wage;

    public Worker(int q, int w) {
        quality = q;
        wage = w;
    }

    public double ratio() {
        return (double) wage / quality;
    }

    public int compareTo(Worker other) {
        return Double.compare(ratio(), other.ratio());
    }
}
