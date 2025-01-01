class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        return gpt(nums);
    }

    public int[] gpt(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> map = new HashMap();
        int n = 0;

        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                map.computeIfAbsent(i + j, k -> new ArrayList()).add(nums.get(i).get(j));
                n++;
            }
        }

        int[] ans = new int[n];
        int index = 0;

        for (int key = 0; map.containsKey(key); key++) {
            List<Integer> diagonal = map.get(key);

            for (int i = diagonal.size() - 1; i >= 0; i--) {
                ans[index++] = diagonal.get(i);
            }
        }

        return ans;
    }

    public int[] mySol_fail(List<List<Integer>> nums) {
        int n = nums.size();
        List<Integer> list = new ArrayList();

        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            add(i, 0, nums, list, i + 1);
            maxLength = Math.max(maxLength, nums.get(i).size());
        }

        // for (int j = 1; j < nums.get(n - 1).size(); j++) {
        for (int j = 1; j < maxLength; j++) {
            add(n - 1, j, nums, list, maxLength);
        }

        int[] ans = new int[list.size()];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private void add(int row, int col, List<List<Integer>> nums, List<Integer> ans, int max) {
        while (row >= 0 && col <= max) {
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