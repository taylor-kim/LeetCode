class Solution {
    public int longestBalanced(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        int n = s.length();

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int[] counter = new int[26];
            for (int j = i; j < n; j++) {
                int target = ++counter[s.charAt(j) - 'a'];

                boolean ok = true;

                for (int count : counter) {
                    if (count > 0 && count != target) {
                        ok = false;
                        break;
                    }
                }

                if (ok) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }
}