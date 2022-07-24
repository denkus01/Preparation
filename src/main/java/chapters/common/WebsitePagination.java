package chapters.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WebsitePagination {

    public static String GasStation(String[] strArrInput) {
        String[] strArr = Arrays.copyOfRange(strArrInput, 1, strArrInput.length);

        for (int i = 0; i < strArr.length; i++) {
            int startIndex = i;
            int count = 0;
            int inputGas = 0;

            while (count < strArr.length) {
                String[] firstStation = strArr[startIndex].split(":");
                inputGas += Integer.parseInt(firstStation[0]);
                int gasNeeded = Integer.parseInt(firstStation[1]);

                if (inputGas < gasNeeded) break;
                else {
                    inputGas -= Integer.parseInt(firstStation[1]);
                    startIndex = startIndex + 1 == strArr.length ? 0 : startIndex + 1;
                    count++;
                }
            }
            if (count == strArr.length) {
                return Integer.toString(i + 1);
            }
        }
        return "impossible";
    }

    public static void BinaryTreeLCA(String[] strArr) {
//        List<String> nodeAPath = path();
//
//        String data = strArr[0].substring(1, strArr[0].length - 1);
//
//        List<String> nodeBPath = path();
//
//        //"[1, 2, 4]" => ["1" , "2"]
//        String[] heaNods = strArr[0].split(", ");
//
//        for (String node : nodeAPath) {
//            if (nodeBPath.contains(node) && !node.equals("#")) {
//                return node;
//            }
//        }
//
//        return "-1";
    }

    public static List<String> path(String[] arr, String key) {
        List<String> path = new LinkedList<>();
        int keyIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(key)) {
                keyIndex = i;
                break;
            }
        }
        if (keyIndex == -1) return path;

        while (keyIndex != 0) {
            path.add(arr[keyIndex]);
            keyIndex = Math.abs((keyIndex - 1) / 2);
        }
        path.add(arr[0]);
        return path;
    }

    private static void dd() {
        Set<Integer> uniqueArray = new HashSet<Integer>();
        uniqueArray.stream().reduce(0, Integer::sum);
    }

    public static List<String> checkIps(List<String> ips) {
        return ips.stream().map(WebsitePagination::validateIp).collect(Collectors.toList());
    }

    public static String validateIp(String ip) {
        String[] address = ip.split("\\.", -1);

        if (address.length == 4) {
            for (String adr : address) {
                if (adr.length() < 1 || adr.length() > 3 || adr.charAt(0) == '0' && adr.length() > 1) {
                    return "Neither";
                }

                for (char sumb : adr.toCharArray()) {
                    if (!Character.isDigit(sumb)) {
                        return "Neither";
                    }
                }

                int value = Integer.parseInt(adr);

                if (value < 0 || value > 255) {
                    return "Neither";
                }
            }
            return "IPv4";
        }

        address = ip.split("\\:", -1);
        if (address.length == 8) {
            for (String adr : address) {
                if (adr.length() < 1 || adr.length() > 4) {
                    return "Neither";
                }

                for (char sumb : adr.toCharArray()) {

                    if (!Character.isLetter(sumb) && !Character.isDigit(sumb)) {
                        return "Neither";
                    }

                    if (Character.isLetter(sumb)) {
                        if ((Character.isLowerCase(sumb) && sumb > 'f')
                                || (Character.isLowerCase(sumb) && sumb > 'F')) {
                            return "Neither";
                        }
                    }
                }
            }
            return "IPv6";
        }
        return "Neither";
    }

    public static String LongestWord(String sen) {
        String[] words = sen.trim().replaceAll("[^a-zA-Z0-9]", "").split(" ");
        String longestWord;
        longestWord = words[0];

        for (int i = 1; i < words.length; i++) {
            if (longestWord.length() < words[i].length()) {
                longestWord = words[i];
            }
        }
        return longestWord;
    }

    public static String Palindrome(String str) {
        String element = str.replaceAll("\\s+", "").toLowerCase();
        boolean isPolindrome = IntStream.range(0, element.length() / 2)
                .noneMatch(leter -> element.charAt(leter) != element.charAt(element.length() - leter - 1));

        return isPolindrome ? "true" : " false";
    }

    public static int CoinDeterminer(int num) {

        int[] coins = {1, 5, 7, 9, 11};
        int[] min = new int[num + 1];
        //System.out.println(Arrays.toString(Min));

        for (int i = 1; i <= num; i++) {
            min[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length - 1; j++) {
                if (i - coins[j] >= 0) {
                    min[i] = Math.min(min[i], min[i - coins[j]] + 1);
                }
            }
        }
        //System.out.println(Arrays.toString(Min));
        return min[num];

    }

    public static int decryption(String input) {
        String[] results = input.split(",");
        Map wordsMap = new HashMap<Integer, String>();

        for (int i = 0; i < results.length; i++) {
            int leng = results[i].toCharArray().length;
            char[] chars = results[i].toCharArray();
            int wordNum = 0;
            for (int j = 0; j < leng; j++) {
                if (Character.isDigit(chars[j])) {
                    int res = Integer.parseInt(String.valueOf(chars[j]));
                    if (wordNum == 0) {
                        wordNum = res;
                    } else {
                        wordNum += res;
                    }
                }
            }

            wordsMap.put(wordNum, results[i]);
        }

        return 14;

    }

    public static void main(String[] args) {
        decryption("t1e1s2t, Hell1o, 1th1e, fir2st1");
    }


}

