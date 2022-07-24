package chapters.chapter_1;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class isPalindrome {
    public static void main(String[] args) {
        String first = "acds";
        System.out.println(isValidBrackets("(({}[()]))"));
    }


    /**
     * Palindrome
     */
    static boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    static boolean isValidBrackets(String str) {
        Map<Character, Character> brackets = new HashMap<>();
        brackets.put(')', '(');
        brackets.put('}', '{');
        brackets.put(']', '[');

        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (brackets.containsValue(c)) {
                stack.push(c);
            } else if (brackets.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != brackets.get(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

