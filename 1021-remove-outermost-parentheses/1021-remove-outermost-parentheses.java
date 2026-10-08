class Solution {
    public String removeOuterParentheses(String s) {
        return mySol(s);
    }

    public String editorial(String s) {
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

    public String mySol(String s) {
        int n = s.length();
        int open = 0;
        StringBuilder ans = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            open += c == '(' ? 1 : -1;
            sb.append(c);

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