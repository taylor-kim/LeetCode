class Solution {
    public int minBitFlips(int start, int goal) {
        return mySol(start, goal);
    }

    public int mySol(int start, int goal) {
        int xor = start ^ goal;

        return Integer.bitCount(xor);
    }
}