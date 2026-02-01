class Solution {
    public int longestSubsequence(int[] nums) {
        return godtin(nums);
    }

    public int godtin(int[] nums) {
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            int bit = 1 << i;

            List<Integer> targets = new ArrayList();

            for (int num : nums) {
                if ((num & bit) != 0) targets.add(num);
            }

            ans = Math.max(ans, getLis(targets));
        }

        return ans;
    }

    // 1,5,3,2,4
    // [1,2,4]

    private int getLis(List<Integer> nums) {
        List<Integer> lis = new ArrayList();

        for (int num : nums) {
            int index = Collections.binarySearch(lis, num);

            if (index < 0) {
                index = -(index + 1);
            }

            if (index == lis.size()) {
                lis.add(num);
            } else {
                lis.set(index, num);
            }
        }

        return lis.size();
    }

    // 5 => 0101
    // 6 => 0110
    // 7 => 0111
    // 8 => 1000
    // 9 => 1001
    //10 => 1010
    //11 => 1011
    //12 => 1100
    //13 => 1101
    //14 => 1110
    //15 => 1111
    //16 =>10000

    public int mySol(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] and = new int[n];

        int ans = 0;
        
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            and[i] = nums[i];
            
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && (and[j] & nums[i]) != 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}