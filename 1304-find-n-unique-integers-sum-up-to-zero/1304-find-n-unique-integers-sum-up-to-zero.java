class Solution {
    public int[] sumZero(int n) {
        return others_math(n);
    }

    public int[] others_math(int n) {
        int[] ans = new int[n];

        for (int i = 1; i < n; i++) {
            ans[i] = i;
        }

        // ans[0] = -((n - 1) * (1 + n - 1) / 2);
        ans[0] = (n - 1) * -n >> 1;

        return ans;
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