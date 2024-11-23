class Solution {
    public char[][] rotateTheBox(char[][] box) {
        return mySol(box);
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