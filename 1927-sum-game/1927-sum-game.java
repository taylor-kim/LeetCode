class Solution {
    public boolean sumGame(String num) {
        return editorial(num);
    }

    public boolean editorial(String num) {
        int n = num.length();
        int[] left = get(num.substring(0, n / 2));
        int[] right = get(num.substring(n / 2, n));

        int n0 = left[0],
            q0 = left[1];
        int n1 = right[0],
            q1 = right[1];

        return (q0 + q1) % 2 == 1 || n0 - n1 != ((q1 - q0) * 9) / 2;
    }

    private int[] get(String s) {
        int nn = 0,
            qq = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '?') {
                qq++;
            } else {
                nn += ch - '0';
            }
        }
        return new int[] { nn, qq };
    }

    public boolean mySol2_fail(String num) {
        // int lSum = a;
        // int rSum = b;
        // int leftAdj = x;
        // int rightAdj = y;

        // return lSum + ([0-9] * x) != rSum + ([0-9] * y);
        return false;
    }

    public boolean mySol_fail(String num) {
        // length, lSum, rSum, indices(sorted)
        // indices == 0 => return lSum - rSum

        // lIndices, rIndices
        // lSum == rSum
        return false;
    }

    private int topdown(int n, int lSum, int rSum, int left, int right, boolean alice) {
        if (left == 0 && right == 0) {
            return lSum - rSum;
        }

        // if (alice) {
        //     if (lSum == rSum) {
        //         return topdown(n, lSum, rSum, left, right, !alice);
        //     } else if (lSum > rSum && (lSum - rSum) <= (right * 9)) {
        //         int diff = lSum - rSum;

        //         int adj = Math.min(diff, 9);
                
        //         return topdown(n, lSum, rSum + adj, left, right - 1, !alice);

        //     } else {

        //     }
        // } else {
        //     return false;
        // }

        return 0;
    }
}