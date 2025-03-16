class Solution {
    public int maximumCandies(int[] candies, long k) {
        return official_bs(candies, k);
    }

    public int official_bs(int[] candies, long k) {
        int n = candies.length;

        int max = Arrays.stream(candies).max().getAsInt();

        int lo = 1;
        int hi = max + 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            long given = 0;

            for (int i = 0; i < candies.length && given < k; i++) {
                given += candies[i] / mid;
            }

            if (given >= k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo - 1;
    }

    public int mySol(int[] candies, long k) {
        int n = candies.length;

        Arrays.sort(candies);

        long sum = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            int candy = candies[i];
            sum += candy;
            max = Math.max(max, candy);
        }

        // if (sum < k) return 0;

        int lo = 1;
        int hi = max + 1;

        print(String.format("sum:%d, k:%d", sum, k));

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            print(String.format("lo:%d, hi:%d, mid:%d", lo, hi, mid));

            if (canGive(candies, k, mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }

            print("\n");
        }

        return lo - 1;
    }

    private boolean canGive(int[] candies, long k, int amount) {
        int n = candies.length;

        int index = leftmost(candies, amount);

        if (index < 0) {
            index = -(index + 1);
        }

        for (int i = index; i < n && k > 0; i++) {
            k -= candies[i] / amount;
        }

        return k <= 0;
    }

    private int leftmost(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (target <= arr[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        if (lo == arr.length || (lo == 0 && arr[lo] != target)) {
            return -(lo + 1);
        }

        return lo;
    }

    private void print(String s) {
        // System.out.println(s);
    }
}