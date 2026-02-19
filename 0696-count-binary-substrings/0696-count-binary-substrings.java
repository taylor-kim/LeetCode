class Solution {
    public int countBinarySubstrings(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        int ans = 0;

        int i = 0;

        while (i < s.length()) {
            char d1 = s.charAt(i);

            int j = i + 1;

            while (j < s.length() && s.charAt(j) == d1) {
                j++;
            }

            int count1 = j - i;

            int nextStart = j;

            while (j < s.length() && s.charAt(j) != d1) {
                j++;
            }

            int count2 = j - nextStart;

            ans += Math.min(count1, count2);

            i = nextStart;
        }

        return ans;
    }
}