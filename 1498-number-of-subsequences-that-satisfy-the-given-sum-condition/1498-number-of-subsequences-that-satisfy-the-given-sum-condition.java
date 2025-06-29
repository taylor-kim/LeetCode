class Solution {
    public int numSubseq(int[] nums, int target) {
        return others(nums, target);
    }

    public int others(int[] nums, int target) {
        int n = nums.length;

        Arrays.sort(nums);

        int mod = (int)1e9 + 7;
        int[] powers = new int[n + 1];
        powers[0] = 1;

        for (int i = 1; i <= n; i++) {
            powers[i] = (powers[i - 1] * 2) % mod;
        }

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int index = findPossible(nums, i, target);

            if (index == -1) break;

            ans = (ans + powers[index - i]) % mod;
        }

        return (int)ans;
    }

    private int findPossible(int[] nums, int lo, int target) {
        int min = lo;
        int hi = nums.length - 1;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[min] + nums[mid] <= target) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    public int mySol_fail(int[] nums, int target) {
        int n = nums.length;

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        long ans = 0;
        int mod = (int)1e9 + 7;

        for (int i = 0; i < n; i++) {
            int min = arr[i][0];

            if (min * 2 > target) break;

            int add = 1;

            int max = target - min;
            int right = leftmost(arr, i, max + 1);

            if (right > i) add++;

            int between = right - i - 1;

            // System.out.println(String.format("i:%d, right:%d, between:%d", i, right, between));

            if (between > 0) {
                ans = (ans + (long)Math.pow(2, between)) % mod;
            }

            ans = (ans + add) % mod;
        }

        return (int)ans;
    }

    private int leftmost(int[][] arr, int lo, int target) {
        int hi = arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid][0] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo - 1;
    }
}