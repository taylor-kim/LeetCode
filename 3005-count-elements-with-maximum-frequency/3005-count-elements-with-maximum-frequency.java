class Solution {
    public int maxFrequencyElements(int[] nums) {
        return mySol(nums);
    }
    
    public int mySol(int[] nums) {
        Map<Integer, Integer> freq = new HashMap();
        
        int maxFreq = 0;
        
        for (int num : nums) {
            freq.computeIfAbsent(num, k -> 0);
            
            int nextFreq = freq.get(num) + 1;
            
            freq.put(num, nextFreq);
            
            maxFreq = Math.max(maxFreq, nextFreq);
        }
        
        int ans = 0;
        
        for (int key : freq.keySet()) {
            if (freq.get(key) == maxFreq) {
                ans += maxFreq;
            }
        }
        
        return ans;
    }
}