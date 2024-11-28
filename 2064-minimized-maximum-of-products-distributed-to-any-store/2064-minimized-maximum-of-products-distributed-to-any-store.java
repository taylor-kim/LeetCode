class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        return try_bs(n, quantities);
    }

    public int try_bs(int n, int[] q) {
        int lo = 0;
        int hi = 0;

        for (int count : q) {
            hi = Math.max(hi, count);
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (check(n, mid, q)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private boolean check(int n, int x, int[] q) {
        int index = 0;
        int remain = q[index];

        for (int i = 0; i < n; i++) {
            remain -= x;

            if (remain <= 0) {
                index++;

                if (index == q.length) return true;

                remain = q[index];
            }
        }

        return false;
    }

    public int try_bf(int n, int[] q) {
        return topdown(n, q, 0, 0, new Integer[n][q.length]);
    }

    public int topdown(int n, int[] q, int i, int j, Integer[][] memo) {
        if (j >= q.length) return 0;

        if (i >= n) return -1;

        // for (int i = 0;)
        return 0;
    }

    public int mySol_fail(int n, int[] q) {
        Arrays.sort(q);

        int sum = 0;

        int min = (int)1e6;

        Map<Integer, Integer> counter = new HashMap();

        for (int num : q) {
            sum += num;
            min = Math.min(min, num);

            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        int base = sum / n;
        int odd = sum % n;
        
        int ans = base + (odd == 0 ? 0 : 1);

        System.out.println(Arrays.toString(q));

        System.out.println(String.format("sum:%d, base:%d, odd:%d, ans:%d, min:%d", sum, base, odd, ans, min));

        if (ans > min) {
            int count = counter.get(min);
            return ans + (ans - min) / count;
        }

        return ans;
    }
}