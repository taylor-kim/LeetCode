class Solution {
    public long maximumTripletValue(int[] nums) {
        return mySol2(nums);
    }

    public long mySol2(int[] nums) {
        int n = nums.length;
        long max = 0;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    max = Math.max(max, ((long)nums[i] - nums[j]) * nums[k]);
                }
            }
        }

        return max;
    }

    public long mySol_fail(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        int lo = 0;
        int hi = n;
        int ans = 0;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            int found = findTriple(arr, arr[mid]);

            if (found >= 0) {
                ans = found;
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return ans;
    }

    private int findTriple(int[][] arr, int[] data) {
        int n = arr.length;

        for (int j = 0, k = n - 1; j < n && k >= 0; j++, k--) {
            if (data[1] < arr[j][1] && arr[j][1] < arr[k][1]) {
                return (data[0] - arr[j][0]) * arr[k][0];
            }
        }

        return -1;
    }
}