class Solution {
    public boolean containsCycle(char[][] grid) {
        return mySol(grid);
    }

    public boolean mySol(char[][] grid) {
        Set<Integer> visited = new HashSet();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited.contains(i * grid[0].length + j)) continue;

                if (hasCycle(grid, i, j, i, j, -1, -1, 0, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasCycle(char[][] grid, int startRow, int startCol, int row, int col, int prevRow, int prevCol, int depth,
                            Set<Integer> visited) {

        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return false;

        if (grid[startRow][startCol] != grid[row][col]) return false;

        // if (depth > 0 && startRow == row && startCol == col) {
        //     return true;
        // }

        if (!visited.add(row * grid[0].length + col)) return true;

        int[][] dirs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
        };

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            
            if (nextRow == prevRow && nextCol == prevCol) continue;

            if (hasCycle(grid, startRow, startCol, nextRow, nextCol, row, col, depth + 1, visited)) {
                return true;
            }
        }

        return false;
    }
}