class Solution {
    public int maxJumps(int[] arr, int d) {
        return editorial(arr, d);
    }

    public int editorial(int[] arr, int d) {
        int n = arr.length;
        Integer[] memo = new Integer[n];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, topdown(arr, d, i, memo));
        }

        return ans;
    }

    private int topdown(int[] arr, int d, int index, Integer[] memo) {
        if (memo[index] != null) return memo[index];

        int ans = 1;

        for (int j = index - 1; j >= Math.max(0, index - d) && arr[j] < arr[index]; j--) {
            ans = Math.max(ans, 1 + topdown(arr, d, j, memo));
        }

        for (int j = index + 1; j <= Math.min(index + d, arr.length - 1) && arr[index] > arr[j]; j++) {
            ans = Math.max(ans, 1 + topdown(arr, d, j, memo));
        }

        return memo[index] = ans;
    }

    public int mySol_fail(int[] arr, int d) {
        int n = arr.length;
        int ans = 0;
        Integer[] memo = new Integer[arr.length];
        int[] toRight = new int[n];
        int[] toLeft = new int[n];

        for (int i = 0; i < n; i++) {
            toRight[i] = i;
            for (int j = i + 1; j < Math.min(n, i + d + 1) && arr[j - 1] > arr[j]; j++) {
                toRight[i] = j;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            toLeft[i] = i;
            for (int j = i - 1; j >= Math.max(0, i - d + 1) && arr[j] < arr[j + 1]; j--) {
                toLeft[i] = j;
            }
        }

        Set<Integer> visit = new HashSet();

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, topdown(arr, d, i, toRight, toLeft, memo, visit));
        }

        return ans;
    }

    public int topdown(int[] arr, int d, int index, int[] toRight, int[] toLeft, Integer[] memo, Set<Integer> visit) {
        if (index < 0 || index >= arr.length) return 0;

        if (memo[index] != null) return memo[index];

        if (!visit.add(index)) return -1;

        int ans = 1;

        for (int j = toLeft[index]; j <= toRight[index]; j++) {
            if (index == j) continue;

            int sub = topdown(arr, d, j, toRight, toLeft, memo, visit);

            if (sub >= 0) {
                ans = Math.max(ans, 1 + sub);
            }
        }

        visit.remove(index);

        // for (int j = Math.max(index - d, 0); j <= Math.min(index + d, arr.length - 1); j++) {
        //     if (arr[j] < arr[index]) {
        //         ans = Math.max(ans, 1 + topdown(arr, d, j, memo));
        //     }
        // }

        return memo[index] = ans;
    }
}