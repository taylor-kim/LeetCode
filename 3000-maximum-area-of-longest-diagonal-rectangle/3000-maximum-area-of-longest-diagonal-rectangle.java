class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        return mySol(dimensions);
    }

    public int mySol(int[][] dimensions) {
        int ans = 0;
        double maxDig = 0.0;

        for (int[] dimension : dimensions) {
            double dig = Math.sqrt(dimension[0] * dimension[0] + dimension[1] * dimension[1]);

            if (dig > maxDig) {
                maxDig = dig;
                ans = dimension[0] * dimension[1];
            } else if (dig == maxDig) {
                ans = Math.max(ans, dimension[0] * dimension[1]);
            }
        }

        return ans;
    }
}