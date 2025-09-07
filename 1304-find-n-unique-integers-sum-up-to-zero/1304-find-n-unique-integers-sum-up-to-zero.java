class Solution {
    public int[] sumZero(int n) {
        return mySol(n);
    }

    public int[] mySol(int n) {
        int[] ans = new int[n];

        int index = 0;
        int num = 1;

        if (n % 2 == 1) {
            ans[index++] = 0;
        }

        int sign = 1;

        while (index < n) {
            ans[index++] = num * sign;
            sign = -sign;

            if (sign > 0) num++;
        }

        return ans;
    }
}