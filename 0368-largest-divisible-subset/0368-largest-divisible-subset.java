class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        return mySol(nums);
    }

    public List<Integer> mySol(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        int[] prev = new int[n];
        int max = 0;
        int index = -1;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
            
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }

            if (dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }

        List<Integer> ans = new ArrayList();

        while (index != -1) {
            ans.add(nums[index]);
            index = prev[index];
        }

        return ans;
    }
}