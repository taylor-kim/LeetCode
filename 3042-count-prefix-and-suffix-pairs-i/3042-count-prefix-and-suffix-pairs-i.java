class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        return mySol(words);
    }

    public int mySol(String[] words) {
        int ans = 0;

        for (int i = 0; i < words.length; i++) {
            String a = words[i];
            for (int j = i + 1; j < words.length; j++) {
                String b = words[j];

                if (a.equals(b) || (b.startsWith(a) && b.endsWith(a))) {
                    ans++;
                }
            }
        }

        return ans;
    }
}