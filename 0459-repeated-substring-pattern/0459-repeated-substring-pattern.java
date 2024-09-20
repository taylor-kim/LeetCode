class Solution {
    public boolean repeatedSubstringPattern(String s) {
        return try_20240921(s);
    }

    public boolean try_20240921(String s) {
        int[] pi = createPI(s);

        return pi[s.length() - 1] != 0 && pi[s.length() - 1] % (s.length() - pi[s.length() - 1]) == 0;
    }

    private int[] createPI(String s) {
        int n = s.length();
        int[] pi = new int[n];

        int start = 1;
        int matching = 0;

        while (start + matching < n) {
            if (s.charAt(start + matching) == s.charAt(matching)) {
                matching++;
                pi[start + matching - 1] = matching;
            } else {
                if (matching == 0) {
                    start++;
                } else {
                    start += matching - pi[matching - 1];
                    matching = pi[matching - 1];
                }
            }
        }

        return pi;
    }
}