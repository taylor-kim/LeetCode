class Solution {
    public int reverseDegree(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';

            ans += (i + 1) * (26 - index);
        }

        return ans;
    }
}