class Solution {
    public int countPalindromicSubsequence(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        int ans = 0;

        for (int i = 0; i < 26; i++) {
            char c = (char)(i + 'a');

            ans += count(s, c);
        }

        return ans;
    }

    private int count(String s, char base) {
        int left = 0;
        int right = s.length() - 1;

        while (left < s.length() && s.charAt(left) != base) left++;

        while (right >= 0 && s.charAt(right) != base) right--;

        int[] freq = new int[26];

        for (int i = left + 1; i < right; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int uniq = 0;

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) uniq++;
        }

        return uniq;
    }
}