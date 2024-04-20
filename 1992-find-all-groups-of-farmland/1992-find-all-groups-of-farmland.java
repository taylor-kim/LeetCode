class Solution {
    public int[][] findFarmland(int[][] land) {
        return mySol_dfs(land);
    }

    public int[][] mySol_dfs(int[][] land) {
        List<int[]> list = new ArrayList();

        int m = land.length;
        int n = land[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1) {
                    list.add(new int[] {i, j, i, j});
                    dfs(land, i, j, list);
                }
            }
        }

        int[][] ans = new int[list.size()][];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private void dfs(int[][ ]land, int r, int c, List<int[]> list) {
        if (r < 0 || r >= land.length || c < 0 || c >= land[0].length) return;

        if (land[r][c] != 1) return;

        land[r][c] = 2;

        int[] pos = list.get(list.size() - 1);

        // pos[0] = Math.min(pos[0], r);
        // pos[1] = Math.min(pos[1], c);

        pos[2] = Math.max(pos[2], r);
        pos[3] = Math.max(pos[3], c);

        dfs(land, r - 1, c, list);
        dfs(land, r + 1, c, list);
        dfs(land, r, c - 1, list);
        dfs(land, r, c + 1, list);
    }
}