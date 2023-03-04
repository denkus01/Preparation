package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DECEMBER_1_WEEK {

    /**
     * Need to be checked
     * 1475. Final Prices With a Special Discount in a Shop
     * O n
     */
    public int[] finalPrices(int[] prices) {
        int[] ans = new int[prices.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = prices.length - 1; i >= 0; i--) {
            if (stack.size() == 0) {
                ans[i] = prices[i];
            } else {
                while (stack.size() > 0 && stack.peek() > prices[i]) {
                    stack.pop();
                }
                if (stack.size() == 0) {
                    ans[i] = prices[i];
                } else {
                    ans[i] = prices[i] - stack.peek();
                }
            }
            stack.push(prices[i]);
        }

        return ans;
    }

    /**
     * 853. Car Fleet
     * On
     */
    public int carFleet(int target, int[] position, int[] speed) {
        if (position.length == 1) return 1;

        int ans = 0;
        int[] positionAndSpeed = new int[target];

        // fill array at position = speed
        for (int i = 0; i < speed.length; i++) {
            positionAndSpeed[position[i]] = speed[i];
        }

        double minTime = 0;
        for (int i = target - 1; i >= 0; i--) {
            if (positionAndSpeed[i] == 0) continue;
            double time = (target - i + 0.0) / positionAndSpeed[i];
            if (time > minTime) {
                minTime = time;
                ans++;
            }
        }
        return ans;
    }

    /**
     * 84. Largest Rectangle in Histogram
     * On - where n numbers are pushed and popped
     * On - for Stack
     */
    public int largestRectangleArea(int[] heights) {
        int area = 0, start;
        int n = heights.length;
        Stack<Pair<Number, S>> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int currentHigh = heights[i];
            start = i;
            while (!stack.isEmpty() && stack.peek().getValue() > currentHigh) {
                Pair<Number, S> pair = stack.pop();
                int index = pair.getKey();
                int height = pair.getValue();
                area = Math.max(area, height * (i - index));
                start = index;
            }
            stack.push(new Pair<Number, S>(start, currentHigh));
        }

        while (!stack.isEmpty()) {
            Pair<Number, S> pair = stack.pop();
            int index = pair.getKey();
            int height = pair.getValue();
            area = Math.max(area, height * (n - index));

        }
        return area;
    }

    /**
     * 206. Reverse Linked List
     * O n
     * O 1
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }


    /**
     * 21. Merge Two Sorted Lists
     * O(n+m)
     * O1
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        if (list1 != null) {
            tail.next = list1;
        } else if (list2 != null) {
            tail.next = list2;
        }
        return dummy.next;

    }

    /**
     * 143. Reorder List
     */
    public void reorderList(ListNode head) {
        if (head == null) return;

        // find the middle of linked list [Problem 876]
        // in 1->2->3->4->5->6 find 4
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second part of the list [Problem 206]
        // convert 1->2->3->4->5->6 into 1->2->3->4 and 6->5->4
        // reverse the second half in-place
        ListNode prev = null, curr = slow, tmp;
        while (curr != null) {
            tmp = curr.next;

            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        // merge two sorted linked lists [Problem 21]
        // merge 1->2->3->4 and 6->5->4 into 1->6->2->5->3->4
        ListNode first = head, second = prev;
        while (second.next != null) {
            tmp = first.next;
            first.next = second;
            first = tmp;

            tmp = second.next;
            second.next = first;
            second = tmp;
        }
    }


    /**
     * 19. Remove Nth Node From End of List
     * On
     * O1
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode currentNode = head;
        for (int i = 0; i < n; i++) {
            currentNode = currentNode.next;
        }

        if (currentNode == null && head != null) {
            return head.next;
        }
        ListNode beforeRemoved = head;

        while (currentNode.next != null) {
            currentNode = currentNode.next;
            beforeRemoved = beforeRemoved.next;
        }
        beforeRemoved.next = beforeRemoved.next.next;
        return head;
    }


    /**
     * 138. Copy List with Random Pointer
     * On
     * O1
     */
//    public Node copyRandomList(Node head) {
//
//        if (head == null) {
//            return null;
//        }
//
//        Node ptr = head;
//        while (ptr != null) {
//            Node newNode = new Node(ptr.val);
//            newNode.next = ptr.next;
//            ptr.next = newNode;
//            ptr = newNode.next;
//        }
//        ptr = head;
//
//        while (ptr != null) {
//            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
//            ptr = ptr.next.next;
//        }
//
//        Node ptr_old_list = head;
//        Node ptr_new_list = head.next;
//        Node head_old = head.next;
//
//        while (ptr_old_list != null) {
//            ptr_old_list.next = ptr_old_list.next.next;
//            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
//            ptr_old_list = ptr_old_list.next;
//            ptr_new_list = ptr_new_list.next;
//        }
//        return head_old;
//    }


    /**
     * 141. Linked List Cycle
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode(0), p = head;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            p.next = new ListNode(sum % 10);
            p = p.next;
            carry = sum / 10;
        }
        return head.next;
    }


    /**
     * 160. Intersection of Two Linked Lists
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();

        while (headB != null) {
            set.add(headB);
            headB = headB.next;
        }

        while (headA != null) {
            if (set.contains(headA)) {
                return headA;
            }
            headA = headA.next;
        }

        return null;
    }

    /**
     * 141. Linked List Cycle
     * O n
     * O n
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }


    /**
     * 287. Find the Duplicate Number
     * On
     * O1
     */
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }


    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }


    /**
     * 234. Palindrome Linked List
     * ON
     * O1
     */
    public boolean isPalindrome(ListNode head) {

        if (head == null) return true;

        ListNode halfToEnd = halfToEnd(head);
        ListNode halfToStart = reverseListNode(halfToEnd.next);

        ListNode p1 = head;
        ListNode p2 = halfToStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) result = false;
            p1 = p1.next;
            p2 = p2.next;
        }

        halfToEnd.next = reverseList(halfToStart);
        return result;
    }

    private ListNode halfToEnd(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverseListNode(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 203. Remove Linked List Elements
     * O n
     * O 1
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode resultNode = new ListNode(0);
        resultNode.next = head;

        ListNode prev = resultNode;
        ListNode curr = head;

        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return resultNode.next;
    }

    /**
     * 516. Longest Palindromic Subsequence
     */

    public int longestPalindromeSubseq(String s) {
        char[] arr = s.toCharArray();
        int size = arr.length;
        int[][] cache = new int[size][size];

        for (int i = size - 1; i >= 0; i--) {
            cache[i][i] = 1;
            for (int j = i + 1; j < size; j++) {
                if (arr[i] == arr[j]) {
                    cache[i][j] = 2;
                    if (i + 1 < j) {
                        cache[i][j] += cache[i + 1][j - 1];
                    }
                } else {
                    cache[i][j] = Math.max(cache[i + 1][j], cache[i][j - 1]);
                }
            }
        }
        return cache[0][size - 1];
    }


    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();

        for (int i = 1; i <= n; i++) {

            if (i % 3 == 0 && i % 5 == 0) {
                ans.add("FizzBuzz");
            } else if (i % 5 == 0) {
                ans.add("Buzz");
            } else if (i % 3 == 0) {
                ans.add("Fizz");
            } else {
                ans.add(Integer.toString(i));
            }

        }

        return ans;
    }


    /**
     * 169. Majority Element
     * O n
     * O 1
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int value = map.getOrDefault(num, 0) + 1;
            if (value > nums.length / 2) {
                return num;
            }
            map.put(num, value);
        }
        return map.size();
    }

    /**
     * _____________________LRU CACHE________________
     */
    private Map<Integer, Node> cache;
    private int capacity;
    private Node left;
    private Node right;

    public void LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();

        //left = LRU , right = most recent
        this.left = new Node(0, 0);
        this.right = new Node(0, 0);
        this.left.next = this.right;
        this.right.prev = this.left;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
            insert(cache.get(key));
            return cache.get(key).val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }
        cache.put(key, new Node(key, value));
        insert(cache.get(key));

        if (cache.size() > capacity) {
            // remove from the list and delte the LRU from the hashmap
            Node lru = this.left.next;
            remove(lru);
            cache.remove(lru.key);
        }
    }

    // remove node from list
    public void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    // insert node at right
    public void insert(Node node) {
        Node prev = this.right.prev;
        Node next = this.right;

        prev.next = node;
        next.prev = node;

        node.next = next;
        node.prev = prev;
    }

    private class Node {

        private int key;
        private int val;

        Node next;
        Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
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


    public class Pair<I extends Number, S> {
        Integer first;
        Integer second;

        Pair(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }

        public Integer getValue() {
            return this.second;
        }

        public Integer getKey() {
            return this.first;
        }

    }

    public static void main(String[] args) {
//        ListNode first = ListNode()
//        DECEMBER_1_WEEK dc = new DECEMBER_1_WEEK();
//        dc.largestRectangleArea(arr);
    }
}
