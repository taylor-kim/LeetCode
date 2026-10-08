class Solution {
    public String removeOuterParentheses(String s) {
        return mySol(s);
    }

    public String mySol(String s) {
        int n = s.length();
        int open = 0;
        StringBuilder ans = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ')') {
                open--;
            }

            if (open > 0) {
                ans.append(c);
            }

            if (c == '(') {
                open++;
            }
        }

        return ans.toString();
    }
}