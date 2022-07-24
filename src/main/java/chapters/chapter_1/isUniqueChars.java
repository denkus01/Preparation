package chapters.chapter_1;

/**
 * Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
 * cannot use additional data structures?
 */
public class isUniqueChars {


    public static void main(String[] args) {
        String[] words = {"abcde", "hello", "apple", "kite", "padle"};
        for (String word : words) {
            boolean wordA = isUniqueCharsFirst(word);
            boolean wordB = isUniqueCharsSecond(word);
            System.out.println(word + "result_1:" + wordA);
            System.out.println(word + "result_2:" + wordB);
        }
    }

    /**
     * Assume, that encoding is ASCII (256 bit)
     * Complexity: O(n), but in fact -> O(1), because invariant limited to alphabet
     * Space: O(1)
     */
    private static boolean isUniqueCharsFirst(String input) {
        if (input.length() > 128) return false;
        boolean[] chars = new boolean[256];
        for (int i = 0; i < input.length(); i++) {
            int symbol = input.charAt(i);
            if (chars[symbol]) return false;
            chars[symbol] = true;
        }
        return true;
    }

    /* Assumes only letters a through z. */
    private static boolean isUniqueCharsSecond(String str) {
        if (str.length() > 26) { // Only 26 characters
            return false;
        }
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) return false;
            checker |= (1 << val);
        }
        return true;
    }
}
