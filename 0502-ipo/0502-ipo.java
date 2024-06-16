class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        return others_pq(k, w, profits, capital);
    }

    public int others_pq(int k, int w, int[] profits, int[] capital) {
        Queue<int[]> pqCap = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        Queue<int[]> pqPro = new PriorityQueue<>((a, b) -> {
            return b[0] - a[0];
        });

        for (int i = 0; i < profits.length; i++) {
            pqCap.add(new int[] {profits[i], capital[i]});
        }

        for (int i = 0; i < k; i++) {
            while (!pqCap.isEmpty() && pqCap.peek()[1] <= w) {
                pqPro.add(pqCap.poll());
            }

            if (pqPro.isEmpty()) break;

            w += pqPro.poll()[0];
        }

        return w;
    }

    public int try_pq_fail(int k, int w, int[] profits, int[] capital) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[0] - a[0];
        });

        for (int i = 0; i < profits.length; i++) {
            pq.add(new int[] {profits[i], capital[i]});
        }

        while (k-- > 0) {
            int size = pq.size();

            Queue<int[]> next = new LinkedList<>();

            boolean choosen = false;

            while (size-- > 0) {
                int[] data = pq.poll();
                int p = data[0];
                int c = data[1];

                if (!choosen && w >= c) {
                    w += p;
                    choosen = true;
                } else {
                    next.add(data);
                }
            }

            pq = next;
        }

        return w;
    }

    // it can't use past data when w becomes enough.
    public int mySol_topdown_fail(int k, int w, int[] profits, int[] capital) { 
        int[][] sorted = new int[profits.length][2];

        for (int i = 0; i < profits.length; i++) {
            sorted[i][0] = profits[i];
            sorted[i][1] = capital[i];
        }

        Arrays.sort(sorted, (a, b) -> {
            return a[1] - b[1];
        });

        return mySol_topdown(0, k, w, sorted, new Integer[profits.length][k + 1]);
    }

    public int mySol_topdown(int index, int k, int w, int[][] sorted, Integer[][] memo) {
        if (k == 0 || index >= sorted.length) {
            return k > 0 ? 0 : w;
        }

        if (memo[index][k] != null) {
            return memo[index][k];
        }

        int remain = w - sorted[index][1];

        int max = 0;

        if (remain >= 0) {
            max = mySol_topdown(index + 1, k - 1, w + sorted[index][0], sorted, memo);
        }

        max = Math.max(mySol_topdown(index + 1, k, w, sorted, memo), max);

        return memo[index][k] = max;
    }
}