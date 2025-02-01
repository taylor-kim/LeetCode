class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        return official_binarySearch(nums, queries);
    }

    public boolean[] try_20250201(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] pSum = new int[n];

        for (int i = 1; i < n; i++) {
            int diff = nums[i] % 2 == nums[i - 1] % 2 ? 1 : 0;

            pSum[i] = pSum[i - 1] + diff;
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = pSum[queries[i][0]] == pSum[queries[i][1]];
        }

        return ans;
    }

    public boolean[] official_sliding_window(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] maxReach = new int[n];
        int end = 0;

        for (int start = 0; start < n; start++) {
            end = Math.max(end, start);

            while (end + 1 < n && nums[end] % 2 != nums[end + 1] % 2) {
                end++;
            }

            maxReach[start] = end;
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < ans.length; i++) {
            int[] query = queries[i];
            ans[i] = query[1] <= maxReach[query[0]];
        }

        return ans;
    }

    public boolean[] official_pSum(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] pSum = new int[n];

        for (int i = 1; i < n; i++) {
            if (nums[i - 1] % 2 == nums[i] % 2) {
                pSum[i] = pSum[i - 1] + 1;
            } else {
                pSum[i] = pSum[i - 1];
            }
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            ans[i] = pSum[query[1]] - pSum[query[0]] == 0;
        }

        return ans;
    }

    public boolean[] official_binarySearch(int[] nums, int[][] queries) {
        List<Integer> invalidIndices = new ArrayList();

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] % 2 == nums[i + 1] % 2) {
                invalidIndices.add(i);
            }
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            ans[i] = !isInvalid2(invalidIndices, query[0], query[1] - 1);
        }

        return ans;
    }

    private boolean isInvalid3(List<Integer> list, int start, int end) {
        int lo = 0; 
        int hi = list.size() - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int index = list.get(mid);

            // System.out.println(String.format("index:%d, lo:%d, hi:%d, mid:%d, start:%d, end:%d", index, lo, hi, mid, start, end));

            if (index < start) {
                lo = mid + 1;
            } else if (index > end) {
                hi = mid - 1;
            } else {
                // System.out.println(String.format("index:%d, lo:%d, hi:%d", index, lo, hi));
                return true;
            }

            // System.out.println(String.format("lo:%d, hi:%d", lo, hi));
        }

        return false;
    }

    private boolean isInvalid2(List<Integer> list, int start, int end) {
        int index1 = Collections.binarySearch(list, start);

        if (index1 < 0) index1 = -(index1 + 1);

        if (index1 >= list.size()) return false;

        if (list.get(index1) > end) return false;

        return true;
    }

    private boolean isInvalid(List<Integer> list, int start, int end) {
        int lo = 0;
        int hi = list.size() - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int index = list.get(mid);

            if (index < start) {
                lo = mid + 1;
            } else if (index > end) {
                hi = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }

    public boolean[] mySol3_tle(int[] nums, int[][] queries) {
        boolean[] ans = new boolean[queries.length];
        Set<int[]> valids = new HashSet();
        Set<int[]> invalids = new HashSet();

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            ans[i] = isSpecial(nums, query[0], query[1], valids, invalids);

            if (ans[i]) {
                valids.add(query);
            } else {
                invalids.add(query);
            }
        }

        return ans;
    }

    private boolean isSpecial(int[] nums, int start, int end, Set<int[]> valids, Set<int[]> invalids) {
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
        Set<List<Integer>> invalids = new HashSet();

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
                invalids.add(List.of(start, right++));
            }
        }

        // for (int[] invalid : invalids) {
        //     System.out.println(Arrays.toString(invalid));
        // }

        boolean[] ans = new boolean[queries.length];
        Arrays.fill(ans, true);

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            for (List<Integer> invalid : invalids) {
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

    private boolean hasIntersect(int[] a, List<Integer> b) {
        return !(a[1] <= b.get(0) || b.get(1) <= a[0]);
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