class Solution {
    public int maxFrequencyElements(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int[] freq = new int[101];

        for (int num : nums) freq[num]++;

        int max = 0;
        int totalCount = 0;

        for (int count : freq) {
            if (max < count) {
                totalCount = max = count;
            } else if (max == count) {
                totalCount += count;
            }
        }

        return totalCount;
    }
}