class Solution {
    public int minMoves2(int[] nums) {
        return myOldSol(nums);
    }

    public int myOldSol(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int mid = n / 2;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans += Math.abs(nums[i] - nums[mid]);
        }

        return ans;
    }

    public int mySol2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        long[] lToR = new long[n];
        long[] rToL = new long[n];

        for (int i = 1; i < n; i++) {
            lToR[i] = lToR[i - 1];
            if (nums[i - 1] != nums[i]) {
                lToR[i] += (nums[i] - nums[i - 1]) * i;
            }

            int j = n - i - 1;

            rToL[j] = rToL[j + 1];
            if (nums[j] != nums[j + 1]) {
                rToL[j] += (nums[j + 1] - nums[j]) * (n - j - 1);
            }
        }

        long ans = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, lToR[i] + rToL[i]);
        }

        return (int)ans;
    }

    public int mySol_fail(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int max = nums[n - 1];
        int min = nums[0];

        Map<Integer, Integer> counter = new HashMap();

        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        int major = nums[0];
        int count = counter.get(major);

        for (int num : counter.keySet()) {
            if (counter.get(num) > count) {
                major = num;
                count = counter.get(num);
            }
        }

        int ans = 0;

        for (int num : nums) {
            ans += Math.abs(num - major);
        }

        return ans;
    }
}