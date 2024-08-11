class Solution {
    public int minimumPushes(String word) {
        return mySol(word);
    }

    public int mySol(String word) {
        int[] freq = new int[26];

        int max = 0;

        for (char c : word.toCharArray()) {
            max = Math.max(max, ++freq[c - 'a']);
        }

        int[] bucket = new int[max + 1];

        for (int f : freq) {
            if (f > 0) {
                bucket[f]++;
            }
        }

        int index = max;
        int countOfChar = 0;

        int ans = 0;

        while (index > 0) {
            while (bucket[index]-- > 0) {
                ans += (countOfChar++ / 8 + 1) * index;
            }

            index--;
        }

        return ans;
    }
}