class Solution {
    public String firstPalindrome(String[] words) {
        return mySol(words);
    }
    
    public String mySol(String[] words) {
        for (String s : words) {
            int left = 0;
            int right = s.length() - 1;
            
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                left++;
                right--;
            }
            
            if (left >= right) {
                return s;
            }
        }
        
        return "";
    }
}