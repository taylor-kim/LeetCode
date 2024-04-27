class Solution {
    // Initialize a hash map to cache the result of each sub-problem
    Map<Pair<Integer, Integer>, Integer> memo = new HashMap<>();

    public int minFallingPathSum(int[][] grid) {
        // We can select any element from the first row. We will select
        // the element which leads to minimum sum.
        int answer = Integer.MAX_VALUE;
        for (int col = 0; col < grid.length; col++) {
            answer = Math.min(answer, optimal(0, col, grid));
        }

        // Return the minimum sum
        return answer;
    }

    // The optimal(row, col) function returns the minimum sum of a
    // falling path with non-zero shifts, starting from grid[row][col]
    int optimal(int row, int col, int[][] grid) {
        // If the last row, then return the value of the cell itself
        if (row == grid.length - 1) {
            return grid[row][col];
        }

        // If the result of this sub-problem is already cached
        if (memo.containsKey(new Pair<>(row, col))) {
            return memo.get(new Pair<>(row, col));
        }

        // Select grid[row][col], and move on to next row. For next
        // row, choose the cell that leads to the minimum sum
        int nextMinimum = Integer.MAX_VALUE;
        for (int nextRowCol = 0; nextRowCol < grid.length; nextRowCol++) {
            if (nextRowCol != col) {
                nextMinimum = Math.min(nextMinimum, optimal(row + 1, nextRowCol, grid));
            }
        }

        // Minimum cost from this cell
        memo.put(new Pair<>(row, col), grid[row][col] + nextMinimum);
        return memo.get(new Pair<>(row, col));
    }
}