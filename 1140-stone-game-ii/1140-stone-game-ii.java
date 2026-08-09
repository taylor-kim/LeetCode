class Solution {
    public int stoneGameII(int[] piles) {
        return mySol(piles);
    }

    public int mySol(int[] piles) {
        int total = 0;

        for (int num : piles) {
            total += num;
        }

        int alice = topdown(piles, 0, 1, new Integer[piles.length][piles.length + 1]);

        // a + b = total
        // a - b = alice

        // a = total - b
        // a = alice + b
        // total - b == alice + b
        // total - alice = 2b
        // b = (total - alice) / 2
        // a = total - (total - alic) / 2

        return total - (total - alice) / 2;
    }

    public int topdown(int[] piles, int index, int m, Integer[][] memo) {
        if (index >= piles.length) return 0;

        if (memo[index][m] != null) return memo[index][m];

        int ans = Integer.MIN_VALUE;
        int stones = 0;

        for (int x = 1; x <= 2 * m && index + x - 1 < piles.length; x++) {
            stones += piles[index + x - 1];

            int sub = stones - topdown(piles, index + x, Math.max(x, m), memo);

            ans = Math.max(ans, sub);
        }

        return memo[index][m] = ans;
    }
}