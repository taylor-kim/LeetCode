class Solution {
    public int maxFrequencyElements(int[] nums) {
        return study_one_pass(nums);
    }

    public int study_one_pass(int[] nums) {
        int[] freq = new int[101];

        int max = 0;
        int totalCount = 0;

        for (int num : nums) {
            freq[num]++;

            if (max < freq[num]) {
                totalCount = max = freq[num];
            } else if (max == freq[num]) {
                totalCount += max;
            }
        }

        return totalCount;
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