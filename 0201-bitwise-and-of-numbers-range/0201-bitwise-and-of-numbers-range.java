class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        return mySol(left, right);
    }
    
    public int mySol(int left, int right) {
        // 1  00000001
        // 2  00000010
        // 3  00000011
        // 4  00000100
        // 5  00000101
        // 6  00000110
        // 7  00000111
        // 8  00001000
        // 9  00001001
        // 10 00001010
        // 11 00001011
        // 12 00001100
        // 13 00001101
        // 14 00001110
        // 15 00001111
        // 16 00010000
        
//         if (left == 0) return 0;
        
//         if (left == right) {
//             return left;
//         }
        
        int ans = 0;
        
        while (left != 0 && right != 0) {
            int a = leftMostBit(left);
            int b = leftMostBit(right);
            
            if (a == b) {
                ans |= a;
                
                left = (left & (~a));
                right = (right & (~b));
            } else {
                break;
            }
        }
        
        return ans;
    }
    
    private int leftMostBit(int num) {
        for (int i = 31; i >= 0; i--) {
            int bit = 1 << i;
            
            if ((num & bit) != 0) {
                return bit;
            }
        }
        
        return 0;
    }
}