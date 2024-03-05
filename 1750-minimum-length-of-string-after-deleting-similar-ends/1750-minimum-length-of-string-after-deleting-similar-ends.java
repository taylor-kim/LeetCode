class Solution {
    public int minimumLength(String s) {
        return official_rec(s);
    }
    
    public int official_rec(String s) {
        return official_rec(s, 0, s.length() - 1);
    }
    
    public int official_rec(String s, int left, int right) {
        if (left >= right || s.charAt(left) != s.charAt(right)) {
            return right - left + 1;
        }
        
        char c = s.charAt(left);
        
        while (left <= right && s.charAt(left) == c) {
            left++;
        }
        
        while (left < right && s.charAt(right) == c) {
            right--;
        }
        
        return official_rec(s, left, right);
    }
    
    public int official_two_pointer(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right && s.charAt(left) == s.charAt(right)) {
            char c = s.charAt(left);
            
            while (left <= right && s.charAt(left) == c) {
                left++;
            }
            
            while (left < right && s.charAt(right) == c) {
                right--;
            }
        }
        
        return right - left + 1;
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