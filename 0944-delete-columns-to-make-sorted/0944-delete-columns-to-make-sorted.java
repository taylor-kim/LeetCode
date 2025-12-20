class Solution {
    public int minDeletionSize(String[] strs) {
        return mySol(strs);
    }

    public int mySol(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();

        int ans = 0;

        for (int c = 0; c < m; c++) {
            char prev = (char)('a' - 1);
            boolean sorted = true;

            for (int r = 0; r < n; r++) {
                if (prev <= strs[r].charAt(c)) {
                    prev = strs[r].charAt(c);
                } else {
                    sorted = false;
                    break;
                }
            }

            if (!sorted) ans++;
        }

        return ans;
    }
}