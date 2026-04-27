class Solution {
    public boolean hasValidPath(int[][] grid) {
        return mySol2(grid);
    }

    public boolean mySol2(int[][] grid) {
        Set<Integer> visited = new HashSet();

        return topdown(grid, 0, 0);
    }

    private boolean topdown(int[][] grid, int r, int c) {
        // System.out.println("r:%d, c:%d, val:%d".formatted(r, c, grid[r][c]));

        if (r == grid.length - 1 && c == grid[0].length - 1) return true;

        if (grid[r][c] < 0) return false;

        int current = grid[r][c];

        grid[r][c] -= grid[r][c];

        for (int i = 0; i < dirs.length; i++) {
            int nextR = r + dirs[i][0];
            int nextC = c + dirs[i][1];

            if (nextR < 0 || nextR >= grid.length || nextC < 0 || nextC >= grid[0].length) continue;

            int next = grid[nextR][nextC];

            // System.out.println("\ti:%d, nextR:%d, nextC:%d".formatted(i, nextR, nextC));

            if (nextMoves.get(i).getOrDefault(current, new HashSet<>()).contains(next)) {
                // System.out.println("\t\tok!! nextR:%d, nextC:%d".formatted(nextR, nextC));
                if (topdown(grid, nextR, nextC)) return true;
            }
        }

        return false;
    }

    int[][] dirs = {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };

    List<Map<Integer, Set<Integer>>> nextMoves = List.of(
        Map.of(1, Set.of(1, 3, 5), 4, Set.of(1, 3, 5), 6, Set.of(1, 3, 5)),
        Map.of(1, Set.of(1, 4, 6), 3, Set.of(1, 4, 6), 5, Set.of(1, 4, 6)),
        Map.of(2, Set.of(2, 5, 6), 3, Set.of(2, 5, 6), 4, Set.of(2, 5, 6)),
        Map.of(2, Set.of(2, 3, 4), 5, Set.of(2, 3, 4), 6, Set.of(2, 3, 4))
    );

    public boolean mySol_miss(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int max = m * n;

        Map<Integer, List<Integer>> graph = new HashMap();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int index = i * m + j;

                switch (grid[i][j]) {
                    case 1 : add(graph, index, index - 1, index + 1, max); break;
                    case 2 : add(graph, index, index - m, index + m, max); break;
                    case 3 : add(graph, index, index - 1, index + m, max); break;
                    case 4 : add(graph, index, index + 1, index + m, max); break;
                    case 5 : add(graph, index, index - 1, index - m, max); break;
                    case 6 : add(graph, index, index + 1, index - m, max); break;
                }
            }
        }

        System.out.println(graph);

        return dfs(graph, 0, max - 1, new HashSet());
    }

    private void add(Map<Integer, List<Integer>> graph, int index, int next1, int next2, int max) {
        if (!graph.containsKey(index)) {
            graph.put(index, new ArrayList());
        }

        if (next1 >= 0 && next1 < max) {
            graph.get(index).add(next1);
        }

        if (next2 >= 0 && next2 < max) {
            graph.get(index).add(next2);
        }
    }

    public boolean dfs(Map<Integer, List<Integer>> graph, int node, int dest, Set<Integer> visited) {
        if (node == dest) return true;

        if (!visited.add(node)) return false;

        for (int next : graph.getOrDefault(node, new ArrayList<>())) {
            if (dfs(graph, next, dest, visited)) return true;
        }

        return false;
    }
}