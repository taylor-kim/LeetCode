class Solution {
    public int minimumLength(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int ans = 0;

        for (int count : freq) {
            if (count <= 2) {
                ans += count;
            } else {
                ans += count % 2 == 0 ? 2 : 1;
            }
        }

        return ans;
    }
}