class Solution {
    public int maxDepth(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        int ret = 0;
        int p = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                p++;
                ret = Math.max(ret, p);
            } else if (s.charAt(i) == ')') {
                p--;
            }
        }

        return ret;
    }
}