class Solution {
    public int largestInteger(int[] nums, int k) {
        return mySol2_fail_and_see_editorial(nums, k);
    }

    public int mySol_bf(int[] nums, int k) {
        int ans = -1;
        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length - k + 1; i++) {
            Set<Integer> set = new HashSet();
            for (int j = i; j < i + k; j++) {
                set.add(nums[j]);
            }

            for (int num : set) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        for (int key : map.keySet()) {
            if (map.get(key) == 1) {
                ans = Math.max(ans, key);
            }
        }

        return ans;
    }

    public int mySol3_fail(int[] nums, int k) {
        int n = nums.length;

        Set<Integer> removed = new HashSet();
        Set<Integer> window = new HashSet();
        Set<Integer> candidates = new HashSet();

        int left = 0;

        for (int right = 0; right < n; right++) {
            int num = nums[right];

            window.add(num);

            if (removed.contains(num)) {
                candidates.remove(num);
            } else {
                candidates.add(num);
            }

            if (right - left + 1 == k + 1) {
                removed.add(nums[left++]);
            }
        }

        return 0;
    }

    public int mySol2_fail_and_see_editorial(int[] nums, int k) {
        int n = nums.length;
        int ans = -1;
        int left = nums[0];
        int right = nums[n - 1];

        Map<Integer, Integer> map = new HashMap();
        int max = -1;

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            max = Math.max(max, nums[i]);
        }

        if (n == k) {
            return max;
        }

        max = -1;

        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == 1) {
                max = Math.max(max, nums[i]);
            }
        }

        if (k == 1) {
            return max;
        }

        if (map.get(left) > 1) {
            left = -1;
        }

        if (map.get(right) > 1) {
            right = -1;
        }

        return Math.max(left, right);
    }

    public int mySol_fail(int[] nums, int k) {
        int n = nums.length;

        int left = 0;
        int ans = -1;
        int max = 0;

        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        List<Integer> indices = new ArrayList();

        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == 1) {
                indices.add(i);
                max = Math.max(max, nums[i]);
            }
        }

        if (k == 1) {
            return max;
        }

        for (int index : indices) {
            return Math.max(nums[0], nums[n - 1]);
        }

        return ans;
    }
}