class Solution {
    public int longestBalanced(int[] nums) {
        return mySol_bf(nums);
    }

    public int mySol_bf(int[] nums) {
        for (int length = nums.length; length > 0; length--) {
            if (check(nums, length)) {
                return length;
            }
        }

        return 0;
    }

    private boolean check(int[] nums, int length) {
        Map<Integer, Integer> even = new HashMap();
        Map<Integer, Integer> odd = new HashMap();

        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];

            add(num % 2 == 0 ? even : odd, num);

            if (even.size() == odd.size() && right - left + 1 == length) {
                return true;
            }

            if (right - left + 1 == length) {
                int remove = nums[left++];
                remove(remove % 2 == 0 ? even : odd, remove);
            }
        }

        return false;
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