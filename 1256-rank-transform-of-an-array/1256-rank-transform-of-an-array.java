class Solution {
    public int[] arrayRankTransform(int[] arr) {
        return mySol_20260712(arr);
    }

    public int[] mySol_20260712(int[] arr) {
        int n = arr.length;
        Integer[][] sorted = new Integer[n][2];

        for (int i = 0; i < n; i++) {
            sorted[i][0] = arr[i];
            sorted[i][1] = i;
        }

        Arrays.sort(sorted, (a, b) -> {
            return a[0] - b[0];
        });

        int rank = 0;
        int prev = Integer.MIN_VALUE;

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            if (prev == sorted[i][0]) {
                ans[sorted[i][1]] = rank;
            } else {
                prev = sorted[i][0];
                ans[sorted[i][1]] = ++rank;
            }
        }

        return ans;
    }
}