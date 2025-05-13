class Solution {
    public int lengthAfterTransformations(String s, int t) {
        return official_dp(s, t);
    }

    public int official_dp(String s, int t) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int mod = (int)1e9 + 7;

        for (int i = 0; i < t; i++) {
            int[] next = new int[26];

            next[0] = freq[25];
            next[1] = (freq[25] + freq[0]) % mod;

            for (int c = 2; c < 26; c++) {
                next[c] = freq[c - 1];
            }

            freq = next;
        }

        int ans = 0;

        for (int count : freq) {
            ans = (ans + count) % mod;
        }

        return ans;
    }

    public int mySol_fail(String s, int t) {
        /**
        a -> z : 25
        b -> z : 24
        ..
        x -> z : 2
        y -> z : 1
        z -> z : 0

        26 * 1 -> ab
        26 * 2 -> abbc
        26 * 3 -> abbcbccd
        */

        int ans = 0;
        int mod = (int)1e9 + 7;

        for (char c : s.toCharArray()) {
            int diff = 'z' - c + 1;

            // System.out.println(diff);

            if (diff > t) {
                ans += 1;
            } else {
                int base = 2;

                int quotient = t / 26;
                int odd = t % diff;
                
            }
        }
        
        return "abbcbccd".length();
    }
}