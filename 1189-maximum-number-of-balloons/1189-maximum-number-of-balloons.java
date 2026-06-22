class Solution {
    public int maxNumberOfBalloons(String text) {
        return mySol(text);
    }

    public int mySol(String text) {
        int[] freq = new int[26];

        for (char c : text.toCharArray()) {
            freq[c - 'a']++;
        }

        String target = "balloon";
        Map<Character, Integer> targetFreq = new HashMap();

        for (char c : target.toCharArray()) {
            targetFreq.put(c, targetFreq.getOrDefault(c, 0) + 1);
        }

        int count = Integer.MAX_VALUE;

        for (char c : targetFreq.keySet()) {
            int need = targetFreq.get(c);
            int actual = freq[c - 'a'];

            count = Math.min(count, actual / need);
        }

        return count;
    }
}