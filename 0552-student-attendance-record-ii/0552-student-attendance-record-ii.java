class Solution {
    public int checkRecord(int n) {
        return mySol(n);
    }

    public int mySol(int n) {
        Integer[][][] memo = new Integer[n][2][3];
        return mySol(n, 0, 0, 0, new StringBuilder(), memo);
    }

    public int mySol(int n, int index, int a, int l, StringBuilder sb, Integer[][][] memo) {
        // System.out.println(String.format("n:%d, index:%d, a:%d, l:%d", n, index, a, l));
        if (a >= 2) return 0;

        if (l >= 3) return 0;

        if (index >= n) {
            // System.out.println(sb.toString());
            return 1;
        }

        if (memo[index][a][l] != null) {
            return memo[index][a][l];
        }

        int mod = (int)1e9 + 7;

        sb.append('a');
        long ans = mySol(n, index + 1, a + 1, 0, sb, memo) % mod;
        sb.setLength(sb.length() - 1);

        sb.append('l');
        ans += mySol(n, index + 1, a, l + 1, sb, memo) % mod;
        sb.setLength(sb.length() - 1);

        sb.append('p');
        ans += mySol(n, index + 1, a, 0, sb, memo) % mod;
        sb.setLength(sb.length() - 1);

        return memo[index][a][l] = (int)ans;
    }
}