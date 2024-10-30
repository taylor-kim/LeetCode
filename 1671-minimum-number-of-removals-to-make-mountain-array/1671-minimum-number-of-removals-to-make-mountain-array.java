class Solution {
    public int minimumMountainRemovals(int[] nums) {
        return try_lis_by_bs(nums);
    }

    public int try_lis_by_bs(int[] nums) {
        int n = nums.length;

        List<Integer> list = new ArrayList();

        for (int num : nums) list.add(num);

        List<Integer> dp = lis_official(list);
        Collections.reverse(list);
        List<Integer> dp2 = lis_official(list);
        Collections.reverse(dp2);

        // System.out.println(dp);
        // System.out.println(dp2);

        int ans = n - 3;

        for (int i = 1; i < n - 1; i++) {
            if (dp.get(i) == 1 || dp2.get(i) == 1) continue;

            int removeLeft = i + 1 - dp.get(i);
            int removeRight = n - i - dp2.get(i);

            ans = Math.min(ans, removeLeft + removeRight);
        }

        return ans;
    }

    private List<Integer> lis(List<Integer> nums) {
        List<Integer> list = new ArrayList();
        List<Integer> lis = new ArrayList();

        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            int index = Collections.binarySearch(list, num);

            if (index < 0) {
                index = -(index + 1);
            }

            if (index == list.size()) {
                list.add(num);
            } else {
                list.set(index, num);
            }

            lis.add(index + 1);
        }

        return lis;
    }

    private List<Integer> lis_official(List<Integer> nums) {
        List<Integer> lisLen = new ArrayList(
            Collections.nCopies(nums.size(), 1)
        );
        List<Integer> lis = new ArrayList();
        lis.add(nums.get(0));

        for (int i = 1; i < nums.size(); i++) {
            int num = nums.get(i);
            int index = Collections.binarySearch(lis, num);

            if (index < 0) {
                index = -(index + 1);
            }

            if (index == lis.size()) {
                lis.add(num);
            } else {
                lis.set(index, num);
            }

            lisLen.set(i, lis.size());
        }

        return lisLen;
    }

    public int mySol2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] dp2 = new int[n];

        int ans = n - 3;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            dp2[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (nums[j] < nums[i]) {
                    dp2[i] = Math.max(dp2[i], dp2[j] + 1);
                }
            }
        }

        // System.out.println(Arrays.toString(dp));
        // System.out.println(Arrays.toString(dp2));

        for (int i = 1; i < n - 1; i++) {
            if (dp[i] == 1 || dp2[i] == 1) continue;

            int removeLeft = i + 1 - dp[i];
            int removeRight = n - i - dp2[i];

            ans = Math.min(ans, removeLeft + removeRight);
        }

        return ans;
    }

    public int mySol_fail(int[] nums) {
        int highest = 1;

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[highest]) {
                highest = i;
            }
        }

        // System.out.println(String.format("highest:%d", highest));

        int ans = nums.length;

        int[] dp = new int[nums.length];

        for (int i = 0; i <= highest; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }

        // System.out.println(Arrays.toString(dp));

        ans = (highest + 1 - dp[highest]);

        for (int i = highest; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = highest; j < i; j++) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }

        // System.out.println(Arrays.toString(dp));

        return ans + (nums.length - highest - dp[nums.length - 1]);
    }
}