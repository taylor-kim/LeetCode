class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        return official_counting(arr, mat);
    }

    public int official_counting(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        Map<Integer, int[]> map = new HashMap();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.put(mat[i][j], new int[] {i, j});
            }
        }

        for (int i = 0; i < m * n; i++) {
            int[] yx = map.get(arr[i]);

            rowCount[yx[0]]++;
            colCount[yx[1]]++;

            if (rowCount[yx[0]] == n || colCount[yx[1]] == m) {
                return i;
            }
        }

        return -1;
    }

    public int mySol(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        long[] rowSum = new long[m];
        long[] colSum = new long[n];

        Map<Integer, int[]> map = new HashMap();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i] += mat[i][j];
                colSum[j] += mat[i][j];

                map.put(mat[i][j], new int[] {i, j});
            }
        }

        for (int i = 0; i < m * n; i++) {
            int[] yx = map.get(arr[i]);
            rowSum[yx[0]] -= arr[i];
            colSum[yx[1]] -= arr[i];

            if (rowSum[yx[0]] == 0 || colSum[yx[1]] == 0) {
                return i;
            }
        }

        return -1;
    }
}