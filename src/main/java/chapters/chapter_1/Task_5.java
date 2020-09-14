package chapters.chapter_1;

public class Task_5 {
    /**
     * There are three types of edits that can be performed on strings: insert a character, remove a character,
     * or replace a character. Given two strings, write a function to check if they are one edit ,
     * (or zero edits) away.
     * EXAMPLE
     * pale, pie -> true
     * pales, pale -> true
     * pale, bale -> true
     * pale, bae - > false
     */

    public static boolean oneEdit(String first, String second) {
        if (!first.trim().equals(second.trim())) return false;
        String s1 = first.length() < second.length() ? first : second;
        String s2 = first.length() < second.length() ? second : first;
        return true;
    }
}
