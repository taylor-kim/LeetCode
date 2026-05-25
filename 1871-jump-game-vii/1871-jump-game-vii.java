class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        return others_bfs(s, minJump, maxJump);
    }

    public boolean others_bfs(String s, int minJump, int maxJump) {
        int n = s.length();

        if (s.charAt(n - 1) == '1') return false;

        Queue<Integer> queue = new LinkedList();
        queue.add(0);

        Set<Integer> visit = new HashSet();
        visit.add(0);
        int farthest = 0;

        while (!queue.isEmpty()) {
            int index = queue.poll();

            int left = Math.max(index + minJump, farthest);
            int right = Math.min(index + maxJump, n - 1);

            farthest = right;

            for (int next = left; next <= right; next++) {
                if (s.charAt(next) == '1') continue;

                if (next == n - 1) return true;

                if (visit.add(next)) {
                    queue.add(next);
                }
            }
        }

        return false;
    }

    public boolean editorial(String s, int minJump, int maxJump) {
        int n = s.length();
        int[] pSum = new int[n];
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 0; i < minJump; i++) {
            // pSum[i] = 1;
            pSum[i] = (i == 0 ? 0 : pSum[i - 1]) + dp[i];
        }

        for (int i = minJump; i < n; i++) {
            int left = i - maxJump;
            int right = i - minJump;

            if (s.charAt(i) == '0') {
                int reachableCount = pSum[right] - (left <= 0 ? 0 : pSum[left - 1]);

                dp[i] = reachableCount > 0 ? 1 : 0;
            }

            pSum[i] = pSum[i - 1] + dp[i];
        }

        return dp[n - 1] == 1;
    }

    public boolean after_sol_fail(String s, int minJump, int maxJump) {
        int n = s.length();
        int[] pSum = new int[n + 1];
        boolean[] dp = new boolean[n];
        dp[0] = true;
        pSum[1] = 1;

        for (int i = 1; i <= minJump; i++) {
            pSum[i] = 1;
        }

        for (int i = minJump; i < n; i++) {
            pSum[i + 1] = pSum[i] + (s.charAt(i) == '0' ? 1 : 0);
        }

        int discount = 0;

        for (int i = minJump; i < n; i++) {
            int left = Math.max(i - maxJump, 0);
            int right = i - minJump;

            if (s.charAt(i) == '0') {
                int zeros = pSum[right + 1] - pSum[left];

                if (zeros > 0) {
                    dp[i] = true;
                } else {
                    discount++;
                }
            }

            pSum[i + 1] -= discount;
        }

        return dp[n - 1];
    }

    public boolean mySol(String s, int minJump, int maxJump) {
        int n = s.length();
        int[] pSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + (s.charAt(i) - '0');
        }

        return topdown(s, minJump, minJump, maxJump, pSum, new Boolean[n]);
    }

    public boolean topdown(String s, int i, int minJump, int maxJump, int[] pSum, Boolean[] memo) {
        if (i >= s.length()) return false;
        int j = i + (maxJump - minJump);
        if (j >= s.length()) j = s.length() - 1;

        if (pSum[j + 1] - pSum[i] == j - i + 1) return false;

        if (j >= s.length() - 1) return s.charAt(s.length() - 1) == '0';

        if (memo[i] != null) return memo[i];

        for (int next = i; next <= j; next++) {
            if (s.charAt(next) == '0') {
                if (topdown(s, next + minJump, minJump, maxJump, pSum, memo)) return memo[i] = true;
            }
        }

        return memo[i] = false;
    }
}