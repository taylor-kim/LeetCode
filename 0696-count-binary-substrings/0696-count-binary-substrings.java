class Solution {
    public int countBinarySubstrings(String s) {
        return mySol_improved(s);
    }

    public int mySol_improved(String s) {
        int ans = 0;
        int n = s.length();

        char prev = s.charAt(0) == '0' ? '1' : '0';
        int prevCount = 0;

        int i = 0;

        while (i < n) {
            int count = 0;

            while (i + count < n && prev != s.charAt(i + count)) {
                count++;
            }

            ans += Math.min(prevCount, count);
            prevCount = count;
            prev = s.charAt(i);

            i += count;
        }

        return ans;
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