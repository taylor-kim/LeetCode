class Solution {
    public char[][] rotateTheBox(char[][] box) {
        return try_20260506_improved(box);
    }

    public char[][] try_20260506_improved(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        char[][] ans = new char[n][m];

        for (int i = 0; i < m; i++) {
            int stone = 0;
            for (int j = 0; j < n; j++) {
                if (box[i][j] == '*') {
                    for (int k = j - 1; stone > 0; k--) {
                        box[i][k] = '#';
                        stone--;
                    }
                } else {
                    stone += box[i][j] == '#' ? 1 : 0;
                    box[i][j] = '.';
                }
            }

            int k = n - 1;

            while (stone-- > 0) {
                box[i][k--] = '#';
            }

            for (int j = 0; j < n; j++) {
                ans[j][m - i - 1] = box[i][j];
            }
        }

        return ans;
    }

    public char[][] try_20260506(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        char[][] ans = new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[j][m - i - 1] = box[i][j];
            }
        }

        // for (char[] row : ans) {
        //     System.out.println(Arrays.toString(row));
        // }

        for (int j = 0; j < m; j++) {
            int start = 0;
            int stone = 0;

            for (int i = 0; i < n; i++) {
                if (ans[i][j] == '*') {
                    int empty = (i - start) - stone;

                    while (empty-- > 0 && start < i) {
                        ans[start++][j] = '.';
                    }

                    while (stone-- > 0 && start < i) {
                        ans[start++][j] = '#';
                    }

                    start = i + 1;
                    stone = 0;
                } else if (ans[i][j] == '#') {
                    stone++;
                }
            }

            int empty = (n - start) - stone;

            while (empty-- > 0 && start < n) {
                ans[start++][j] = '.';
            }

            while (stone-- > 0 && start < n) {
                ans[start++][j] = '#';
            }
        }

        return ans;
    }

    public char[][] mySol(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        
        char[][] ans = new char[n][m];

        for (char[] row : ans) {
            Arrays.fill(row, '.');
        }

        for (int i = 0; i < m; i++) {
            int row = n - 1;
            int col = m - i - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (box[i][j] == '*') {
                    row = j;
                    ans[row--][col] = '*';
                } else if (box[i][j] == '#') {
                    ans[row--][col] = '#';
                }
            }
        }

        return ans;
    }
}