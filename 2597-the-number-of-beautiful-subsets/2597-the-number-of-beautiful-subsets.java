class Solution {
    public int beautifulSubsets(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        Arrays.sort(nums);

        return mySol(nums, k, 0, new HashMap());
    }

    public int mySol(int[] nums, int k, int index, Map<Integer, Integer> map) {
        if (index >= nums.length) return map.size() == 0 ? 0 : 1;

        int include = 0;

        if (!map.containsKey(nums[index] - k)) {
            int count = map.getOrDefault(nums[index], 0);
            map.put(nums[index], count + 1);

            include = mySol(nums, k, index + 1, map);

            if (count == 0) {
                map.remove(nums[index]);
            } else {
                map.put(nums[index], count);
            }
        }

        int exclude = mySol(nums, k, index + 1, map);

        return include + exclude;
    }
}