class Solution {
    public int[] rearrangeArray(int[] nums) {
        return mySimple(nums);
    }
    
    public int[] mySimple(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        
        int index = 0;
        int pos = 0;
        int neg = 0;
        
        while (index < n) {
            while (nums[pos] < 0) pos++;
            
            while (nums[neg] > 0) neg++;
            
            ret[index++] = nums[pos++];
            ret[index++] = nums[neg++];
        }
        
        return ret;
    }
}