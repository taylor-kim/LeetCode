// By @Arnab
class Solution {
    private static final int INF = Integer.MAX_VALUE / 2;

    public String minCostGoodCaption(String caption) {
        int n = caption.length();
        if (n < 3) return "";

        char[] arr = caption.toCharArray();
        int[][] prefixCost = new int[n + 1][26];

        for (int i = 0; i < n; i++) {
            int orig = arr[i] - 'a';
            for (int c = 0; c < 26; c++) {
                prefixCost[i + 1][c] = prefixCost[i][c] + Math.abs(orig - c);
            }
        }

        int[] dp = new int[n + 1];
        int[] nextIndex = new int[n + 1];
        int[] nextChar = new int[n + 1];
        int[] blockLen = new int[n + 1];

        for (int i = 0; i < n; i++) {
            dp[i] = INF;
            nextIndex[i] = -1;
            nextChar[i] = -1;
            blockLen[i] = 0;
        }
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int l = 3; l <= 5; l++) {
                if (i + l <= n) {
                    int bestLetter = 0, bestCost = INF;
                    for (int c = 0; c < 26; c++) {
                        int costBlock = prefixCost[i + l][c] - prefixCost[i][c];
                        if (costBlock < bestCost) {
                            bestCost = costBlock;
                            bestLetter = c;
                        }
                    }

                    int costCandidate = dp[i + l] + bestCost;
                    if (costCandidate < dp[i]) {
                        dp[i] = costCandidate;
                        nextIndex[i] = i + l;
                        nextChar[i] = bestLetter;
                        blockLen[i] = l;
                    } else if (costCandidate == dp[i]) {
                        int cmp = compareSolutions(i, i, nextChar[i], blockLen[i], nextIndex[i],
                                bestLetter, l, (i + l), nextIndex, nextChar, blockLen, n, dp);
                        if (cmp > 0) {
                            nextIndex[i] = i + l;
                            nextChar[i] = bestLetter;
                            blockLen[i] = l;
                        }
                    }
                }
            }
        }

        if (dp[0] >= INF) return "";

        StringBuilder sb = new StringBuilder(n);
        int idx = 0;
        while (idx < n) {
            int len = blockLen[idx];
            int c = nextChar[idx];
            for (int k = 0; k < len; k++) {
                sb.append((char) ('a' + c));
            }
            idx = nextIndex[idx];
        }
        return sb.toString();
    }

    private int compareSolutions(int startPos, int oldPos, int oldLetter, int oldLen, int oldNext,
                                 int newLetter, int newLen, int newNext, int[] nextIndex,
                                 int[] nextChar, int[] blockLen, int n, int[] dp) {
        int offsetOld = 0, offsetNew = 0;
        int curOldPos = oldPos, curNewPos = startPos;
        int letOld = oldLetter, letNew = newLetter;
        int lenOld = oldLen, lenNew = newLen;
        int nxtOld = oldNext, nxtNew = newNext;

        while (true) {
            if (letOld != letNew) return (letOld < letNew) ? -1 : 1;

            int remainOld = lenOld - offsetOld, remainNew = lenNew - offsetNew;
            int step = Math.min(remainOld, remainNew);
            offsetOld += step;
            offsetNew += step;

            if (offsetOld == lenOld && offsetNew == lenNew) {
                if (nxtOld == n && nxtNew == n) return 0;
                if (nxtOld == n) return -1;
                if (nxtNew == n) return 1;
                curOldPos = nxtOld;
                letOld = nextChar[curOldPos];
                lenOld = blockLen[curOldPos];
                nxtOld = nextIndex[curOldPos];
                offsetOld = 0;

                curNewPos = nxtNew;
                letNew = nextChar[curNewPos];
                lenNew = blockLen[curNewPos];
                nxtNew = nextIndex[curNewPos];
                offsetNew = 0;
            } else if (offsetOld == lenOld) {
                if (nxtOld == n) return -1;
                curOldPos = nxtOld;
                letOld = nextChar[curOldPos];
                lenOld = blockLen[curOldPos];
                nxtOld = nextIndex[curOldPos];
                offsetOld = 0;
            } else if (offsetNew == lenNew) {
                if (nxtNew == n) return 1;
                curNewPos = nxtNew;
                letNew = nextChar[curNewPos];
                lenNew = blockLen[curNewPos];
                nxtNew = nextIndex[curNewPos];
                offsetNew = 0;
            }
        }
    }
}