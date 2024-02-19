class Solution {
    public boolean isPowerOfTwo(int n) {
        return mySol(n);
    }
    
    public boolean mySol(int n) {
        if (n < 0) return false;
        
        int bitCount = 0;
        
        for (int i = 0; i < 31; i++) {
            int bit = 1 << i;
            
            if ((n & bit) != 0) {
                bitCount++;
            }
        }
        
        return bitCount == 1;
    }
}