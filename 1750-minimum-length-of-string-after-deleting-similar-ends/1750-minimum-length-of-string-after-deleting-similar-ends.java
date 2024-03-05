class Solution {
    public int minimumLength(String s) {
        return mySol(s);
    }
    
    public int mySol(String s) {
        int n = s.length();
        int removed = 0;
        
        int left = 0;
        int right = n - 1;
        
        while (left < right) {
            char lc = s.charAt(left);
            char rc = s.charAt(right);
            
            if (lc != rc) break;
            
            removed += 2;
            
            while (++left < right && lc == s.charAt(left)) {
                removed++;
            }
            
            while (--right > left && rc == s.charAt(right)) {
                removed++;
            }
        }
        
        return n - removed;
    }
}