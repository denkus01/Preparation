package chapters.chapter_1;

import java.util.Arrays;

public class TwoStrings {

    static String twoStrings(String s1, String s2) {
        boolean[] array = new boolean[26];
        Arrays.fill(array,false);

        for (int i = 0; i < s1.length(); i++) {
            array[s1.charAt(i) - 'a'] = true;
        }

        for (int i = 0; i < s2.length(); i++) {
            if(array[s2.charAt(i)-'a']) return "YES";
        }
        return "NO";
    }

    public static void main(String[] args) {
        TwoStrings.twoStrings("abcd", "rted");
    }
}
