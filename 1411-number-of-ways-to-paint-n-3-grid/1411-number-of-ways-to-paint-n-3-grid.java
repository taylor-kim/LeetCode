class Solution {
    public int numOfWays(int n) {
        return mySol2(n);
    }

    public int mySol2(int n) {
        return topdown2(n, "777");
    }

    public int topdown2(int n, String current) {
        if (n == 0) return 1;

        List<String> nexts = new ArrayList();
        buildNexts(current, new StringBuilder(), nexts);

        long ans = 0;
        int mod = (int)1e9 + 7;

        for (String next : nexts) {
            ans = (ans + topdown2(n - 1, next)) % mod;
        }

        return (int)ans;
    }

    void buildNexts(String row, StringBuilder current, List<String> nexts) {
        if (current.length() == 3) {
            nexts.add(current.toString());
            return;
        }

        for (int color = 0; color < 3; color++) {
            int index = current.length();
            if (row.charAt(index) - '0' != color && (index == 0 || current.charAt(index - 1) - '0' != color)) {
                current.append(color);

                buildNexts(row, current, nexts);

                current.deleteCharAt(index);
            }
        }
    }

    public int mySol_fail(int n) {
        return topdown(n, 0, 0, "777", "");
    }

    public int topdown(int n, int r, int c, String prev, String current) {
        if (r == n) return 1;

        if (c == 3) {
            return topdown(n, r + 1, 0, prev, current);
        }

        long ans = 0;
        int mod = (int)1e9 + 7;

        for (int color = 0; color < 3; color++) {
            
            if ((prev.charAt(c) - '0' != color) && (c == 0 || current.charAt(c - 1) - '0' != color)) {

                ans = (ans + topdown(n, r, c + 1, prev, current + (char)(c + '0'))) % mod;
            }
        }

        return (int)ans;
    }
}