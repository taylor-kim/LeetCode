class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        return mySol(words, queries);
    }

    public int[] mySol(String[] words, int[][] queries) {
        int n = words.length;
        int[] pSum = new int[n + 1];
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

        for (int i = 0; i < words.length; i++) {
            pSum[i + 1] = pSum[i] + 
                (
                    vowels.contains(words[i].charAt(0))
                    && vowels.contains(words[i].charAt(words[i].length() - 1))
                    ? 1 : 0
                );
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            ans[i] = pSum[queries[i][1] + 1] - pSum[queries[i][0]];
        }

        return ans;
    }
}