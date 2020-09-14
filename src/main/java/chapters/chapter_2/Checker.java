package chapters.chapter_2;

public class Checker {
    public static void main(String[] args) {
        Long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE ; i++) {
            sum+=i;
        }
        System.out.println("Sum"+ sum);
    }
}
