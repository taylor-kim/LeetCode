class Solution {
    public String compressedString(String word) {
        char prev = word.charAt(0);
        
        StringBuilder sb = new StringBuilder();
        
        int index = 0;
        int count = 0;
        
        while (index < word.length()) {
            prev = word.charAt(index);
            
            while (index < word.length() && prev == word.charAt(index)) {
                count++;
                index++;
            }
            
            if (count != 0) {
                int n = count / 9;
                int odd = count % 9;

                while (n-- > 0) {
                    sb.append("9").append(prev);
                }
                
                if (odd > 0) {
                    sb.append(odd).append(prev);
                }
            }
            
            count = 0;
        }
        
        return sb.toString();
    }
}