class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        return bf(grid);
    }

    public int[] bf(int[][] grid) {
		int n = grid.length;
		int total = n * n;
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 1; i <= total; i++) {
			map.put(i, 0);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int num = grid[i][j];
				map.put(num, map.get(num) + 1);
			}
		}

		int[] ans = {-1, -1};

		for (int num : map.keySet()) {
			if (map.get(num) == 2) {
				ans[0] = num;
			} else if (map.get(num) == 0) {
				ans[1] = num;
			}
		}

		return ans;
	}

    public int[] mySol(int[][] grid) {
		int n = grid.length;
		int total = n * n;

		int dup = -1;
		int missing = -1;

		for (int i = 0; i < total; i++) {
			int y = i / n;
			int x = i % n;

			int expectedValue = i + 1;

			if (grid[y][x] == expectedValue) {
				continue;
			} else {
				change(grid, y, x, expectedValue);
			}
		}

		return null;
	}

	private void change(int[][] grid, int y, int x, int newValue) {
		int oldValue = grid[y][x];

		if (oldValue == newValue) return;

		grid[y][x] = newValue;

		int newIndex = oldValue - 1;

		int ny = newIndex / grid.length;
		int nx = newIndex % grid.length;

		change(grid, ny, nx, oldValue);
	}
}