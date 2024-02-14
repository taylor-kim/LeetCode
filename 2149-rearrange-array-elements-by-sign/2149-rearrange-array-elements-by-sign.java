class Solution {
    public int[] rearrangeArray(int[] nums) {
        return official(nums);
    }
    
    public int[] try_spaceopt(int[] nums) {
        return nums;
    }
    
    public int[] official(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        
        int pos = 0;
        int neg = 1;
        
        for (int num : nums) {
            if (num > 0) {
                ret[pos] = num;
                pos += 2;
            } else {
                ret[neg] = num;
                neg += 2;
            }
        }
        
        return ret;
    }
    
    public int[] mySimple2(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        
        int index = 0;
        int pos = 0;
        int neg = 0;
        
        while (index < n) {
            while (nums[pos] < 0) pos++;
            
            ret[index] = nums[pos++];
            
            index += 2;
        }
        
        index = 1;
        
        while (index < n) {
            while (nums[neg] > 0) neg++;
            
            ret[index] = nums[neg++];
            
            index += 2;
        }
        
        return ret;
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