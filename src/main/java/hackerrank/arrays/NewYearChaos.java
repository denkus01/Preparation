package hackerrank.arrays;

public class NewYearChaos {

    static void minimumBribes(int[] q) {
        int moveCount = 0;

        for (int i = 0; i < q.length; i++) {
            if (q[i] - (i + 1) > 2) {
                System.out.println("Too chaotic");
                return;
            }
            for (int j = Math.max(0, q[i] - 2); j < i; j++)
                if (q[j] > q[i])
                    moveCount++;

        }
        System.out.println(moveCount);
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3, 7, 8, 6, 4};
        NewYearChaos.minimumBribes(arr);
    }
}
