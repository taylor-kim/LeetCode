class Solution {
    public int maxScore(String s) {
        return mySol_spaceopt(s);
    }

    public int mySol_spaceopt(String s) {
        int n = s.length();
        int[] zeros = new int[n];

        for (int i = 0; i < n; i++) {
            zeros[i] = s.charAt(i) == '0' ? 1 : 0;

            if (i > 0) {
                zeros[i] += zeros[i - 1];
            }
        }

        int ans = 0;
        int ones = 0;

        for (int i = n - 1; i >= 1; i--) {
            if (s.charAt(i) == '1') ones++;

            ans = Math.max(ans, zeros[i - 1] + ones);
        }

        return ans;
    }

    public int mySol(String s) {
        int n = s.length();
        int[] zeros = new int[n];
        int[] ones = new int[n];

        for (int i = 0; i < n; i++) {
            zeros[i] = s.charAt(i) == '0' ? 1 : 0;

            ones[n - i - 1] = s.charAt(n - i - 1) == '1' ? 1 : 0;

            if (i > 0) {
                zeros[i] += zeros[i - 1];
            }

            if (n - i < n) {
                ones[n - i - 1] += ones[n - i];
            }
        }

        int ans = 0;

        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, zeros[i - 1] + ones[i]);
        }

        return ans;
    }
}