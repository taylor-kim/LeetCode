class Solution {
    public int repeatedNTimes(int[] nums) {
        return bf(nums);
    }

    public int bf(int[] nums) {
        Set<Integer> set = new HashSet();

        for (int num : nums) {
            if (!set.add(num)) return num;
        }

        return -1;
    }

    public int mySol_fail(int[] nums) {
        int ans = -1;
        int count = 1;

        for (int num : nums) {
            if (ans == num) {
                count++;
            } else if (--count == 0) {
                ans = num;
                count = 1;
            }
        }

        return ans;
    }
}