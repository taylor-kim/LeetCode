class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        return mySol(words, groups);
    }

    public List<String> mySol(String[] words, int[] groups) {
        int n = words.length;
        int[] dp = new int[n];
        int maxLength = 0;
        int lastIndex = -1;
        int[] track = new int[n];
        Arrays.fill(track, -1);

        for (int i = 0; i < n; i++) {
            int length = words[i].length();
            dp[i] = length;

            for (int j = 0; j < i; j++) {
                if (groups[j] != groups[i] && dp[j] + length > dp[i]) {
                    dp[i] = dp[j] + length;
                    track[i] = j;
                }
            }

            if (maxLength < dp[i]) {
                lastIndex = i;
                maxLength = dp[i];
            }
        }

        List<String> ans = new ArrayList();

        while (lastIndex != -1) {
            ans.add(words[lastIndex]);
            lastIndex = track[lastIndex];
        }

        Collections.reverse(ans);

        return ans;
    }

    private void topdown(String[] words, int[] groups, int index, List<String> ans) {
        return;
    }
}