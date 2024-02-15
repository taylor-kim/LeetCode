class Solution {
    public long largestPerimeter(int[] nums) {
        return others_fast_terminate(nums);
    }
    
    public long others_fast_terminate(int[] nums) {
        Arrays.sort(nums);
        
        int n = nums.length;
        
        long ans = -1;
        long sum = 0;
        
        for (int num : nums) sum += num;
        
        for (int i = n - 1; i >= 0; i--) {
            sum -= nums[i];
            if (sum > nums[i]) {
                return sum + nums[i];
            }
        }
        
        return ans;
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