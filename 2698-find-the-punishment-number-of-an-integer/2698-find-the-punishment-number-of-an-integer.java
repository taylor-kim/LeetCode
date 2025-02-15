class Solution {
    public int punishmentNumber(int n) {
        return mySol(n);
    }

    public int mySol(int n) {
        int ans = 0;

        // Boolean[][] memo = new Boolean[n * n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            if (partition(String.valueOf(i * i), 0, i)) {
                ans += i * i;
            }
        }

        return ans;
    }

    private boolean partition2_mle(int square, int target, Boolean[][] memo) {
        if (target < 0) return false;

        if (square == 0) return target == 0;

        if (memo[square][target] != null) {
            return memo[square][target];
        }
        
        int sum = 0;
        int number = square;
        int pos = 1;
        boolean ans = false;

        while (number > 0) {
            sum += (number % 10) * pos;
            number /= 10;
            pos *= 10;

            if ((ans = partition2_mle(number, target - sum, memo))) {
                break;
            }
        }

        return memo[square][target] = ans;
    }

    private boolean partition(String s, int index, int target) {
        if (target < 0) return false;

        if (index >= s.length()) return target == 0;

        int number = 0;

        for (int i = index; i < s.length(); i++) {
            number = number * 10 + (s.charAt(i) - '0');

            if (partition(s, i + 1, target - number)) {
                return true;
            }
        }

        return false;
    }
}