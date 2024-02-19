class Solution {
    public boolean isPowerOfTwo(int n) {
        return rec(n);
    }
    
    public boolean rec(int n) {
        if (n <= 0) return false;
        
        if (n == 1 || n == 2) return true;
        
        if (n % 2 == 1) return false;
        
        return rec(n / 2);
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