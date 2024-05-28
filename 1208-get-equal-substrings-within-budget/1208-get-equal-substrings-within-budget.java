class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        return mySol(s, t, maxCost);
    }

    public int mySol(String s, String t, int maxCost) {
        int n = s.length();
        int[] dp = new int[n + 1];

        int cost = 0;
        int left = 0;

        for (int i = 0; i < n; i++) {
            cost += Math.abs(s.charAt(i) - t.charAt(i));

            while (left <= i && cost > maxCost) {
                cost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            dp[i + 1] = Math.max(dp[i], i - left + 1);
        }

        return dp[n];
    }
}