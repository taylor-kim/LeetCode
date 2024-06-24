class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        return by_hint(bloomDay, m, k);
    }

    public int by_hint(int[] bloomDay, int m, int k) {
        int max = Arrays.stream(bloomDay).max().getAsInt();

        System.out.println(max);

        int ans = -1;

        int lo = 1;
        int hi = max;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            int count = getCount(bloomDay, mid, k);

            if (count >= m) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private int getCount(int[] bloomDay, int day, int k) {
        int count = 0;
        int canMake = 0;

        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= day) {
                count++;
            } else {
                canMake += count / k;
                count = 0;
            }
        }

        canMake += count / k;

        // System.out.println(String.format("canMake:%d, day:%d, k:%d", canMake, day, k));

        return canMake;
    }
}