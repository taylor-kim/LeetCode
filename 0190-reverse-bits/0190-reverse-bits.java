class Solution {
    public int reverseBits(int n) {
        return mySol(n);
    }

    public int mySol(int n) {
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            int bit = 1 << i;

            if ((n & bit) != 0) {
                int j = 1 << (31 - i);
                ans |= j;
            }
        }

        return ans;
    }
}