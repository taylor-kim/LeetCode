class Solution {
    public int maxAbsoluteSum(int[] nums) {
        return official2(nums);
    }

    public int official2(int[] nums) {
        int minPrefixSum = 0, maxPrefixSum = 0;
        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            minPrefixSum = Math.min(minPrefixSum, prefixSum);
            maxPrefixSum = Math.max(maxPrefixSum, prefixSum);
        }

        return maxPrefixSum - minPrefixSum;
    }

    public int official1(int[] nums) {
        int minPrefixSum = Integer.MAX_VALUE, maxPrefixSum = Integer.MIN_VALUE;
        int prefixSum = 0, maxAbsSum = 0;

        for (int i = 0; i < nums.length; i++) {
            // Prefix sum from index 0 to i
            prefixSum += nums[i];

            // Minimum & Maximum prefix sum we have seen so far
            minPrefixSum = Math.min(minPrefixSum, prefixSum);
            maxPrefixSum = Math.max(maxPrefixSum, prefixSum);

            if (prefixSum >= 0) {
                // If the prefixSum is positive, we will get the difference between prefixSum &
                // minPrefixSum
                maxAbsSum = Math.max(
                    maxAbsSum,
                    Math.max(prefixSum, prefixSum - minPrefixSum)
                );
            } else if (prefixSum <= 0) {
                // If the prefixSum is negative, we will get the absolute difference between
                // prefixSum & maxPrefixSum
                maxAbsSum = Math.max(
                    maxAbsSum,
                    Math.max(
                        Math.abs(prefixSum),
                        Math.abs(prefixSum - maxPrefixSum)
                    )
                );
            }
        }

        return maxAbsSum;
    }

    public int mySol2_fail(int[] nums) {
        int n = nums.length;

        int[] pSum = new int[n + 1];
        int[] negSum = new int[n + 1];
        int[] posSum = new int[n + 1];

        int ans = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            pSum[i + 1] = pSum[i] + num;
            negSum[i + 1] = negSum[i];
            posSum[i + 1] = posSum[i];
            
            if (num < 0) {
                negSum[i + 1] += num;
            } else {
                posSum[i + 1] += num;
            }
        }

        System.out.println(Arrays.toString(negSum));
        System.out.println(Arrays.toString(posSum));
        System.out.println(Arrays.toString(pSum));

        return ans;
    }


    public int mySol_fail(int[] nums) {
        int ans = 0;
        int maxSum = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            maxSum += num;

            if (Math.abs(maxSum) < Math.abs(num)) {
                maxSum = num;
            }

            ans = Math.max(ans, Math.abs(maxSum));
        }

        return ans;
    }
}