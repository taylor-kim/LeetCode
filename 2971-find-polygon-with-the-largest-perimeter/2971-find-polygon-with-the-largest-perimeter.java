class Solution {
    public long largestPerimeter(int[] nums) {
        return mySol(nums);
    }
    
    public long mySol(int[] nums) {
        Arrays.sort(nums);
        
        int n = nums.length;
        
        long ans = -1;
        long sum = 0;
        
        for (int i = 0; i < n; i++) {
            if (sum > nums[i]) {
                ans = sum + nums[i];
            }
            sum += nums[i];
        }
        
        return ans;
    }
}