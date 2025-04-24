class Solution {
    public int countCompleteSubarrays(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        int ans = 0;

        int left = 0;

        Set<Integer> set = new HashSet();

        for (int num : nums) set.add(num);

        Map<Integer, Integer> map = new HashMap();

        for (int right = 0; right < n; right++) {
            int num = nums[right];

            map.put(num, map.getOrDefault(num, 0) + 1);

            while (left <= right && map.size() == set.size()) {
                ans += n - right;

                int removedNum = nums[left++];

                map.put(removedNum, map.get(removedNum) - 1);

                if (map.get(removedNum) == 0) {
                    map.remove(removedNum);
                }
            }
        }

        return ans;
    }
}