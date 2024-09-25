class Solution {
    public int findKthNumber(int n, int k) {
        return official_math(n, k);
    }

    public int official_math(int n, int k) {
        int cur = 1;
        k--;
        
        while (k > 0) {
            int steps = countSteps(n, cur, cur + 1);

            if (steps <= k) {
                cur++;
                k -= steps;
            } else {
                cur *= 10;
                k--;
            }
        }

        return cur;
    }

    private int countSteps(int n, long prefix1, long prefix2) {
        int steps = 0;

        while (prefix1 <= n) {
            steps += Math.min(n + 1, prefix2) - prefix1;
            prefix1 *= 10;
            prefix2 *= 10;            
        }

        return steps;
    }

    public int mySol2_fail(int n, int k) {
        int[] kk = new int[] {k};
        int[] ans = {0};

        for (int num = 1; num < 10; num++) {
            if (ans[0] != 0) break;

            mySol2(n, kk, num, ans);
        }

        return ans[0];
    }

    public void mySol2(int n, int[] k, int num, int[] ans) {
        if (n < num) return;

        k[0]--;

        if (k[0] == 0) {
            ans[0] = num;
            return;
        }

        int next = num * 10;

        if (next > n) return;

        for (int i = 0; i < 10; i++) {
            mySol2(n, k, next + i, ans);
        }
    }

    public int mySol_fail(int n, int k) {
        int p = 10;
        int number = 0;

        for (int i = 1; i < 10 && k > 0; i++) {
            number = i;
            k--;

            System.out.println(String.format("number:%d, k:%d", number, k));

            while (k > 0 && number <= n) {
                number *= 10;

                for (int j = 0; j < 10 && number <= n; j++) {
                    k--;

                    if (k == 0) return number;

                    number++;
                }
            }
        }

        return number;
    }
}