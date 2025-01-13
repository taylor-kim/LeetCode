class Solution {
    public int minimumLength(String s) {
        return official_two_pointer(s);
    }

    public int official_two_pointer(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right && s.charAt(left) == s.charAt(right)) {
            char c = s.charAt(left);
            
            while (left <= right && s.charAt(left) == c) {
                left++;
            }
            
            while (left <= right && s.charAt(right) == c) {
                right--;
            }
        }
        
        return right - left + 1;
    }

    public int try_20250113(String s) {
        int n = s.length();
        int left = 0;
        int right = n - 1;

        while (left < right && s.charAt(left) == s.charAt(right)) {
            while (left + 1 < right && s.charAt(left) == s.charAt(left + 1)) {
                left++;
            }

            while (right - 1 > left && s.charAt(right - 1) == s.charAt(right)) {
                right--;
            }

            left++;
            right--;
        }

        return Math.max(0, right - left + 1);
    }
}