class Solution {
    public int concatenatedBinary(int n) {
        return helped_by_gpt(n);
    }

    public int helped_by_gpt(int n) {
        int ans = 0;
        int mod = (int)1e9 + 7;

        for (int bin = 1; bin <= n; bin++) {
            String s = Integer.toBinaryString(bin);

            for (int i = 0; i < s.length(); i++) {
                int bit = s.charAt(i) - '0';
                ans = (ans * 2 + bit) % mod;
            }
        }

        return ans;
    }

    public int mySol_giveup(int n) {
        long concated = 0;
        int size = 0;
        int mod = (int)1e9 + 7;
        int maxLength = getBinaryLength(mod);

        long ans = 0;

        for (long bin = n; bin >= 1; bin--) {
            int length = getBinaryLength(bin);

            long shifted = (((bin << size) % mod) + mod) % mod;
            // long shifted = bin << size;

            ans = (ans + shifted) % mod;

            size += length;
            // size = getBinaryLength(ans);

            System.out.println(size);
        }

        return (int)ans;
    }

    private int getBinaryLength(long n) {
        for (int i = 31; i >= 0; i--) {
            int bit = 1 << i;

            if ((n & bit) != 0) {
                return i + 1;
            }
        }

        return 0;
    }
}