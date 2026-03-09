class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        return mySol2(zero, one, limit);
    }

    public int mySol2(int zero, int one, int limit) {
        int[][] pSum = new int[2][zero + one + 1];

        return topdown2(zero, one, limit, 0, pSum);
    }

    public int topdown2(int zero, int one, int limit, int index, int[][] pSum) {
        // System.out.println("zero:%d, one:%d, index:%d".formatted(zero, one, index));

        if (zero < 0 || one < 0) return 0;

        if (index > limit && 
            (
                pSum[0][index] - pSum[0][Math.max(index - limit - 1, 0)] == 0 ||
                pSum[1][index] - pSum[1][Math.max(index - limit - 1, 0)] == 0
            )
        ) {
            return 0;
        }

        if (zero == 0 && one == 0) return 1;

        int mod = (int)1e9 + 7;

        int a = 0;
        int b = 0;

        pSum[0][index + 1] = pSum[0][index] + 1;
        pSum[1][index + 1] = pSum[1][index];
        a = topdown2(zero - 1, one, limit, index + 1, pSum);
        pSum[0][index + 1] = 0;
        pSum[1][index + 1] = 0;

        pSum[0][index + 1] = pSum[0][index];
        pSum[1][index + 1] = pSum[1][index] + 1;
        b = topdown2(zero, one - 1, limit, index + 1, pSum);
        pSum[0][index + 1] = 0;
        pSum[1][index + 1] = 0;

        return (a + b) % mod;
    }

    public int topdown_fail(int zero, int one, int limit, int index, int[][] pSum) {
        // System.out.println("zero:%d, one:%d, index:%d".formatted(zero, one, index));

        if (zero < 0 || one < 0) return 0;

        if (zero == 0 && one == 0) return 1;

        int mod = (int)1e9 + 7;

        int a = 0;
        int b = 0;

        if (index < limit || pSum[1][index] - pSum[1][Math.max(index - limit, 0)] > 0) {
            pSum[0][index + 1] = pSum[0][index] + 1;
            a = topdown_fail(zero - 1, one, limit, index + 1, pSum);
            pSum[0][index + 1] = pSum[0][index] - 1;
        }

        if (index < limit || pSum[0][index] - pSum[0][Math.max(index - limit, 0)] > 0) {
            pSum[1][index + 1] = pSum[1][index] + 1;
            b = topdown_fail(zero, one - 1, limit, index + 1, pSum);
            pSum[1][index + 1] = pSum[1][index] - 1;
        }

        return (a + b) % mod;
    }

    public int mySol_tle(int zero, int one, int limit) {
        return topdown(zero, one, limit, "");
    }

    public int topdown(int zero, int one, int limit, String s) {
        if (zero < 0 || one < 0) return 0;

        if (zero == 0 && one == 0) {
            return isOk(s, limit) ? 1 : 0;
        }

        int a = topdown(zero - 1, one, limit, s + "0");
        int b = topdown(zero, one - 1, limit, s + "1");

        int mod = (int)1e9 + 7;

        return (a + b) % mod;
    }

    private boolean isOk(String s, int limit) {
        if (s.length() <= limit) return true;

        int left = 0;
        int[] counter = new int[2];

        for (int right = 0; right < s.length(); right++) {
            int digit = s.charAt(right) - '0';

            counter[digit]++;

            if (right - left + 1 > limit) {
                if (counter[0] == 0 || counter[1] == 0) return false;

                int remove = s.charAt(left++) - '0';

                counter[remove]--;
            }
        }

        return true;
    }
}