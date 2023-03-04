package leetcode.weeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class January2 {

    /*
    1099. Two Sum Less Than K
     */
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1, res = -1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < k) {
                res = Math.max(res, sum);
                left++;
            } else {
                right--;
            }
        }
        return res;
    }


    /*
    367. Valid Perfect Square
     */
    public boolean isPerfectSquare(int num) {
        if (num < 2) return true;


        long left = 2;
        long right = num / 2, mid, guessSquared;

        while (left <= right) {
            mid = left + (right - left) / 2;
            guessSquared = mid * mid;
            if (guessSquared == num) {
                return true;
            }
            if (guessSquared > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    /**
     * 57. Insert Interval
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> result = new ArrayList<>();

        for (int[] slot : intervals) {

            if (newInterval[1] < slot[0]) {
                result.add(newInterval);
                newInterval = slot;
            } else if (slot[1] < newInterval[0]) {
                result.add(slot);
            } else {
                newInterval[0] = Math.min(newInterval[0], slot[0]);
                newInterval[1] = Math.max(newInterval[1], slot[1]);
            }
        }

        result.add(newInterval);

        return result.toArray(new int[result.size()][]);
    }

    /**
     * 852. Peak Index in a Mountain Array
     */
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 4. Median of Two Sorted Arrays
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int total = m + n;
        int half = (total + 1) / 2;

        int left = 0;
        int right = m;

        double result = 0.0;

        while (left <= right) {
            int i = left + (right - left) / 2;
            int j = half - i;

            int left1 = (i > 0) ? nums1[i - 1] : Integer.MIN_VALUE;
            int right1 = (i < m) ? nums1[i] : Integer.MAX_VALUE;
            int left2 = (j > 0) ? nums2[j - 1] : Integer.MIN_VALUE;
            int right2 = (j < n) ? nums2[j] : Integer.MAX_VALUE;

            if (left1 <= right2 && left2 <= right1) {
                if (total % 2 == 0) {
                    result = (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    result = Math.max(left1, left2);
                }
                break;
            } else if (left1 > right2) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }

        return result;
    }

    /*
    926. Flip String to Monotone Increasing
     */
    public int minFlipsMonoIncr(String s) {
        int zeroes = 0, onesToZeroes = 0;

        for (char c : s.toCharArray()) {
            if (c == '0') {
                zeroes++;
            } else {
                onesToZeroes++;
            }
            zeroes = Math.min(zeroes, onesToZeroes);
        }

        return zeroes;
    }

    /**
     * 718. Maximum Length of Repeated Subarray
     * Time Complexity: O(n^2), Space O(n)
     */
    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[] prev = new int[n + 1];
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            int[] cur = new int[n + 1];
            for (int j = 1; j <= m; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    cur[j] = prev[j - 1] + 1;
                } else {
                    cur[j] = 0;
                }
                ans = Math.max(ans, cur[j]);
            }
            prev = cur;
        }
        return ans;
    }


    public int maxSubarraySumCircular(int[] nums) {
        int curMax = 0, curMin = 0, sum = 0;
        int maxSum = nums[0], minSum = nums[0];
        int res;

        for (int num : nums) {
            curMax = Math.max(curMax, 0) + num;
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin, 0) + num;
            minSum = Math.min(minSum, curMin);
            sum += num;
        }
        if (sum == minSum) {
            res = maxSum;
        } else {
            res = Math.max(maxSum, sum - minSum);
        }
        return res;
    }

    /**
     * 202. Happy Number
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (set.add(n)) {
            int total = 0;
            while (n > 0) {
                total += (n % 10) * (n % 10);
                n /= 10;
            }
            if (total == 1)
                return true;
            else
                n = total;
        }
        return false;
    }


    public int subarraysDivByK(int[] nums, int k) {
        int prefixMod = 0, result = 0;

        int[] mod = new int[k];
        mod[0] = 1;

        for (int num : nums) {
            prefixMod = (prefixMod + num % k + k) % k;
            result += mod[prefixMod];
            mod[prefixMod]++;
        }

        return result;
    }


    /**
     * 34. Find First and Last Position of Element in Sorted Array
     */
    public int[] searchRange(int[] nums, int target) {

        int firstOccurrence = this.findBound(nums, target, true);

        if (firstOccurrence == -1) {
            return new int[]{-1, -1};
        }

        int lastOccurrence = this.findBound(nums, target, false);

        return new int[]{firstOccurrence, lastOccurrence};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int len = nums.length;
        int begin = 0, end = len - 1;

        while (begin <= end) {
            int mid = (begin + end) / 2;

            if (nums[mid] == target) {
                if (isFirst) {
                    if (mid == begin || nums[mid - 1] != target) {
                        return mid;
                    }
                    end = mid - 1;
                } else {
                    if (mid == end || nums[mid + 1] != target) {
                        return mid;
                    }
                    begin = mid + 1;
                }

            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }

        return -1;
    }

    public boolean canAttendMeetings(int[][] intervals) {

        Arrays.sort(intervals, (a1, a2) -> Integer.compare(a1[0], a2[0]));

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }

    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }

        return ans;
    }

    /**
     * 83. Remove Duplicates from Sorted List
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    /**
     * 328. Odd Even Linked List
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;
        ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed

        // 1 - 2 -3 - 4 - 5 ; m=2; right =4 ---> pre = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5

        for (int i = 0; i < right - left; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 -  2 - 5; pre = 1, start = 2, then = 5 (finish)

        return dummy.next;

    }

    /**
     * 441. Arranging Coins
     */
    public int arrangeCoins(int n) {
        long left = 0, right = n;
        long rowCount, curr;

        while (left <= right) {
            rowCount = left + (right - left) / 2;
            curr = rowCount * (rowCount + 1) / 2;

            if (curr == n) {
                return (int) rowCount;
            }

            if (n < curr) {
                right = rowCount - 1;
            } else {
                left = rowCount + 1;
            }
        }
        return (int) right;
    }


    /**
     * 163. Missing Ranges
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {

        List<String> result = new ArrayList<>();

        for (int n : nums) {
            int nextOne = n - 1;
            if (lower == nextOne) {
                result.add(lower + "");
            } else if (lower < nextOne) {
                result.add(format(lower, nextOne));
            }
            lower = n + 1;
        }
        if (lower == upper) {
            result.add(lower + "");
        } else if (lower < upper) {
            result.add(format(lower, upper));
        }
        return result;

    }

    private String format(int left, int right) {
        return (left == right) ? String.valueOf(left) : left + "->" + right;
    }

    /**
     * 1046. Last Stone Weight
     * O n log n
     * O n
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (int stone : stones) {
            maxHeap.add(stone);
        }

        while (maxHeap.size() > 1) {
            int stone1 = maxHeap.remove();
            int stone2 = maxHeap.remove();
            if (stone1 != stone2) {
                maxHeap.add(stone1 - stone2);
            }
        }
        return maxHeap.size() != 0 ? maxHeap.remove() : 0;
    }

    /**
     * 506. Relative Ranks
     */
    public String[] findRelativeRanks(int[] score) {

        int len = score.length;
        String[] res = new String[len];

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            final int i = score[b] - score[a];
            System.out.println("Score b:" + score[b]);
            System.out.println("Score a:" + score[a]);
            return i;
        });

        for (int i = 0; i < len; i++) {
            pq.add(i);
        }
        int i = 1;
        while (!pq.isEmpty()) {

            int idx = pq.poll();

            if (i > 3) {
                res[idx] = Integer.toString(i);
            } else if (i == 1) {
                res[idx] = "Gold Medal";
            } else if (i == 2) {
                res[idx] = "Silver Medal";
            } else if (i == 3) {
                res[idx] = "Bronze Medal";
            }
            i++;
        }

        return res;

    }

    /**
     * 997. Find the Town Judge
     * O n
     * O n
     */
    public int findJudge(int n, int[][] trust) {
        int[] arr = new int[n + 1];

        for (int[] ints : trust) {
            arr[ints[0]]--;
            arr[ints[1]]++;

        }

        for (int i = 1; i <= n; i++) {
            if (arr[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 1338. Reduce Array Size to The Half
     */
    public int minSetSize(int[] arr) {

        int maxVal = arr[0];

        for (int i : arr) {
            maxVal = Math.max(maxVal, i);
        }
        int[] frequencies = new int[maxVal + 1];

        for (int i : arr) {
            frequencies[i]--;
        }
        Arrays.sort(frequencies);

        int size = arr.length;
        int count = 0;
        while (size > arr.length / 2) {
            size += frequencies[count];
            count++;
        }
        return count;
    }


    private int binarySearch(int[] row) {
        int low = 0;
        int high = row.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (row[mid] == 1) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            else return b[0] - a[0];
        });

        for (int i = 0; i < m; i++) {
            int strength = binarySearch(mat[i]);
            int[] entry = new int[]{strength, i};
            pq.add(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] ind = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            int[] pair = pq.poll();
            ind[i] = pair[1];
        }

        return ind;
    }


    // 252. Meeting rooms
    //[[0,30],[5,10],[15,20]]

    public boolean canAttendMeetings2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;

    }

    public int canAttendMeetings3(int[][] intervals) {
        int[] startTime = new int[intervals.length];
        int[] endTime = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            startTime[i] = intervals[i][0];
            endTime[i] = intervals[i][1];
        }

        Arrays.sort(startTime);
        Arrays.sort(endTime);

        int x = 0;
        int y = 0;
        int roomCount = 0;
        while (x < intervals.length) {
            if (endTime[y] <= startTime[x]) {
                roomCount -= 1;
                y += 1;
            }
            roomCount += 1;
            x += 1;
        }
        return roomCount;
    }

    public int alternateDigitSum(int n) {
        String number = Integer.toString(n);
        int result = Character.getNumericValue(number.charAt(0));
        for (int i = 1; i < number.length(); i++) {
            int digit = Character.getNumericValue(number.charAt(i));
            result += Math.pow(-1, i) * digit;
        }
        return result;
    }


    /**
     * 1898. Maximum Number of Removable Characters
     */
    // This is to check if p is subsequence of s. Time complexity will be O(s.length());
    public boolean isSubsequence(char[] s, char[] p){
        int i = 0, j = 0;
        while(i < s.length && j < p.length){
            if(s[i] == p[j]){
                i++;
                j++;
            }
            else i++;
        }

        // If our j pointer reaches the end of P string or P char[] then P is a subsequence of S so return TRUE otherwise return FALSE.
        return j == p.length;
    }

    public int maximumRemovals(String s, String p, int[] removable) {
        char[] sChar = s.toCharArray();
        char[] pChar = p.toCharArray();
        int l = 0;
        int r = removable.length - 1;

        while(l <= r){
            int mid = l + (r-l)/2;

            // Let's check till the mid point of removable array and find whether after removing all those indices from s, p remains a subsequence of s or not? I used '* to indicate that I removed those indices.'
            for(int i = l; i <= mid; i++) sChar[removable[i]] = '*';

            // If it is a subsequence then this might be possible that the value of K is more but we are only checking till mid so we need to move right, hence l = mid+1;
            if(isSubsequence(sChar, pChar)){
                l = mid+1;
            }

            // If we remove the indices till mid point but our K is less i.e, we can't remove mid+1(we start our iteration considering 0 indexed) numbers of elements then it might be possible that our K is less than mid+1. so we need to move left. Since we have already checked till mid now we need to set our r = mid-1.
            else{
                for(int i = l; i <= mid; i++) sChar[removable[i]] = s.charAt(removable[i]);
                r = mid - 1;
            }
        }

        // Since we have programmed based on 0 indexed our r might go to -1 or our r value will stay at the end index of removable array so to find out toal number of K we need to do r+1;
        return r+1;

        // Time complexity for this method will be Time complexity for (isSubsequence() * BinarySearch(removable)) so total TIme complexity will be O(s.length*log(removable.length));
    }


}




