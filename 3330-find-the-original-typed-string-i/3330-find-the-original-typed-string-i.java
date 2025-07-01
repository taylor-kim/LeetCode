class Solution {
    public int possibleStringCount(String word) {
        return mySol2(word);
    }

    public int mySol2(String word) {
        int n = word.length();
        int ans = 0;

        int left = 0;

        for (int right = 0; right < n; right++) {
            char c = word.charAt(right);

            if (word.charAt(left) == c) continue;

            ans += right - left - 1;
            left = right;
        }

        ans += n - left;

        return ans;
    }

    public int mySol_fail(String word) {
        int[] freq = new int[26];

        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        int ans = 1;

        for (int count : freq) {
            if (count > 1) {
                ans += count - 1;
            }
        }

        return ans;
    }
}