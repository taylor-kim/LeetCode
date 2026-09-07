class Solution {
    public int distinctSubseqII(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        int[] count = new int[26];
        Arrays.fill(count, 1);

        int ans = 0;
        int mod = (int)1e9 + 7;

        for (char c : s.toCharArray()) {
            int node = c - 'a';

            int orphan = count[node];
            ans = (ans + orphan) % mod;

            for (int i = 0; i < 26; i++) {
                count[i] = (count[i] + orphan) % mod;
            }

            count[node] = orphan;
        }

        return ans;
    }
}