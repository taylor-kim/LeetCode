class Solution {
    public String makeFancyString(String s) {
        return mySol(s);
    }
    
    public String mySol(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (sb.length() < 2 || sb.charAt(sb.length() - 1) != c || sb.charAt(sb.length() - 2) != c) {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}