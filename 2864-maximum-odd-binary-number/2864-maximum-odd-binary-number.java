class Solution {
    public String maximumOddBinaryNumber(String s) {
        return mySol(s);
    }
    
    public String mySol(String s) {
        int n = s.length();
        int numberOfOne = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '1') {
                numberOfOne++;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("1".repeat(numberOfOne - 1));
        sb.append("0".repeat(n - numberOfOne));
        sb.append("1");
        
        return sb.toString();
    }
}