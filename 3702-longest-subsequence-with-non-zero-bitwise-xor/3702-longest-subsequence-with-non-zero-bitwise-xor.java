class Solution {
    public int longestSubsequence(int[] nums) {
        return mySol5(nums);
    }

    public int mySol5(int[] nums) {
        int n = nums.length;
        int xor = 0;

        boolean hasValue = false;

        for (int i = 0; i < n; i++) {
            xor ^= nums[i];

            if (xor != 0) {
                hasValue = true;
            }
        }

        if (hasValue && xor == 0) {
            return n - 1;
        }

        return hasValue ? n : 0;
    }

    public int mySol4_fail(int[] nums) {
        Map<Integer, Integer> map = new HashMap();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int evenCount = 0;

        for (int count : map.values()) {
            if (count % 2 == 0) {
                evenCount++;
            }
        }

        if (evenCount > 0) {
            return nums.length - 1;
        }

        return nums.length;
    }

    public int mySol3_fail(int[] nums) {
        int[] ans = new int[1];
        topdown(nums, 0, 0, 0, ans);
        return ans[0];
    }

    private void topdown(int[] nums, int index, int xor, int length, int[] ans) {
        if (index >= nums.length) {
            if (xor != 0) {
                ans[0] = Math.max(ans[0], length);
            }

            return;
        }

        if (xor != 0) {
            ans[0] = Math.max(ans[0], length);
        }

        topdown(nums, index + 1, xor ^ nums[index], length + 1, ans);
        topdown(nums, index + 1, xor, length, ans);
    }

    public int mySol2_fail(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int xor = 0;

        for (int i = 0; i < n; i++) {
            xor ^= nums[i];

            if (xor != 0) {
                ans = i + 1;
            }

            System.out.println("i:%d, xor:%d".formatted(i, xor));
        }

        return ans;
    }

    public int mySol_fail(int[] nums) {
        int n = nums.length;
        int ans = 1;
        int[] dp = new int[n];
        int[] xor = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            xor[i] = nums[i];
            for (int j = 0; j < i; j++) {
                if ((xor[j] ^ nums[i]) != 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n - 1];
    }
}