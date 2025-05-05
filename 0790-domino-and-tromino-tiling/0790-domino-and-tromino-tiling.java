class Solution {
    public int numTilings(int n) {
        return others(n);
    }

    public int others(int n) {
        long[] tilingWays = new long[n + 3];
        tilingWays[0] = 1;
        tilingWays[1] = 2;
        tilingWays[2] = 5;

        for (int i = 3; i < n; i++) {
            // tilingWays[i] = (tilingWays[i - 1] * 2 + tilingWays[i - 3]) % 1000000007;

            tilingWays[i] = (tilingWays[i - 1] + tilingWays[i - 2] + 2 * tilingWays[i - 3]) % 1000000007;
        }

        return (int) tilingWays[n - 1];
    }

    public int mySol_fail(int n) {
        boolean[][] tiles = new boolean[2][n];

        return dfs(tiles, 0, 0, 2 * n);
    }

    private int[][] typeA = {
        {0, 1},
        {1, 0}
    };

    private int[][][] typeB = {
        {{0, 1}, {1, 0}, {1, 1}},
        {{1, 0}, {1, 1}, {0, 1}}
    };

    private int mod = (int)1e9 + 7;

    private int dfs(boolean[][] tiles, int y, int x, int remain) {
        if (remain == 0) return 1;

        if (!isValid(tiles, y, x)) {
            System.out.println(String.format("ret 0. invalid y:%d, x:%d, remain:%d", y, x, remain));
            return 0;
        }

        if (!isValid(tiles, y, x) || tiles[y][x]) {
            System.out.println(String.format("ret 0. already used. y:%d, x:%d, remain:%d", y, x, remain));
            return 0;
        }

        tiles[y][x] = true;

        int ans = 0;

        for (int i = 0; i < typeA.length; i++) {
            int[] dir1 = typeA[i];
            int[] nextDir = typeA[(i + 1) % typeA.length];

            if (canUseTypeA(tiles, dir1, y, x)) {
                tiles[y + dir1[0]][x + dir1[1]] = true;

                ans = (ans + dfs(tiles, y + nextDir[0], x + nextDir[1], remain - 2)) % mod;

                tiles[y + dir1[0]][x + dir1[1]] = false;
            }
        }

        for (int i = 0; i < typeB.length; i++) {
            int[][] dir = typeB[i];

            if (canUseTypeB(tiles, dir, y, x)) {
                tiles[y + dir[0][0]][x + dir[0][1]] = true;
                tiles[y + dir[1][0]][x + dir[1][1]] = true;

                ans = (ans + dfs(tiles, y + dir[2][0], x + dir[2][1], remain - 3)) % mod;

                tiles[y + dir[0][0]][x + dir[0][1]] = false;
                tiles[y + dir[1][0]][x + dir[1][1]] = false;
            }
        }

        tiles[y][x] = false;

        return ans;
    }

    private boolean canUseTypeA(boolean[][] tiles, int[] dir, int y, int x) {
        int ny = y + dir[0];
        int nx = x + dir[1];
        
        if (!isValid(tiles, ny, nx) || tiles[ny][nx]) return false;

        return true;
    }

    private boolean canUseTypeB(boolean[][] tiles, int[][] dirs, int y, int x) {
        return canUseTypeA(tiles, dirs[0], y, x)
                && canUseTypeA(tiles, dirs[1], y, x);
    }

    private boolean isValid(boolean[][] tiles, int y, int x) {
        boolean v = y >= 0 && x >= 0 && y < 2 && x < tiles[0].length;

        // System.out.println(String.format("ny:%d, nx:%d is valid ?? %b", y, x, v));

        return v;
    }
}