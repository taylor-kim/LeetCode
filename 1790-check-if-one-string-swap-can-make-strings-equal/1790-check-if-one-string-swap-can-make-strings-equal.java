class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        return mySol(s1, s2);
    }

    public boolean mySol(String s1, String s2) {
        int n = s1.length();

        int maxDiff = 2;
        int diffCount = 0;
        int[] diffIndices = new int[maxDiff];

        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (c1 != c2) {
                if (diffCount + 1 > maxDiff) {
                    return false;
                }

                diffIndices[diffCount++] = i;
            }
        }

        if (diffCount == 0) {
            return true;
        } else if (diffCount != maxDiff) {
            return false;
        }

        return s1.charAt(diffIndices[0]) == s2.charAt(diffIndices[1])
                && s1.charAt(diffIndices[1]) == s2.charAt(diffIndices[0]);
    }
}