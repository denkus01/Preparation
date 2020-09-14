package chapters.chapter_1;

import java.util.Arrays;

/**
 * Check Permutation: Given two strings, write a method to decide if one is a permutation of the
 * other.
 */
public class Task_2 {
    public static void main(String[] args) {
        String first = "acds";
        String second = "sdca";
        String third = "acdt";
        System.out.println(isPermutationFirst(first, second));
        System.out.println(isPermutationFirst(first, third));
        //System.out.println(isPermutationSecond(first, second));
        System.out.println(isPermutationSecond(first, third));
    }

    // O (n * log n)
    private static boolean isPermutationFirst(String a, String b) {
        if (a.length() != b.length()) return false;
        return sort(a).equals(sort(b));
    }

    private static String sort(String s) {
        char[] aChars = s.toCharArray();
        Arrays.sort(aChars);
        return new String(aChars);
    }

    private static boolean isPermutationSecond(String a, String b) {
        if (a.length() != b.length()) return false;

        int[] letters = new int[128];
        char[] chars = a.toCharArray();
        for (char c : chars) {
            letters[c]++;
        }

        for (int i = 0; i < b.length(); i++) {
            char symbol = b.charAt(i);
            letters[symbol]--;
            if (letters[symbol] < 0) {
                return false;
            }
        }
        return true;
    }
}
