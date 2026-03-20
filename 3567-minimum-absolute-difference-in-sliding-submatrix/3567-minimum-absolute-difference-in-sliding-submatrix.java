class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        return mySol_bf(grid, k);
    }

    public int[][] mySol_bf(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];

        for (int i = 0; i + k - 1 < m; i++) {
            for (int j = 0; j + k - 1 < n; j++) {
                ans[i][j] = getMin(grid, i, j, k);

                // System.out.println("i:%d, j:%d, ans:%d".formatted(i, j, ans[i][j]));
            }
        }

        return ans;
    }

    private int getMin(int[][] grid, int r, int c, int k) {

        List<Integer> list = new ArrayList();
        Set<Integer> set = new HashSet();

        for (int i = r; i < r + k; i++) {
            for (int j = c; j < c + k; j++) {
                list.add(grid[i][j]);
                set.add(grid[i][j]);
            }
        }

        if (set.size() == 1) return 0;

        Collections.sort(list);

        int prev = list.get(0);

        int min = Integer.MAX_VALUE;

        for (int ii = 1; ii < list.size(); ii++) {
            int num = list.get(ii);

            if (prev != num) {
                min = Math.min(min, num - prev);
            }

            prev = num;
        }

        return min;
    }

    public int[][] mySol_hold(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m][n];

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                treeMap.put(grid[i][j], treeMap.getOrDefault(grid[i][j], 0) + 1);
            }
        }

        return ans;
    }

    private void topdown(int[][] grid, int i, int j, int k, int dir, TreeMap<Integer, Integer> treeMap, int[][] ans) {
        if (i + k - 1 >= grid.length) return;

        if (j + k - 1 >= grid[0].length || j < 0) {
            int nextDir = -dir;
            j += nextDir;

            for (int jj = j; jj < j + k; jj += nextDir) {
                int deleted = grid[i][jj];
                treeMap.put(deleted, treeMap.get(deleted) - 1);

                if (treeMap.get(deleted) == 0) {
                    treeMap.remove(deleted);
                }

                if (i + k < grid.length) {
                    int added = grid[i + k][jj];

                    treeMap.put(added, treeMap.getOrDefault(added, 0) + 1);
                }
            }

            topdown(grid, i + 1, j, k, nextDir, treeMap, ans);

            return;
        }

        List<Integer> list = getList(ans, i, j, treeMap);

        if (list.size() == k * k) {
            int prev = list.get(0);
            for (int ii = 1; ii < list.size(); ii++) {
                int num = list.get(ii);

                ans[i][j] = Math.min(ans[i][j], num - prev);

                prev = num;
            }
        }

        topdown(grid, i, j + dir, k, dir, treeMap, ans);
    }

    private List<Integer> getList(int[][] ans, int i, int j, TreeMap<Integer, Integer> treeMap) {
        ans[i][j] = Integer.MAX_VALUE;

        List<Integer> list = new ArrayList();

        Integer num = treeMap.firstKey();

        while (num != null) {
            int count = treeMap.get(num);

            if (count > 1) {
                ans[i][j] = 0;
                break;
            }
            
            while (count-- > 0) {
                list.add(num);
            }
        }

        return list;
    }
}