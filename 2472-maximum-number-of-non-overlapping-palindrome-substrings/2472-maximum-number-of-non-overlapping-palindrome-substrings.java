class Solution {
    public int maxPalindromes(String s, int k) {
        return mySol(s, k);
    }

    public int mySol(String s, int k) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            check(s, i, i, isPalindrome);
            check(s, i, i + 1, isPalindrome);
        }

        List<int[]> coordinates = new ArrayList();

        for (int i = 0; i <= n - k; i++) {
            for (int j = i + k - 1; j < n; j++) {
                if (isPalindrome[i][j]) {
                    coordinates.add(new int[] {i, j});
                }
            }
        }

        Collections.sort(coordinates, (a, b) -> {
            return a[1] - b[1];
        });

        // dp[start] 보다 먼저 끝난 겹치지 않는 회문 갯수
        int[] dp = new int[n + 1];

        int count = 0;
        int end = -1;

        for (int[] coordinate : coordinates) {
            if (end < coordinate[0]) {
                count++;
                end = coordinate[1];
            }
        }

        // for (int i = 0; i <= n - k; i++) {
        //     ans = Math.max(ans, count(i, k, isPalindrome));
        // }

        // for (boolean[] row : isPalindrome) {
        //     System.out.println(Arrays.toString(row));
        // }

        return count;
    }

    private int count(int i, int k, boolean[][] isPalindrome) {
        int n = isPalindrome.length;

        int j = i + k - 1;

        int count = 0;

        while (j < n) {
            if (isPalindrome[i][j]) {
                count++;
                i = j + 1;
                j = i + k - 1;
            } else {
                j++;
            }
        }

        System.out.println("i:%d, count:%d".formatted(i, count));

        return count;
    }

    private void check(String s, int i, int j, boolean[][] isPalindrome) {
        int n = s.length();

        while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
            if ((j - i <= 1 || isPalindrome[i + 1][j - 1])) {
                isPalindrome[i][j] = true;
            }
            i--;
            j++;
        }
    }
}