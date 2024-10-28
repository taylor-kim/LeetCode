class Solution {
    public int longestSquareStreak(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        Arrays.sort(nums);

        int ans = 0;

        int min = nums[0];
        int max = nums[nums.length - 1];

        int[] bucket = new int[max - min + 1];

        for (int num : nums) bucket[num - min]++;

        int base = min;

        while (base <= max) {
            int length = 0;
            int num = base;

            while (min <= num && num <= max && bucket[num - min] > 0) {
                length++;
                bucket[num - min] = 0;
                
                num = num * num;
            }

            ans = Math.max(ans, length);
            base++;
        }

        return ans > 1 ? ans : -1;
    }
}