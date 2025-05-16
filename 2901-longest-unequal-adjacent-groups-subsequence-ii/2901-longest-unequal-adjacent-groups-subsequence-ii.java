class Solution {
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        return mySol(words, groups);
    }

    public List<String> mySol(String[] words, int[] groups) {
        int n = words.length;
        int[] dp = new int[n];
        int[] indices = new int[n];
        int lastIndex = -1;
        int max = 0;

        Arrays.fill(indices, -1);

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (groups[i] != groups[j]
                    && isHammingDistance(words[i], words[j])
                    && dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        indices[i] = j;
                }
            }

            if (max < dp[i]) {
                max = dp[i];
                lastIndex = i;
            }
        }

        List<String> ans = new ArrayList();

        while (lastIndex != -1) {
            ans.add(words[lastIndex]);
            
            lastIndex = indices[lastIndex];
        }

        Collections.reverse(ans);

        return ans;
    }

    private boolean isHammingDistance(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        int count = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) count++;

            if (count > 1) {
                return false;
            }
        }

        return count == 1;
    }
}