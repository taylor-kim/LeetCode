class Solution {
    public int totalNumbers(int[] digits) {
        return mySol(digits);
    }

    public int mySol(int[] digits) {
        Set<Integer> set = new HashSet();

        topdown(digits, new boolean[digits.length], set, 0);

        return set.size();
    }

    private void topdown(int[] digits, boolean[] visit, Set<Integer> set, int number) {
        if (number >= 100 && number < 1000) {
            if (number % 2 == 0) set.add(number);

            return;
        }

        for (int i = 0; i < digits.length; i++) {
            if (visit[i]) continue;

            if (i > 0 && digits[i - 1] == digits[i] && !visit[i - 1]) continue;

            visit[i] = true;

            topdown(digits, visit, set, number * 10 + digits[i]);

            visit[i] = false;
        }
    }
}