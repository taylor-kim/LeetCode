class Solution {
    public int strStr(String haystack, String needle) {
        int[] pi = createPi(needle);

        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        
        int matchingCount = 0;
        int begin = 0;

        while (begin + matchingCount < h.length) {
            if (h[begin + matchingCount] == n[matchingCount]) {
                matchingCount++;

                if (matchingCount == n.length) {
                    return begin;
                }
            } else {
                if (matchingCount == 0) {
                    begin++;
                } else {
                    begin += matchingCount - pi[matchingCount - 1];
                    matchingCount = pi[matchingCount - 1];
                }
            }
        }

        return -1;
    }

    private int[] createPi(String s) {
        char[] arr = s.toCharArray();
        int[] pi = new int[arr.length];

        int begin = 1;
        int matchingCount = 0;

        while (begin + matchingCount < arr.length) {
            if (arr[begin + matchingCount] == arr[matchingCount]) {
                matchingCount++;
                pi[begin + matchingCount - 1] = matchingCount;
            } else {
                if (matchingCount == 0) {
                    begin++;
                } else {
                    begin += matchingCount - pi[matchingCount - 1];
                    matchingCount = pi[matchingCount - 1];
                }
            }
        }

        return pi;
    }
}