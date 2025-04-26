class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        return mySol(nums, minK, maxK);
    }

    public long mySol(int[] nums, int minK, int maxK) {
        int n = nums.length;
        int[] next = new int[n + 1];
        next[n] = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];

            if (num < minK || num > maxK) {
                next[i] = -1;
            } else if (next[i + 1] >= 0) {
                next[i] = next[i + 1];
            } else {
                next[i] = i;
            }
        }

        long ans = 0;

        int left = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        TreeMap<Integer, Integer> map = new TreeMap();
        
        for (int right = 0; right < n; right++) {
            while (left <= right && !map.isEmpty() && (map.firstKey() < minK || map.lastKey() > maxK)) {
                map.put(nums[left], map.get(nums[left]) - 1);

                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }

            int num = nums[right];

            map.put(num, map.getOrDefault(num, 0) + 1);

            while (!map.isEmpty() && map.firstKey() == minK && map.lastKey() == maxK) {
                ans += next[right] - right + 1;
                map.put(nums[left], map.get(nums[left]) - 1);

                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
        }

        return ans;
    }
}