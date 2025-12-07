class Solution {
    public int countOdds(int low, int high) {
        return mySol(low, high);
    }

    public int mySol(int low, int high) {
        int left = (low % 2);
        int right = (high % 2);
        int mid = 0;

        if (left == 0) {
            if (right == 0) {
                mid = (high - low) / 2;
            } else {
                mid = (high - low) / 2;
            }
        } else {
            if (right == 0) {
                mid = (high - low) / 2;
            } else {
                mid = (high - low - 1) / 2;
            }
        }

        return left + mid + right;
    }
}