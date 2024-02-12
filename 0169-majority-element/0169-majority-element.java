class Solution {
    public int majorityElement(int[] nums) {
        return mySol(nums);
    }
    
    public int mySol(int[] nums) {
        int count = 0;
        int major = 0;
        
        for (int num : nums) {
            if (count == 0) {
                major = num;
                count = 1;
            } else {
                count += major == num ? 1 : -1;
            }
        }
        
        return major;
    }
}