class Solution {
    public int largestCombination(int[] candidates) {
        return mySol3(candidates);
    }

    public int mySol3(int[] arr) {
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            int count = 0;
            for (int num : arr) {
                if ((num & mask) > 0) count++;
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }

    public int mySol2_fail(int[] arr) {
        Map<Integer, Integer> map = new HashMap();

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int ans = 0;

        int and = (int)1e8 - 1;

        for (int num : arr) {
            int next = and & num;
            if (next > 0) {
                ans++;
                and = next;
            }
        }

        return ans;
    }

    public int mySol_fail(int[] arr) {
        return topdown(0, (int)1e8 - 1, arr, new Integer[arr.length]);
    }

    public int topdown(int index, int and, int[] arr, Integer[] memo) {
        if (index >= arr.length) return and == 0 ? Integer.MIN_VALUE : 0;

        if (memo[index] != null) {
            return memo[index];
        }

        int include = 1 + topdown(index + 1, and & arr[index], arr, memo);

        int exclude = topdown(index + 1, and, arr, memo);

        return memo[index] = Math.max(include, exclude);
    }
}