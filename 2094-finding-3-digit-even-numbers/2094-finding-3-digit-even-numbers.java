class Solution {
    public int[] findEvenNumbers(int[] digits) {
        return mySol(digits);
    }

    public int[] mySol(int[] digits) {
        Arrays.sort(digits);

        Set<Integer> ans = new LinkedHashSet();

        int[] counter = new int[10];

        for (int digit : digits) {
            counter[digit]++;
        }

        // System.out.println(Arrays.toString(digits));

        backtrack(digits, 0, counter, new int[10], ans);

        return ans.stream().mapToInt(i -> i).toArray();
    }

    private void backtrack(int[] digits, int number, int[] counter, int[] used, Set<Integer> set) {
        if (number / 1000 == 0 && number / 100 > 0) {
            if (number % 2 == 0) {
                set.add(number);
            }
            return;
        }

        for (int i = 0; i < digits.length; i++) {
            if (number == 0 && digits[i] == 0) continue;

            int d = digits[i];

            // if (used[d] == counter[d] || (i > 0 && digits[i - 1] == digits[i] && used[d] == 0)) continue;

            if (used[d] == counter[d]) {
                // System.out.println(String.format("used[%d] == counter[%d]", d, d));
                continue;
            }

            if (i > 0 && digits[i - 1] == digits[i] && used[d] == 0) {
                // System.out.println(String.format("i:%d, d:%d", i, d));
                continue;
            }

            used[d]++;

            backtrack(digits, number * 10 + d, counter, used, set);

            used[d]--;
        }
    }
}