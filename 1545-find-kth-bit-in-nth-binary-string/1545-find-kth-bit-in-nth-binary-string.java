class Solution {
    public char findKthBit(int n, int k) {
        return official_bit(n, k);
    }

    public char official_bit(int n, int k) {
        // Find the position of the rightmost set bit in k
        // This helps determine which "section" of the string we're in
        int positionInSection = k & -k;

        // Determine if k is in the inverted part of the string
        // This checks if the bit to the left of the rightmost set bit is 1
        boolean isInInvertedPart = (((k / positionInSection) >> 1) & 1) == 1;

        // Determine if the original bit (before any inversion) would be 1
        // This is true if k is even (i.e., its least significant bit is 0)
        boolean originalBitIsOne = (k & 1) == 0;

        if (isInInvertedPart) {
            // If we're in the inverted part, we need to flip the bit
            return originalBitIsOne ? '0' : '1';
        } else {
            // If we're not in the inverted part, return the original bit
            return originalBitIsOne ? '1' : '0';
        }
    }

    public char official_iter(int n, int k) {
        int inverting = 0;
        int len = (1 << n) - 1;

        while (k > 1) {
            if (k == len / 2 + 1) {
                return inverting % 2 == 0 ? '1' : '0';
            } else if (k > len / 2 + 1) {
                k = len - k + 1;
                inverting++;
            }

            len /= 2;
        }

        // System.out.println(String.format("k:%d, inverting:%d", k, inverting));

        return inverting % 2 == 0 ? '0' : '1';
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