class Solution {
    public int minimumPushes(String word) {
        return mySol(word);
    }

    public int mySol(String word) {
        int[] freq = new int[26];
        int ans = 0;

        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        Arrays.sort(freq);

        int count = 0;
        int limit = 8;

        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break;

            ans += (count++ / limit + 1) * freq[i];
        }

        return ans;
    }
}