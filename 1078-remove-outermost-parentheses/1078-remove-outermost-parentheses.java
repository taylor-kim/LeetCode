class Solution {
    public String removeOuterParentheses(String s) {
        return mySol(s);
    }

    public String mySol(String s) {
        StringBuilder ans = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        int open = 0;

        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            sb.append(c);

            if (c == '(') {
                open++;
            } else {
                open--;
            }

            if (open == 0) {
                sb.deleteCharAt(0);
                sb.setLength(sb.length() - 1);
                ans.append(sb.toString());
                sb.setLength(0);
            }
        }

        return ans.toString();
    }
}