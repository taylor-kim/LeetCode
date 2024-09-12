class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        return official_bit(allowed, words);
    }

    public int official_bit(String allowed, String[] words) {
        int bits = 0;

        for (char c : allowed.toCharArray()) bits |= 1 << (c - 'a');

        int ans = 0;

        for (String word : words) {
            int delta = 1;
            for (char c : word.toCharArray()) {
                int matched = (bits >> (c - 'a')) & 1;

                if (matched == 0) {
                    delta = 0;
                    break;
                }
            }

            ans += delta;
        }

        return ans;
    }

    public int mySol(String allowed, String[] words) {
        int[] freq = new int[26];

        for (char c : allowed.toCharArray()) freq[c - 'a']++;

        int ans = 0;

        for (String word : words) {
            int delta = 1;
            for (char c : word.toCharArray()) {
                if (freq[c - 'a'] == 0) {
                    delta = 0;
                    break;
                }
            }

            ans += delta;
        }

        return ans;
    }
}