package problem;

import java.util.ArrayList;
import java.util.List;

public class OptimalGameStrategy {

    public static void main(String[] args) {

        int[] coins = {4, 15, 7, 3, 8, 9};
        for (int coin : coins) {
            System.out.print(coin + " ");
        }
        System.out.println();
        Game game = play(coins);
        System.out.println("Excepted Result = " + game.getExceptedResult());
        System.out.println("DP Table : ");
        printDpTable(game.getDbTable());
        System.out.println("The coin give excepted result = " + game.getCoins().get(0));

    }

    public static Game play(int[] A) {

        int n = A.length;
        int[][] dp = new int[n][n];
        int[][] choose = new int[n][n];
        for (int len = 0; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                if (i == j) {
                    dp[i][j] = A[i];
                    choose[i][j] = -1;
                } else if (i + 1 == j) {
                    dp[i][j] = Math.max(A[i], A[j]);
                    choose[i][j] = (A[i] > A[j]) ? i : j;
                } else {
                    int option1 = A[i] + Math.min(dp[i+2][j], dp[i+1][j-1]);
                    int option2 = A[j] + Math.min(dp[i+1][j-1], dp[i][j-2]);
                    dp[i][j] = Math.max(option1, option2);
                    choose[i][j] = (option1 > option2) ? i : j;
                }
            }
        }

        // Backtrack to get the coins
        List<Integer> coins = new ArrayList<>();
        int i = 0, j = n - 1;
        while (i <= j) {
            if (choose[i][j] == -1) {
                coins.add(A[i]);
                i++;
            } else if (choose[i][j] == i) {
                coins.add(A[i]);
                i += 2;
            } else {
                coins.add(A[j]);
                j -= 2;
            }
        }

        return new Game(dp[0][n-1], dp, coins);

    }

    public static void printDpTable(int[][] dp){

        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + "  ");
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

}
