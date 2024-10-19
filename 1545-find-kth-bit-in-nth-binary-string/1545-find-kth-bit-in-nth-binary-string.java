class Solution {
    public char findKthBit(int n, int k) {
        return official_recur(n, k);
    }

    public char official_recur(int n, int k) {
        if (n == 1) return '0';

        int len = (1 << n) - 1;

        if (k == len / 2 + 1) {
            return '1';
        } else if (k < len / 2 + 1) {
            return official_recur(n - 1, k);
        } else {
            char c = official_recur(n - 1, len - k + 1);

            return c == '0' ? '1' : '0';
        }
    }

    public char mySol_bf(int n, int k) {
        // s1 = 0, s1'=1
        // s2 = s1,1,s1' = 011, s2' = s1'0 s1 = s1,0,s1' = 001
        // s3 = 011,1,001, 011,0,001
        // s4 = 011100110110001

        // sn = sx,1,sx',
        // sn' = sx,0,sx'

        String s = "0";
        String sp = "1";

        while (s.length() < k) {
            String next = s + "1" + sp;
            String nextP = s + "0" + sp;

            s = next;
            sp = nextP;
        }

        return s.charAt(k - 1);
    }
}