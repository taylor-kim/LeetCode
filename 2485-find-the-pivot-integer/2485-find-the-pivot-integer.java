class Solution {
    public int pivotInteger(int n) {
        return official_math(n);
    }

    public int official_math(int n) {
        int sum = n * (1 + n) / 2;
        int pivot = (int)Math.sqrt(sum);

        return pivot * pivot == sum ? pivot : -1;
    }

    public int official_dp(int n) {
        int[] dp = new int[n + 1];

        if (dp[1] == 0) {
            for (int i = 1, j = 1; i <= n; i++) {
                int sum = i * (1 + i) / 2;

                while (j * j < sum) {
                    j++;
                }

                dp[i] = j * j == sum ? j : -1;
            }
        }

        return dp[n];
    }

    public int official_onepass(int n) {
        if (n == 1) return 1;

        int left = 1;
        int right = n;

        int leftSum = left;
        int rightSum = right;

        while (left < right) {
            if (leftSum < rightSum) {
                leftSum += ++left;
            } else {
                rightSum += --right;
            }

            if (leftSum == rightSum && left + 1 == right - 1) {
                return left + 1;
            }
        }

        return -1;
    }

    public int mySol_binarysearch(int n) {
        int left = 1;
        int right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int leftSum = (1 + mid) * mid / 2;
            int rightSum = (mid + n) * (n - mid + 1) / 2;

            if (leftSum == rightSum) {
                return mid;
            } else if (leftSum < rightSum) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public int mySol_onepass_fail(int n) {
        if (n == 1) return 1;
        if (n == 2) return -1;

        int left = 2;
        int right = n - 1;

        int leftSum = 1;
        int rightSum = n;

        while (left < right) {
            if (leftSum == rightSum) break;

            if (leftSum < rightSum) {
                leftSum += left++;
            } else {
                rightSum += right--;
            }
        }

        return  leftSum == rightSum ? (left == right ? left : (left + 1 == right - 1 ? left + 1 : -1)) : -1;
    }

    public int mySol(int n) {
         int[] pSum = new int[n + 2];

         for (int i = 1; i <= n; i++) {
            pSum[i] = i + pSum[i - 1];
         }

         for (int i = 1; i <= n; i++) {
            int leftSum = pSum[i];
            int rightSum = pSum[n] - pSum[i - 1];

            if (leftSum == rightSum) return i;
         }

         return -1;
    }
}