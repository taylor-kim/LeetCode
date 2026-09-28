class Solution {
    public int maxDepth(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        int ans = 0;
        int open = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
                ans = Math.max(ans, open);
            } else if (c == ')') {
                open--;
            }
        }

        return ans;
    }
}