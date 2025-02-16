class Solution {
    public int[] constructDistancedSequence(int n) {
        return mySol_tle_after_martin(n);
    }

    // [5,3,1,4,3,5,2,4,2]
    // [5,3,4,1,3,5,4,2,2]

    public int[] mySol_tle_after_martin(int n) {
        int[] ans = new int[2 * n - 1];

        topdown(0, ans.length, ans, new boolean[n + 1]);

        return ans;
    }

    private boolean topdown(int i, int remain, int[] ans, boolean[] visit) {
        if (i >= ans.length) return remain == 0;
        if (remain == 0) return true;

        // System.out.println(String.format("i:%d, remain:%d, ans:%s, visit:%s", i, remain, Arrays.toString(ans), Arrays.toString(visit)));

        while (i < ans.length && ans[i] != 0) {
            i++;
        }

        int startNum = Math.max(1, Math.min(ans.length - i - 1, visit.length - 1));

        for (int num = startNum; num > 0; num--) {
            if (visit[num]) continue;

            int use = 0;

            if (num == 1) {
                ans[i] = num;
                use = 1;
            } else if (i + num < ans.length && ans[i + num] == 0) {
                ans[i] = num;
                ans[i + num] = num;
                use = 2;
            } else {
                continue;
            }

            visit[num] = true;

            if (topdown(i + 1, remain - use, ans, visit)) {
                return true;
            }

            ans[i] = 0;

            if (use == 2) {
                ans[i + num] = 0;
            }

            visit[num] = false;
        }

        return false;
    }
}