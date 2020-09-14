package chapters.chapter_1;

public class Task_6 {
    /**
     * String Compression: Implement a method to perform basic string compression using the counts
     * of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3 . If the
     * "compressed "string would not become smaller than the original string, your method should return
     * the original string. You can assume the string has only uppercase and lowercase letters (a - z),
     */
    private static String compress(String input) {
        StringBuilder buffer = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < input.length(); i++) {
            counter++;
            if (i + 1 >= input.length() || input.charAt(i) != input.charAt(i + 1)) {
                buffer.append(input.charAt(i)).append(counter);
                counter = 0;
            }
        }
        return buffer.length() < input.length() ? buffer.toString() : input;
    }

    public static void main(String[] args) {
        String str = "aabcccccaaa";
        System.out.println(str);
        System.out.println(compress(str));
    }
}
