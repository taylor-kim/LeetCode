class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        return mySol(s, t, maxCost);
    }

    public int mySol(String s, String t, int maxCost) {
        int n = s.length();

        int[] dp = new int[n + 1];

        int left = 0;
        int cost = 0;

        for (int right = 0; right < n; right++) {
            cost += Math.abs(s.charAt(right) - t.charAt(right));

            while (left <= right && cost > maxCost) {
                cost -= Math.abs(s.charAt(left) - t.charAt(left++));
            }

            dp[right + 1] = Math.max(dp[right], right - left + 1);
        }

        return dp[n];
    }
}