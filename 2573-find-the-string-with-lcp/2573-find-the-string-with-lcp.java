class Solution {
    public String findTheString(int[][] lcp) {
        return official_intuition(lcp);
    }

    public String official_intuition(int[][] lcp) {
        int n = lcp.length;

        char[] words = new char[n];

        char current = 'a';

        for (int i = 0; i < n; i++) {
            if (words[i] == 0) {
                if (current > 'z') return "";

                words[i] = current;
                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        words[j] = words[i];
                    }
                }
                current++;
            }
            // System.out.println("current:%c, words:%s".formatted(current, Arrays.toString(words)));
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (words[i] != words[j]) {
                    if (lcp[i][j] > 0) return "";
                } else {
                    if (i == n - 1 || j == n - 1) {
                        if (lcp[i][j] != 1) return "";
                    } else {
                        if (lcp[i][j] != lcp[i + 1][j + 1] + 1) return "";
                    }
                }
            }
        }

        // int[][] myLcp = new int[n][n];

        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         if (i == j) {
        //             myLcp[i][j] = n - i;
        //         } else {
        //             int count = 0;

        //             while (Math.max(i, j) + count < n && words[i + count] == words[j + count]) {
        //                 myLcp[i][j]++;
        //                 count++;
        //             }
        //         }
        //     }
        // }

        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         if (lcp[i][j] != myLcp[i][j]) return "";
        //     }
        // }

        return new String(words);
    }

    public String mySol_fail(int[][] lcp) {
        int n = lcp.length;

        StringBuilder sb = new StringBuilder();

        if (n == 0) return sb.toString();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    if (lcp[i][j] != n) return "";
                    sb.append("a".repeat(n));
                } else if (i == j) {
                    if (lcp[i][j] > n - i) return "";
                } else {
                    int length = lcp[i][j];

                    int smaller = Math.min(i, j);
                    int bigger = Math.max(i, j);

                    if (length == 0) {
                        if (sb.charAt(smaller) != sb.charAt(bigger)) {
                            continue;
                        }
                        sb.setCharAt(bigger, (char)(sb.charAt(smaller) + 1));
                    } else {
                        for (int k = 0; k < length; k++) {
                            sb.setCharAt(bigger++, (char)(sb.charAt(smaller++)));
                        }
                    }

                    // System.out.println("i:%d, j:%d, s:%s".formatted(i, j, sb.toString()));
                }
            }
        }

        return sb.toString();
    }
}