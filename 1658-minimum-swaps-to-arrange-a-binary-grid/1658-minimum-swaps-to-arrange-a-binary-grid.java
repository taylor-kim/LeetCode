class Solution {
    public int minSwaps(int[][] grid) {
        return mySol3(grid);
    }

    public int mySol3(int[][] grid) {
        int n = grid.length;
        List<Integer> zeros = new ArrayList();

        for (int i = 0; i < n; i++) {
            int z = 0;
            for (int j = n - 1; j >= 0 && grid[i][j] == 0; j--) {
                z++;
            }
            zeros.add(z);
        }

        Collections.sort(zeros, Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            if (zeros.get(i) < n - i - 1) return -1;
        }

        return count(grid);
    }

    private int count(int[][] grid) {
        int n = grid.length;
        List<Integer> zeros = new ArrayList();

        for (int i = 0; i < n; i++) {
            int z = 0;
            for (int j = n - 1; j >= 0 && grid[i][j] == 0; j--) {
                z++;
            }
            zeros.add(z);
        }

        int ans = 0;

        // [5, 4, 3, 2, 1, 0]
        // [5, 2, 2, 4, 3, 0]
        // [5, 4, 2, 2, 3, 0]

        int needZero = n - 1;
        int index = 0;

        while (index < zeros.size()) {
            if (zeros.get(index) >= needZero) {
                index++;
            } else {
                int j = index + 1;
                while (j < n && zeros.get(j) < needZero) {
                    j++;
                }

                if (j < n) {
                    ans += j - index;
                    zeros.remove(j);
                }
            }

            needZero--;
        }

        return ans;
    }

    private boolean isValid(List<Integer> list) {
        int n = list.size();

        for (int i = 0; i < n; i++) {
            int zeroCount = list.get(i);

            if (zeroCount < n - i - 1) return false;
        }

        return true;
    }
}