class Solution {
    public int missingNumber(int[] nums) {
        return mySol_in_the_past(nums);
    }
    
    public int mySol_in_the_past(int[] nums) {
        int ans = 0;
        
        for (int i = 0; i < nums.length; i++) {
            ans += i + 1 - nums[i];
        }
        
        return ans;
    }
    
    public int mySol_fail(int[] nums) {
        int n = nums.length;
        
        boolean found0 = false;
        
        for (int i = 0; i < n; i++) {
            int valueAsIndex = Math.abs(nums[i]);
            
            if (valueAsIndex == 0) {
                found0 = true;
                continue;
            }
            
            if (valueAsIndex <= n) {
                nums[valueAsIndex - 1] = -nums[valueAsIndex - 1];
            }
        }
        
        if (!found0) {
            return 0;
        }
        
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        
        return -1;
    }
}