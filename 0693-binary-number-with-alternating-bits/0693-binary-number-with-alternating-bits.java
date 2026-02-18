class Solution {
    public boolean hasAlternatingBits(int n) {
        return mySol(n);
    }

    public boolean mySol(int n) {
        int highestBitPosition = 31 - Integer.numberOfLeadingZeros(n);

        for (int i = 0; i <= highestBitPosition; i++) {
            int pos1 = 1 << i;
            int pos2 = 1 << (i + 1);

            int b1 = n & pos1;
            int b2 = n & pos2;

            if ((b1 >> i) == (b2 >> (i + 1))) return false;
        }

        return true;
    }
}