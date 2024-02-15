class Solution {
    public long largestPerimeter(int[] nums) {
        return mySol(nums);
    }
    
    public long mySol(int[] nums) {
        Arrays.sort(nums);
        
        int n = nums.length;
        
        long[] pSum = new long[n + 1];
        
        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + nums[i];
        }
        
        long ans = -1;
        
        for (int i = 2; i < n; i++) {
            long others = pSum[i];
            
            if (others > nums[i]) {
                ans = others + nums[i];
            }
        }
        
        return ans;
    }
}