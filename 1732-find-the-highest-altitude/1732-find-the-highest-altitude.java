class Solution {
    public int largestAltitude(int[] gain) {
        return mySol(gain);
    }

    public int mySol(int[] gain) {
        int max = 0;
        int altitude = 0;

        for (int g : gain) {
            altitude += g;
            max = Math.max(max, altitude);
        }

        return max;
    }
}