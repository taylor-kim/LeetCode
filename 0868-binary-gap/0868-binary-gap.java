class Solution {
    public int binaryGap(int n) {
        return mySol2(n);
    }

    public int mySol2(int n) {
        int ans = 0;

        int left = 31 - Integer.numberOfLeadingZeros(n);

        for (int right = left - 1; right >= 0; right--) {
            while (right >= 0 && (n & (1 << right)) == 0) {
                right--;
            }

            if (right >= 0 && (n & (1 << right)) != 0) {
                ans = Math.max(ans, left - right);
                left = right;
            }
        }

        return ans;
    }

    public int mySol(int n) {
        int ans = 0;

        while (n > 0) {
            while (n > 0 && (n & 1) == 0) {
                n /= 2;
            }

            n /= 2;

            int count = 0;

            while (n > 0 && (n & 1) == 0) {
                n /= 2;
                count++;
            }

            if ((n & 1) == 1) {
                ans = Math.max(ans, count + 1);
            }
        }

        return ans;
    }
}