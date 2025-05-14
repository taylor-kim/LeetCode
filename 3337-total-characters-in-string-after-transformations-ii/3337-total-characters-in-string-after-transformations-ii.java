class Solution {
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        return mySol(s, t, nums);
    }

    public int mySol(String s, int t, List<Integer> nums) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int mod = (int)1e9 + 7;

        for (int i = 0; i < t; i++) {
            int[] next = new int[26];

            for (int j = 0; j < freq.length; j++) {
                if (freq[j] == 0) continue;

                int limit = nums.get(j);
                
                for (int k = 1; k <= limit; k++) {
                    int index = (j + k) % 26;
                    next[index] = (next[index] + freq[j]) % mod;
                }
            }

            // print(next);

            freq = next;
        }

        int ans = 0;

        for (int count : freq) {
            ans = (ans + count) % mod;
        }

        return ans;
    }

    private void print(int[] next) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < next.length; i++) {
            if (next[i] > 0) {
                sb.append(String.valueOf((char)(i + 'a')).repeat(next[i]));
            }
        }

        System.out.println(sb.toString());
    }
}