class Solution {
    public int maximumAmount(int[][] coins) {
        return mySol(coins);
    }
    
    public int mySol(int[][] coins) {
        return topdown(coins, 0, 0, 2, new Integer[coins.length][coins[0].length][3]);
    }

    public int topdown(int[][] coins, int i, int j, int pass, Integer[][][] memo) {
        int min = 500 * 500 * -1001;

        if (i == coins.length && j == coins[0].length - 1
            || i == coins.length - 1 && j == coins[0].length) return 0;

        if (i >= coins.length || j >= coins[0].length) return min;

        if (memo[i][j][pass] != null) return memo[i][j][pass];

        int ans = min;
        int m = coins[i][j];

        int down = topdown(coins, i + 1, j, pass, memo);

        if (down >= min) {
            ans = Math.max(ans, m + down);
        }

        int right = topdown(coins, i, j + 1, pass, memo);

        if (right >= min) {
            ans = Math.max(ans, m + right);
        }

        if (m < 0 && pass > 0) {
            down = topdown(coins, i + 1, j, pass - 1, memo);

            if (down >= min) {
                ans = Math.max(ans, down);
            }

            right = topdown(coins, i, j + 1, pass - 1, memo);

            if (right >= min) {
                ans = Math.max(ans, right);
            }
        }

        return memo[i][j][pass] = ans;
    }
}