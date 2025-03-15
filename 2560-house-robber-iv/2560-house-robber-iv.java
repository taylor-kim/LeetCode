class Solution {
    public int minCapability(int[] nums, int k) {
        return official_bs_and_greedy(nums, k);
    }

    public int official_bs_and_greedy(int[] nums, int k) {
        int n = nums.length;
        int lo = 1;
        int hi = Arrays.stream(nums).max().getAsInt();

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int possible = 0;

            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) {
                    possible++;
                    i++;
                }
            }

            if (possible >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public int after_hint_fail(int[] nums, int k) {
        int lo = 1;
        int hi = (int)1e9 + 1;

        Integer[][] arr = new Integer[nums.length][2];

        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (isPossible(arr, k, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private boolean isPossible(Integer[][] arr, int k, int targetAmount) {
        int index = leftmost(arr, targetAmount + 1);

        if (index >= arr.length) return true;

        List<Integer> candidates = new ArrayList();

        for (int i = 0; i <= index; i++) {
            if (arr[i][1] < arr[index][1]) {
                candidates.add(arr[i][1]);
            }
        }

        if (candidates.size() < k) return false;

        Collections.sort(candidates);

        return isNotAdjacentK(candidates, -1, 0, k);
    }

    private boolean isNotAdjacentK(List<Integer> list, int prevNum, int index, int k) {
        if (k == 0) return true;

        if (index >= list.size()) return k <= 0;

        int num = list.get(index);

        boolean ans = false;

        if (prevNum + 1 < num) {
            ans |= isNotAdjacentK(list, num, index + 1, k - 1);
        }

        ans |= isNotAdjacentK(list, prevNum, index + 1, k);

        return ans;
    }

    private int leftmost(Integer[][] arr, int target) {
        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid][0] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public int mySol_tle(int[] nums, int k) {
        int n = nums.length;
        int[] maxFrom = new int[n];
        int max = 0;

        for (int i = n - 1; i >= 0; i--) {
            maxFrom[i] = max = Math.max(nums[i], max);
        }

        return topdown(nums, 0, k, maxFrom);
    }

    public int topdown(int[] nums, int index, int k, int[] maxFrom) {
        if (k == 0) return 0;

        if (index >= nums.length) return k == 0 ? 0 : Integer.MAX_VALUE;

        // if (memo[index][k] != null) {
        //     return memo[index][k];
        // }

        int include = nums[index];

        if (index + 2 < nums.length && nums[index] < maxFrom[index + 2]) {
            include = Math.max(include, topdown(nums, index + 2, k - 1, maxFrom));
        }

        int exclude = topdown(nums, index + 1, k, maxFrom);

        return Math.min(include, exclude);
        // return memo[index][k] = Math.min(include, exclude);
    }
}