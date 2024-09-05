class Solution {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        return mySol(rolls, mean, n);
    }

    public int[] mySol(int[] rolls, int mean, int n) {
        int m = rolls.length;

        int sum = 0;

        for (int num : rolls) sum += num;

        int targetSum = mean * (m + n);

        int missingSum = targetSum - sum;

        System.out.println(String.format("m:%d, n:%d, sum:%d, targetSum:%d, missingSum:%d", m, n, sum, targetSum, missingSum));

        if (missingSum < n || missingSum > n * 6) {
            return new int[] {};
        }

        int[] ans = new int[n];

        int atLeast = missingSum / n;
        int lastExtra = missingSum % n;
        int addable = 6 - atLeast;

        Arrays.fill(ans, atLeast);

        int index = 0;

        while (lastExtra > 0) {
            int add = Math.min(addable, lastExtra);
            ans[index++] += add;

            lastExtra -= add;
        }

        return ans;
    }
}