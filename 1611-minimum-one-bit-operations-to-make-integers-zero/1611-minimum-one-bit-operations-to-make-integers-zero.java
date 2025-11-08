class Solution {
    public int minimumOneBitOperations(int n) {
        return mySol_read_editorial(n);
    }

    public int mySol_read_editorial(int n) {
        if (n == 0) return 0;

        for (int i = 31; i >= 0; i--) {
            int bit = 1 << i;

            if ((n & bit) != 0) {
                return (int)Math.pow(2, i + 1) - 1 - (mySol_read_editorial(n & (~bit)));
            }
        }

        return -1;
    }

    public int mySol_fail(int n) {
        // 000

        // 001

        // 011
        // 010

        // 110
        // 111
        // 101
        // 100

        //1100
        //1101
        //1111
        //1110
        //1010
        //1011
        //1001
        //1000

        return 15;
    }

    public int mySol2_fail(int n) {
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            int bit = 1 << i;

            if ((n & bit) != 0) {
                ans += f(i);
            }
        }
        
        return ans;
    }

    public int f(int k) {
        if (k == 0) return 1;

        return 2 * f(k - 1) + 1;
    }
}