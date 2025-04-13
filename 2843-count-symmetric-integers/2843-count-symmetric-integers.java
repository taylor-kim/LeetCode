class Solution {
    public int countSymmetricIntegers(int low, int high) {
        return others_digit_dp(low, high);
    }

    public int others_digit_dp(int low, int high) {
        return countUpTo(high) - countUpTo(low - 1);
    }

    private int countUpTo(int num) {
        String s = String.valueOf(num);
        int n = s.length();

        int firstHalfSum = n;
        int diffFromLeft = 9 * n;

        Integer[][][][][][] dp = new Integer[n + 1][2][2][2][2 * n + 1][18 * n + 1];

        return countSymmetric(s, 0, n, 1, 0, 0, 0, 0, firstHalfSum, diffFromLeft, dp);
    }

    public int countSymmetric(String s, int index, int n, int tight
                        , int hasNonZero, int isSecondHalf, int rem, int diff
                        , int firstHalfSum, int diffFromLeft, Integer[][][][][][] dp) {
        if (index == n) {
            return (hasNonZero == 1 && diff == 0 && rem == 0) ? 1 : 0;
        }

        if (dp[index][tight][hasNonZero][isSecondHalf][rem + firstHalfSum][diff + diffFromLeft] != null) {
            return dp[index][tight][hasNonZero][isSecondHalf][rem + firstHalfSum][diff + diffFromLeft];
        }

        int maxDigit = tight == 1 ? s.charAt(index) - '0' : 9;
        int result = 0;

        for (int digit = 0; digit <= maxDigit; digit++) {
            int newHasNonZero = hasNonZero == 1 || digit != 0 ? 1 : 0;

            int newRem = rem;
            int newDiff = diff;

            if (isSecondHalf == 1) {
                newDiff -= digit;

                if (newHasNonZero == 1) {
                    newRem -= 1;
                }
            } else {
                newDiff += digit;

                if (newHasNonZero == 1) {
                    newRem += 1;
                }
            }

            int newTight = (tight == 1 && digit == maxDigit) ? 1 : 0;

            result += countSymmetric(s, index + 1, n, newTight, newHasNonZero
                                    , isSecondHalf, newRem, newDiff
                                    , firstHalfSum, diffFromLeft, dp);

            if (newHasNonZero == 1 && isSecondHalf == 0) {
                result += countSymmetric(s, index + 1, n, newTight, newHasNonZero
                        , 1, newRem, newDiff
                        , firstHalfSum, diffFromLeft, dp);
            }
        }

        return dp[index][tight][hasNonZero][isSecondHalf][rem + firstHalfSum][diff + diffFromLeft] = result;
    }

    public int official(int low, int high) {
        int ans = 0;

        for (int num = low; num <= high; num++) {
            if (num < 100 && num % 11 == 0) {
                ans++;
            } else if (num >= 1001 && num < 10000) {
                int left = num / 1000 + ((num / 100) % 10);
                int right = (num % 100) / 10 + (num % 10);

                if (left == right) ans++;
            }
        }

        return ans;
    }

    public int mySol(int low, int high) {
        int ans = 0;

        for (int num = low; num <= high; num++) {
            if (isSym(num)) ans++;
        }

        return ans;
    }

    private boolean isSym(int num) {
        char[] arr = String.valueOf(num).toCharArray();

        int n = arr.length;

        if (n % 2 == 1) return false;

        int sum = 0;
        int half = n / 2;

        for (int i = 0; i < half; i++) {
            sum += arr[i];
            sum -= arr[n - i - 1];
        }

        return sum == 0;
    }
}