class Solution {
    public boolean canPartition(int[] nums) {
        return niceDP(nums);
    }

    public boolean niceDP(int[] nums) {
        int sum = 0;
        int n = nums.length;
        
        for(int i : nums) sum+=i;
        
        if(sum%2!=0) return false;
        
        sum /= 2;
        
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int i = sum; i > 0; i--) {
                if (i >= num) {
                    dp[i] = dp[i] || dp[i - num];
                }
            }
        }
        
        return dp[sum];
    }

    Boolean mem[][];
    public boolean othersMemoization(int[] nums) {
        int sum = 0;
        int n = nums.length;
        
        for(int i : nums) sum+=i;
        
        if(sum%2!=0) return false;
        
        sum /= 2;
        
        mem = new Boolean[n+1][sum+1];
        
        return subsetSum(nums,0,sum);
    }
    
    boolean subsetSum(int[] nums, int pos, int sum){
        if(sum==0) return true;
        
        else if(pos>=nums.length || sum<0) return false;
        
        if(mem[pos][sum]!=null) return mem[pos][sum];
        
        return mem[pos][sum] = subsetSum(nums,pos+1,sum-nums[pos]) ||
                                subsetSum(nums,pos+1,sum);
        
        
    }

    public boolean mySol(int[] nums) {
        int total = 0;

        for (int num : nums) {
            total += num;
        }

        if (total % 2 == 1) {
            return false;
        }

        total /= 2;

        int[][] memo = new int[nums.length][total + 1];

        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        return mySol(nums, total, 0, memo);
    }

    public boolean mySol(int[] nums, int total, int index, int[][] memo) {
        if (total == 0) {
            return true;
        }

        if (index >= nums.length || total < 0) {
            return false;
        }

        if (memo[index][total] != -1) {
            return memo[index][total] == 1;
        }

        memo[index][total] = (mySol(nums, total - nums[index], index + 1, memo) 
                                || mySol(nums, total, index + 1, memo)) ? 1 : 0;

        return memo[index][total] == 1;
    }
}