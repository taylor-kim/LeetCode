class Solution {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        return mySol(words, letters, score);
    }

    public int mySol(String[] words, char[] letters, int[] score) {
        Map<Character, Integer> freq = new HashMap();

        for (char c : letters) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        return mySol(words, letters, score, 0, freq);
    }

    public int mySol(String[] words, char[] letters, int[] score, int index, Map<Character, Integer> freq) {
        if (index >= words.length || freq.size() == 0) return 0;

        String word = words[index];

        int made = 0;
        int currentScore = 0;

        int exclude = mySol(words, letters, score, index + 1, freq);

        for (char c : word.toCharArray()) {
            if (freq.getOrDefault(c, 0) > 0) {
                freq.put(c, freq.get(c) - 1);
                if (freq.get(c) == 0) {
                    freq.remove(c);
                }
                made++;
                currentScore += score[c - 'a'];
            } else {
                // freq.remove(c);
                break;
            }
        }

        int include = 0;

        if (word.length() == made) {
            include = currentScore + mySol(words, letters, score, index + 1, freq);
        }

        for (int i = 0; i < made; i++) {
            char c = word.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        return Math.max(include, exclude);
    }
}