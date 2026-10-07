class Solution {
    public String removeOuterParentheses(String s) {
        return editorial(s);
    }

    public String editorial(String s) {
        StringBuilder ans = new StringBuilder();
        int open = 0;

        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

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