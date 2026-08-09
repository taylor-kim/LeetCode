class Solution {
    public int[] validSequence(String word1, String word2) {
        return editorial(word1, word2);
    }

    public int[] editorial(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] last = new int[m];
        Arrays.fill(last, -1);
        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                last[j--] = i;
            }
        }

        // System.out.println(Arrays.toString(last));
        
        int[] result = new int[m];
        j = 0;
        int adjust = 0;

        for (int i = 0; i < n; i++) {
            if (j == m) break;

            if (word1.charAt(i) == word2.charAt(j)) {
                result[j++] = i;
            } else {
                if (adjust == 0 && (j == m - 1 || i < last[j + 1])) {
                    result[j++] = i;
                    adjust++;
                }
            }
        }

        return j == m ? result : new int[0];
    }

    public int[] mySol_fail(String word1, String word2) {
        List<Integer>[] indices1 = new List[26];

        for (int i = 0; i < indices1.length; i++) {
            indices1[i] = new ArrayList();
        }

        for (int i = 0; i < word1.length(); i++) {
            char c = word1.charAt(i);

            if (indices1[c - 'a'] == null) indices1[c - 'a'] = new ArrayList();

            indices1[c - 'a'].add(i);
        }

        int[] result = new int[word2.length()];

        int adjust = 0;

        for (int i = 0; i < word2.length(); i++) {
            char c = word2.charAt(i);
            
            if (indices1[c - 'a'].size() == 0) {
                if (++adjust > 1) {
                    return new int[0];
                } else {
                    result[i] = -1;
                }
            } else {
                int index = indices1[c - 'a'].remove(0);
                int candidates = word1.length() - index - 1;
                int remain = word2.length() - i - 1;

                if (candidates < remain) {
                    if (++adjust > 1) {
                        return new int[0];
                    } else {
                        result[i] = -1;
                    }
                } else {
                    result[i] = index;
                }
            }
        }

        System.out.println(Arrays.toString(result));

        return result;
    }

    public int[] validSequence_fail(String word1, String word2) {
        List<Integer>[] indices1 = new List[26];

        for (int i = 0; i < word1.length(); i++) {
            char c = word1.charAt(i);

            if (indices1[c - 'a'] == null) indices1[c - 'a'] = new ArrayList();

            indices1[c - 'a'].add(i);
        }

        int[] result = new int[word2.length()];
        
        int j = 0;
        boolean skip = false;

        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                result[j] = i;

                if (indices1[word1.charAt(i) - 'a'].size() == 0) return new int[0];

                indices1[word1.charAt(i) - 'a'].remove(0);
            } else if (!skip) {
                // indices
            }
        }

        return null;
    }
}