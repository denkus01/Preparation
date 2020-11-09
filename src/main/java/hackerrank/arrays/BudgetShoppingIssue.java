package hackerrank.arrays;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BudgetShoppingIssue {

    /**
     * Problem:
     * Budget Shopping
     *
     * Helen has n dollars budgeted to purchase Math notebooks. There are m stores that stock unlimited supplies of notebooks in bundles,
     * but the size and price of the bundles vary from store to store. Helen can purchase as many bundles as she wants from as many stores
     * as necessary until she depletes her budget, but her goal is to purchase a maximal number of notebooks. For example,
     * if Helen has n = 5 dollars and there are m = 2 shops where one sells 4-notebook bundles for 2 dollars a piece and the other sells
     * 2-notebook bundles for 1 dollar a piece, she will buy a total of 4 + 4 + 2 = 10 notebooks (two bundles of 4 from the first shop and
     * one bundle of 2 from the second shop).
     *
     * Constraints
     *
     * 1 ≤ n ≤ 104
     * 1 ≤ m ≤ 102
     * 1 ≤ bundleQuantitiesi ≤ 20
     * 1 ≤ bundleCostsi ≤ 200
     *
     * Input Format For Custom Testing
     *
     * The first line contains an integer, n, denoting the number of dollars in Helen's notebook budget.
     *
     * The next line contains an integer, m, denoting the number of shops.
     *
     * Each line i of the m subsequent lines (where 0 ≤ i < m) contains an integer describing bundleQuantitiesi.
     *
     * The next line contains an integer, m, denoting the number of shops.
     *
     * Each line i of the m subsequent lines (where 0 ≤ i < m) contains an integer describing bundleCostsi.
     * Sample Case 0
     *
     * Sample Input
     *
     * 50
     * 2
     * 20
     * 19
     * 2
     * 24
     * 20
     */


    public static int budgetShopping(int budget, List<Integer> bundleQuantities, List<Integer> bundleCosts) {
        int[] temp = new int[budget + 1];

        int qtdVendors = bundleCosts.size();
        for (int i = 0; i <= budget; i++) {
            for (int j = 0; j < qtdVendors; j++) {
                int cost = bundleCosts.get(j);
                if (cost <= i) {
                    int quantity = bundleQuantities.get(j);
                    int tempCost = temp[i - cost];
                    temp[i] = Math.max(temp[i], tempCost + quantity);
                    System.out.println("i: " + i + ", temp: " + temp[i]);
                }
            }
        }
        return temp[budget];
    }

    public static void main(String[] args) throws IOException {

        int n = 50;
        List<Integer> bundleQuantities = Arrays.asList(20, 19);
        List<Integer> bundleCosts = Arrays.asList(24, 20);
        // result: 40

        int result = BudgetShoppingIssue.budgetShopping(n, bundleQuantities, bundleCosts);
        System.out.println(result);
    }
}


