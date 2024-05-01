class Solution {
    public long wonderfulSubstrings(String word) {
        return official_bit(word);
    }

    public long official_bit(String word) {
        int n = word.length();
        Map<Integer, Integer> freq = new HashMap();
        freq.put(0, 1);

        int mask = 0;

        long ans = 0l;

        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            int bit = c - 'a';

            mask ^= (1 << bit);

            int count = freq.getOrDefault(mask, 0);

            ans += count;

            freq.put(mask, count + 1);

            for (int odd = 0; odd < 10; odd++) {
                ans += freq.getOrDefault(mask ^ (1 << odd), 0);
            }
        }

        return ans;
    }

    public long mySol_sliding_window_fail(String word) {
        int left = 0;
        int[] dp = new int[10];

        long ans = 0;

        for (int right = 0; right < word.length(); right++) {
            char c = word.charAt(right);

            dp[c - 'a']++;

            while (left < right && !isBeautiful(dp)) {
                dp[word.charAt(left++) - 'a']--;
            }

            ans++;
        }

        return ans;
    }

    private boolean isBeautiful(int[] dp) {
        int oddCount = 0;

        for (int count : dp) {
            if (count % 2 == 1) oddCount++;
        }

        return oddCount <= 1;
    }
}