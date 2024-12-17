class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        return mySol(s, repeatLimit);
    }

    public String mySol(String s, int repeatLimit) {
        int[] freq = new int[26];
        int maxChar = 0;

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
            maxChar = Math.max(maxChar, c - 'a');
        }

        StringBuilder ans = new StringBuilder();

        while (maxChar >= 0) {
            int count = 0;
            char c = (char)(maxChar + 'a');

            while (freq[maxChar]-- > 0) {
                ans.append((char)(maxChar + 'a'));
                count++;

                if (freq[maxChar] > 0 && count == repeatLimit) {
                    int lower = maxChar - 1;

                    while (lower >= 0 && freq[lower] == 0) {
                        lower--;
                    }

                    if (lower < 0) return ans.toString();

                    freq[lower]--;

                    ans.append((char)(lower + 'a'));

                    count = 0;
                }
            }

            maxChar--;
        }

        return ans.toString();
    }
}