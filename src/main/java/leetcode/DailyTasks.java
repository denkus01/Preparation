package leetcode;

import java.util.HashMap;
import java.util.Map;

public class DailyTasks {

    /**
     * 953. Verifying an Alien Dictionary
     */
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> sort = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            sort.put(order.charAt(i), i);
        }

        for (int i = 1; i < words.length; i++) {
            String a = words[i - 1];
            String b = words[i];
            for (int j = 0; j < a.length(); j++) {
                if (j == b.length()) {
                    return false;
                }
                char cha = a.charAt(j);
                char chb = b.charAt(j);
                if (sort.get(cha) < sort.get(chb)) {
                    break;
                }
                if (sort.get(cha) > sort.get(chb)) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 6. Zigzag Conversion
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        StringBuilder answer = new StringBuilder();
        int wordLen = s.length();
        int diff = 2 * (numRows - 1);
        int diagonalDiff, secondIndex, index;

        for (int i = 0; i < numRows; i++) {
            index = i;
            while (index < wordLen) {

                answer.append(s.charAt(index));

                if (i != 0 && i != numRows - 1) {
                    diagonalDiff = diff - 2 * i;
                    secondIndex = index + diagonalDiff;

                    if (secondIndex < wordLen) {
                        answer.append(s.charAt(secondIndex));
                    }
                }
                index += diff;
            }
        }

        return answer.toString();

    }
}

