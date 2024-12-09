class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        return mySol3(nums, queries);
    }

    public boolean[] mySol3(int[] nums, int[][] queries) {
        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            ans[i] = isSpecial(nums, query[0], query[1]);
        }

        return ans;
    }

    private boolean isSpecial(int[] nums, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            if (nums[i - 1] % 2 == nums[i] % 2) return false;
        }

        return true;
    }

    private boolean isSpecial_fail(int[] nums, int start, int end) {
        int diff = (end - start + 1) % 2 == 1 ? 0 : 1;

        while (start <= end) {
            if (Math.abs((nums[start] % 2) - (nums[end] % 2)) != diff) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    public boolean[] mySol2_fail(int[] nums, int[][] queries) {
        int n = nums.length;
        Set<int[]> invalids = new HashSet();

        int left = 0;
        int right = 0;

        while (right < n) {
            int start = right;
            while (right + 1 < n && nums[right] % 2 == nums[right + 1] % 2) {
                right++;
            }

            if (start == right) {
                right++;
            } else {
                invalids.add(new int[] {start, right++});
            }
        }

        // for (int[] invalid : invalids) {
        //     System.out.println(Arrays.toString(invalid));
        // }

        boolean[] ans = new boolean[queries.length];
        Arrays.fill(ans, true);

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            for (int[] invalid : invalids) {
                if (hasIntersect(query, invalid)) {
                    // System.out.println(
                    //     String.format("a:%s and b:%s => have an intersect"
                    //         , Arrays.toString(query)
                    //         , Arrays.toString(invalid)
                    //     )
                    // );
                    ans[i] = false;
                    break;
                }
            }
        }

        return ans;
    }

    private boolean hasIntersect(int[] a, int[] b) {
        return !(a[1] <= b[0] || b[1] <= a[0]);
    }

    public boolean[] mySol_mle(int[] nums, int[][] queries) {
        boolean[] ans = new boolean[queries.length];

        boolean dp[][] = new boolean[nums.length][nums.length];

        for (int i = 0; i < nums.length; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < nums.length; i++) {
            setDp(nums, dp, i, i);
            setDp(nums, dp, i, i + 1);
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            ans[i] = dp[query[0]][query[1]];
        }

        return ans;
    }

    private void setDp(int[] nums, boolean dp[][], int left, int right) {
        while (left <= right && left >= 0 && right < nums.length) {
            // if (left + 1 <= right - 1 && (right - left <= 1 || dp[left + 1][right - 1])) {
            if (left + 1 < nums.length && right - 1 >= 0 && (right - left <= 1 || dp[left + 1][right - 1])) {
                if (nums[left] % 2 != nums[left + 1] % 2 && nums[right - 1] % 2 != nums[right] % 2) {
                    dp[left][right] = true;
                }
            }

            left--;
            right++;
        }
    }
}