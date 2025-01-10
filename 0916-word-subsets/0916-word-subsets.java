class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        return mySol(words1, words2);
    }

    public List<String> mySol(String[] words1, String[] words2) {
        int[] subsetFreq = new int[26];

        for (String word : words2) {
            int[] freq = new int[26];
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
                subsetFreq[c - 'a'] = Math.max(subsetFreq[c - 'a'], freq[c - 'a']);
            }
        }

        List<String> ans = new ArrayList();

        for (String word : words1) {
            int[] freq = new int[26];
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }

            boolean isSubset = true;

            for (int i = 0; i < 26; i++) {
                if (freq[i] < subsetFreq[i]) {
                    isSubset = false;
                    break;
                }
            }

            if (isSubset) {
                ans.add(word);
            }
        }

        return ans;
    }
}