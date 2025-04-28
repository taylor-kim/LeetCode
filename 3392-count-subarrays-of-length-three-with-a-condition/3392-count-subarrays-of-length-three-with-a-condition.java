class Solution {
    public int countSubarrays(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int ans = 0;

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] % 2 == 1) continue;

            if (nums[i - 1] + nums[i + 1] == nums[i] / 2) {
                ans++;
            }
        }

        return ans;
    }

    public int mySol_fail(int[] nums) {
        int ans = 0;

        Map<Integer, Integer> map = new HashMap();
        Map<Integer, Integer> remains = new HashMap();

        for (int num : nums) {
            add(remains, num);
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            remove(remains, num);

            if (num % 2 == 0) {
                int left = map.getOrDefault(num / 4, 0);
                int right = remains.getOrDefault(num / 4, 0);

                ans += left * right;

                // System.out.println(String.format("num:%d, left:%d, right:%d, ans:%d", num, left, right, ans));
            }

            add(map, num);
        }
        
        return ans;
    }

    private void add(Map<Integer, Integer> map, int num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    private void remove(Map<Integer, Integer> map, int num) {
        map.put(num, map.get(num) - 1);

        if (map.get(num) == 0) {
            map.remove(num);
        }
    }
}