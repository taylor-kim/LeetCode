class Solution {
    public int minimumOperations(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] freq = new int[max - min + 1];

        for (int num : nums) freq[num - min]++;

        int ans = 0;
        int index = 0;

        while (!allOccurencesAreOne(freq)) {
            for (int i = index; i < Math.min(index + 3, nums.length); i++) {
                freq[nums[i] - min]--;
            }
            ans++;
            index = Math.min(index + 3, nums.length);
        }

        return ans;
    }

    private boolean allOccurencesAreOne(int[] freq) {
        for (int f : freq) {
            if (f > 1) return false;
        }

        return true;
    }
}