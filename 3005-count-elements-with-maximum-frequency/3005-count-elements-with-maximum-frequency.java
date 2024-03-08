class Solution {
    public int maxFrequencyElements(int[] nums) {
        return official_one_pass(nums);
    }
    
    public int official_one_pass(int[] nums) {
        Map<Integer, Integer> freq = new HashMap();
        
        int maxFreq = 0;
        int ans = 0;
        
        for (int num : nums) {
            freq.computeIfAbsent(num, k -> 0);
            
            int nextFreq = freq.get(num) + 1;
            
            freq.put(num, nextFreq);
            
            if (maxFreq < nextFreq) {
                maxFreq = nextFreq;
                ans = maxFreq;
            } else if (maxFreq == nextFreq) {
                ans += maxFreq;
            }
        }
        
        return ans;
    }
    
    public int official_sort(int[] nums) {
        int[] freq = new int[101];
        
        int maxFreq = 0;
        
        for (int num : nums) {
            freq[num]++;
        }
        
        Arrays.sort(freq);
        
        int maxFreqIndex = freq.length - 1;
        int ans = freq[maxFreqIndex];
        
        while (maxFreqIndex > 0 && freq[maxFreqIndex] == freq[maxFreqIndex - 1]) {
            ans += freq[maxFreqIndex];
            maxFreqIndex--;
        }
        
        return ans;
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