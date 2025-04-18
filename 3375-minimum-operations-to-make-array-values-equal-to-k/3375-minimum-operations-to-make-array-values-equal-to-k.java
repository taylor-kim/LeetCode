class Solution {
    public int minOperations(int[] nums, int k) {
        return try_20250409(nums, k);
    }

    public int try_20250409(int[] nums, int k) {
        Set<Integer> greater = new HashSet();

        for (int num : nums) {
            if (num < k) return -1;

            if (num > k) greater.add(num);
        }

        return greater.size();
    }

    public int others_linear(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();

        for (int num : nums) {
            if (num < k) {
                return -1;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int ans = map.size();

        if (map.containsKey(k)) {
            ans--;
        }

        return ans;
    }

    public int mySol(int[] nums, int k) {
        Arrays.sort(nums);

        if (nums[0] < k) return -1;

        int count = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            int current = nums[i];
            int next = nums[i + 1];

            if (current != next) {
                count++;
            }
        }

        return count - (nums[0] == k ? 1 : 0);
    }
}