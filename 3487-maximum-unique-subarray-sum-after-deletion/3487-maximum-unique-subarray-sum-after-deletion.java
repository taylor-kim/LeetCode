class Solution {
    public int maxSum(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        Set<Integer> set = new HashSet();

        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int num : nums) {
            max = Math.max(max, num);
            if (num <= 0) continue;

            if (set.add(num)) {
                sum += num;
            }
        }

        return set.size() > 0 ? sum : max;
    }
}