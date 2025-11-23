class Solution {
    public int maxSumDivThree(int[] nums) {
        return editorial(nums);
    }

    public int editorial(int[] nums) {
        // Use v[0], v[1], v[2] to represent a, b, c respectively.
        List<Integer>[] v = new List[3];
        for (int i = 0; i < 3; ++i) {
            v[i] = new ArrayList<Integer>();
        }
        for (int num : nums) {
            v[num % 3].add(num);
        }
        Collections.sort(v[1], (a, b) -> b - a);
        Collections.sort(v[2], (a, b) -> b - a);

        int ans = 0;
        int lb = v[1].size();
        int lc = v[2].size();
        for (int cntb = lb - 2; cntb <= lb; ++cntb) {
            if (cntb >= 0) {
                for (int cntc = lc - 2; cntc <= lc; ++cntc) {
                    if (cntc >= 0 && (cntb - cntc) % 3 == 0) {
                        ans = Math.max(
                            ans,
                            getSum(v[1], 0, cntb) + getSum(v[2], 0, cntc)
                        );
                    }
                }
            }
        }
        return ans + getSum(v[0], 0, v[0].size());
    }

    public int getSum(List<Integer> list, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; ++i) {
            sum += list.get(i);
        }
        return sum;
    }

    public int mySol_fail(int[] nums) {
        return topdown(nums, 0, 0, 0, new Integer[nums.length][4]);
    }

    public int topdown(int[] nums, int index, int mod, int sum, Integer[][] memo) {
        if (index >= nums.length) {
            return mod % 3 == 0 ? sum : 0;
        }

        if (memo[index][mod] != null) return memo[index][mod];

        int include = topdown(nums, index + 1, (mod + nums[index]) % 3, sum + nums[index], memo);
        int exclude = topdown(nums, index + 1, mod, sum, memo);

        return memo[index][mod] = Math.max(include, exclude);
    }
}