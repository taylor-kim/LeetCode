class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        return mySol2(nums, k);
    }

    public int[] mySol2(int[] nums, int k) {
        int n = nums.length;

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        int[][] arr2 = new int[k][2];

        for (int i = n - k, j = 0; i < n; i++, j++) {
            arr2[j][0] = arr[i][0];
            arr2[j][1] = arr[i][1];
        }

        Arrays.sort(arr2, (a, b) -> {
            return a[1] - b[1];
        });

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            ans[i] = arr2[i][0];
        }

        return ans;
    }

    int max = Integer.MIN_VALUE;
    List<Integer> ans = new ArrayList();

    public int[] mySol_fail(int[] nums, int k) {
        topdown(0, k, nums, new ArrayList(), 0, new boolean[nums.length][k + 1]);

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private void topdown(int index, int k, int[] nums, List<Integer> list, int sum, boolean[][] visit) {
        if (k == 0) {
            if (max < sum) {
                max = sum;
                ans = new ArrayList(list);
            }

            return;
        }

        if (index >= nums.length) {
            return;
        }

        if (visit[index][k]) return;

        visit[index][k] = true;

        list.add(nums[index]);

        topdown(index + 1, k - 1, nums, list, sum + nums[index], visit);

        list.remove(list.size() - 1);

        topdown(index + 1, k, nums, list, sum, visit);
    }
}