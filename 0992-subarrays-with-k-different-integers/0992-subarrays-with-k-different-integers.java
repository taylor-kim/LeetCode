class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return official_sw_with_complement_set(nums, k);
    }

    public int official_sw_with_complement_set(int[] nums, int k) {
        return official_sw_at_most(nums, k) - official_sw_at_most(nums, k - 1);
    }

    public int official_sw_at_most(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();

        int ans = 0;

        int left = 0;
        int n = nums.length;

        for (int right = 0; right < n; right++) {
            int rv = nums[right];
            map.put(rv, map.getOrDefault(rv, 0) + 1);

            while (map.size() > k) {
                int lv = nums[left++];
                map.put(lv, map.get(lv) - 1);

                if (map.get(lv) == 0) {
                    map.remove(lv);
                }
            }

            ans += right - left + 1;
        }

        return ans;
    }

    public int mySol_fail(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> counter = new HashMap();

        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            int rv = nums[right];
            counter.put(rv, counter.getOrDefault(rv, 0) + 1);

            while (counter.size() == k) {
                int lv = nums[left++];
                counter.put(lv, counter.get(lv) - 1);

                if (counter.get(lv) == 0) {
                    counter.remove(lv);
                }
            }

            ans += left;
        }

        return ans;
    }
}