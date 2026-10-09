class Solution {
    public int minInsertions(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        int n = s.length();
        int ans = 0;
        int open = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
            } else {
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++;
                    open--;
                } else {
                    ans++;
                    open--;
                }
            }

            if (open < 0) {
                ans++;
                open = 0;
            }
        }

        return ans + (open * 2);
    }
}