class Solution {
    public int getLargestOutlier(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int totalSum = 0;
        int maxOutlier = Integer.MIN_VALUE;

        for (int num : nums) {
            totalSum += num;
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            int outlier = totalSum - 2 * num;
            
            if (countMap.getOrDefault(outlier, 0) > (outlier == num ? 1 : 0)) {
                maxOutlier = Math.max(maxOutlier, outlier);
            }
        }

        return maxOutlier;
    }
}
