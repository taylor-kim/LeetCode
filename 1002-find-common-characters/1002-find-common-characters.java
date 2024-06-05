class Solution {
    public List<String> commonChars(String[] words) {
        return mySol(words);
    }

    public List<String> mySol(String[] words) {
        int[][] freqs = new int[words.length][26];

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (char c : word.toCharArray()) {
                freqs[i][c - 'a']++;
            }
        }

        List<String> ans = new ArrayList();

        for (int i = 0; i < 26; i++) {
            int minFreq = Integer.MAX_VALUE;

            for (int j = 0; j < words.length; j++) {
                minFreq = Math.min(freqs[j][i], minFreq);
            }

            while (minFreq-- > 0) {
                ans.add(String.valueOf((char)(i + 'a')));
            }
        }

        return ans;
    }
}