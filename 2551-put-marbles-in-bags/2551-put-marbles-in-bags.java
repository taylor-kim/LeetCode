class Solution {
    public long putMarbles(int[] weights, int k) {
        return hint_and_king_god_doo(weights, k);
    }

    public long hint_and_king_god_doo(int[] weights, int k) {
        if (k == 1 || weights.length == k) return 0l;

        Queue<Integer> minHeap = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        Queue<Integer> maxHeap = new PriorityQueue();

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        long minSum = 0;
        long maxSum = 0;

        for (int i = 0; i < weights.length - 1; i++) {
            int currentSplitValue = weights[i] + weights[i + 1];

            minSum += currentSplitValue;
            maxSum += currentSplitValue;

            minHeap.add(currentSplitValue);
            maxHeap.add(currentSplitValue);

            if (minHeap.size() == k) {
                minSum -= minHeap.poll();
            }

            if (maxHeap.size() == k) {
                maxSum -= maxHeap.poll();
            }
        }

        return maxSum - minSum;
    }

    public long mySol_bf_mle(int[] weights, int k) {
        Long[] ans = topdown(weights, 0, k - 1, new Long[weights.length][k + 1][2]);
        
        return ans[1] - ans[0];
    }

    private Long[] topdown(int[] weights, int start, int k, Long[][][] memo) {
        if (k < 0) {
            return start >= weights.length ? new Long[] {0L, 0L} : null;
        }

        if (memo[start][k][0] != null) {
            // return memo[start][k];
        }

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for (int end = start; end < weights.length - k; end++) {
            Long[] sub = topdown(weights, end + 1, k - 1, memo);

            if (sub == null) continue;

            long current = weights[start] + weights[end];
            long localMin = current + sub[0];
            long localMax = current + sub[1];

            min = Math.min(min, localMin);
            max = Math.max(max, localMax);
        }

        return memo[start][k] = new Long[] {min, max};
    }
}