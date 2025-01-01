class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        return mySol(nums);
    }

    public int[] mySol(List<List<Integer>> nums) {
        int n = nums.size();
        List<Integer> list = new ArrayList();

        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            add(i, 0, nums, list);
            maxLength = Math.max(maxLength, nums.get(i).size());
        }

        // for (int j = 1; j < nums.get(n - 1).size(); j++) {
        for (int j = 1; j < maxLength; j++) {
            add(n - 1, j, nums, list);
        }

        int[] ans = new int[list.size()];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private void add(int row, int col, List<List<Integer>> nums, List<Integer> ans) {
        while (row >= 0) {
            if (col < nums.get(row).size()) {
                ans.add(nums.get(row).get(col));
            }
            col++;
            row--;
        }
    }

    /**
    [14,12,19,16,9]
    [13,14,15,8,11]
    [11,13,1]
     */
}