class Solution {
    public int findClosest(int x, int y, int z) {
        return mySol(x, y, z);
    }

    public int mySol(int x, int y, int z) {
        int diff = -z;

        int absX = Math.abs(x + diff);
        int absY = Math.abs(y + diff);

        return absX == absY ? 0 : absX < absY ? 1 : 2;
    }
}