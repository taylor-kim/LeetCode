class Solution {
    public int longestPalindrome(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        int[] freq = new int[128];

        for (char c : s.toCharArray()) {
            freq[c - 'A']++;
        }

        int ans = 0;
        boolean odd = false;

        for (int f : freq) {
            if (f % 2 == 0) {
                ans += f;
            } else {
                odd = true;
                ans += (f - 1) % 2;
            }
        }

        return ans + (odd ? 1 : 0);
    }
}