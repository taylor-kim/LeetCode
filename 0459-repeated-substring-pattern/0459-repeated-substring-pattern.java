class Solution {
    public boolean repeatedSubstringPattern(String s) {
        return try_20241125(s);
    }

    public boolean try_20241125(String s) {
        int[] pt = createPT(s);

        return pt[s.length() - 1] != 0 && s.length() % (s.length() - pt[s.length() - 1]) == 0;
    }

    private int[] createPT(String s) {
        int[] pt = new int[s.length()];

        int start = 1;
        int matchCount = 0;

        while (start + matchCount < s.length()) {
            if (s.charAt(start + matchCount) == s.charAt(matchCount)) {
                pt[start + matchCount] = matchCount + 1;
                matchCount++;
            } else {
                if (matchCount == 0) {
                    start++;
                } else {
                    start += matchCount - pt[matchCount - 1];
                    matchCount = pt[matchCount - 1];
                }
            }
        }

        return pt;
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