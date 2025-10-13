class Solution {
    public List<String> removeAnagrams(String[] words) {
        return mySol(words);
    }

    public List<String> mySol(String[] words) {
        List<String> ans = new ArrayList();

        int[] freq = new int[26];

        int i = 0;

        int[] f1 = getFreq(words[0]);

        while (i < words.length) {
            ans.add(words[i++]);

            while (i < words.length) {
                int[] f2 = getFreq(words[i]);

                if (isAnagram(f1, f2)) {
                    i++;
                } else {
                    f1 = f2;
                    break;
                }
            }
        }

        return ans;
    }

    private int[] getFreq(String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        return freq;
    }

    private boolean isAnagram(int[] f1, int[] f2) {
        for (int i = 0; i < f1.length; i++) {
            if (f1[i] != f2[i]) return false;
        }
        
        return true;
    }
}