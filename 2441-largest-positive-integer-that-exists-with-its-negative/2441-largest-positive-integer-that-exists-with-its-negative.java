class Solution {
    public int findMaxK(int[] nums) {
        return official_bitset(nums);
    }

    public int official_bitset(int[] nums) {
        int ans = -1;
        BitSet seen = new BitSet(2048);

        for (int num : nums) {
            int abs = Math.abs(num);

            if (abs > ans && seen.get(-num + 1024)) {
                ans = abs;
            }

            seen.set(num + 1024);
        }

        return ans;
    }

    public int official_two_pointers(int[] nums) {
        Arrays.sort(nums);
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            if (-nums[lo] == nums[hi]) {
                return nums[hi];
            } else if (-nums[lo] > nums[hi]) {
                lo++;
            } else {
                hi--;
            }
        }

        return -1;
    }

    public int mySol_set(int[] nums) {
        Set<Integer> set = new HashSet();

        int ans = -1;

        for (int num : nums) {
            int abs = Math.abs(num);

            if (abs > ans && set.contains(-num)) {
                ans = Math.max(ans, abs);
            }
            set.add(num);
        }

        return ans;
    }
}