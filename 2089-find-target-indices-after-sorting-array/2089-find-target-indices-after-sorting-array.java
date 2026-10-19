class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        return mySol(nums, target);
    }

    public List<Integer> mySol(int[] nums, int target) {
        Arrays.sort(nums);

        int left = leftmost(nums, target);
        int right = leftmost(nums, target + 1) - 1;

        List<Integer> ans = new ArrayList();

        for (int i = left; i <= right; i++) {
            ans.add(i);
        }

        return ans;
    }

    private int leftmost(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }
}