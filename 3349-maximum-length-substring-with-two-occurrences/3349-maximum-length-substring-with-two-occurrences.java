class Solution {
    public int maximumLengthSubstring(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        int ans = 0;

        int[] freq = new int[26];

        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq[c - 'a']++;

            while (freq[c - 'a'] > 2) {
                freq[s.charAt(left++) - 'a']--;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}