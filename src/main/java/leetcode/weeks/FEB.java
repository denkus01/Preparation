package leetcode.weeks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FEB {

    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }

        Set<String> set = new HashSet<>();

        for (List<String> list : similarPairs) {
            String pair = list.get(0) + "-" + list.get(1);
            set.add(pair);
        }

        for (int i = 0; i < sentence1.length; i++) {
            String pair1 = sentence1[i] + "-" + sentence2[i];
            String pair2 = sentence2[i] + "-" + sentence1[i];
            if (!pair1.equals(pair2) && !set.contains(pair1) && !set.contains(pair2)) {
                return false;
            }
        }

        return true;
    }

    //    Add to Array-Form of Integer
    public List<Integer> addToArrayForm(int[] num, int K) {
        int len = num.length;
        int cur = K;
        List<Integer> res = new ArrayList<>();

        int i = len;
        while (i-- >= 0 || cur > 0) {
            if (i >= 0){
                cur += num[i];
            }
            res.add(cur % 10);
            cur /= 10;
        }

        Collections.reverse(res);
        return res;
    }



}
