package chapters.chapter_1;

public class Task_9 {
    /**
     * String Rotation
     *
     * Assume you have a method isSubstring which checks if one word is a substring of another.
     * Given two strings, sl and s2, write code to check if s2 is a rotation of s1 using only one call
     * to isSubstring (e.g.,"waterbottle" is a rotation of"erbottlewat").
     *
     */
    public static void main(String[] args) {
        System.out.println(isRotatedString("waterbottle", "erbottlewat"));
    }

    private static boolean isRotatedString(String input, String example) {
        return isSubstring(input + input, example);
    }

    private static boolean isSubstring(String input, String example) {
        return input.indexOf(example) > 0;
    }
}
