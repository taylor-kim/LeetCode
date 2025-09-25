class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        return recursive(triangle);
    }

    public int recursive(List<List<Integer>> triangle) {
        int leng = triangle.size();
        int sumOfItems = leng * (1 + leng) / 2;

        int[][] memo = new int[leng][sumOfItems];

        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return recursive(triangle, 0, 0, memo);
    }

    public int recursive(List<List<Integer>> triangle, int level, int index, int[][] memo) {
        if (level >= triangle.size()) {
            return 0;
        }

        List<Integer> items = triangle.get(level);

        if (index >= items.size()) {
            return 0;
        }

        if (memo[level][index] != -1) {
            return memo[level][index];
        }

        int a = recursive(triangle, level + 1, index, memo);
        int b = recursive(triangle, level + 1, index + 1, memo);

        return memo[level][index] = items.get(index) + Math.min(a, b);
    }
}