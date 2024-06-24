class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        return profit_base_bs(difficulty, profit, worker);
    }

    public int profit_base_bs(int[] difficulty, int[] profit, int[] worker) {
        int n = profit.length;
        int[][] datas = new int[n][2];

        for (int i = 0; i < n; i++) {
            datas[i][0] = profit[i];
            datas[i][1] = difficulty[i];
        }

        Arrays.sort(datas, (a, b) -> {
            return b[0] - a[0];
        });

        for (int i = 0; i < n - 1; i++) {
            datas[i + 1][1] = Math.min(datas[i + 1][1], datas[i][1]);
        }

        int ans = 0;

        for (int i = 0; i < worker.length; i++) {
            int lo = 0;
            int hi = n - 1;
            int max = 0;

            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int p = datas[mid][0];
                int diff = datas[mid][1];

                if (worker[i] >= diff) {
                    max = Math.max(max, p);
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            ans += max;
        }

        return ans;
    }

    public int diffbase_bs(int[] difficulty, int[] profit, int[] worker) {
        int n = profit.length;
        int[][] datas = new int[n][2];

        for (int i = 0; i < n; i++) {
            datas[i][0] = difficulty[i];
            datas[i][1] = profit[i];
        }

        Arrays.sort(datas, (a, b) -> {
            return a[0] - b[0];
        });

        for (int i = 0; i < n - 1; i++) {
            datas[i + 1][1] = Math.max(datas[i + 1][1], datas[i][1]);
        }

        int ans = 0;

        for (int i = 0; i < worker.length; i++) {
            int lo = 0;
            int hi = n - 1;
            int max = 0;

            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int diff = datas[mid][0];
                int p = datas[mid][1];

                if (worker[i] >= diff) {
                    max = Math.max(max, p);
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            ans += max;
        }

        return ans;
    }

    public int mySol(int[] difficulty, int[] profit, int[] worker) {
        

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] != b[1] ? b[1] - a[1] : a[0] - b[0];
        });

        for (int i = 0; i < difficulty.length; i++) {
            pq.add(new int[] {
                difficulty[i]
                , profit[i]
            });
        }

        int ans = 0;

        for (int i = 0; i < worker.length; i++) {
            Queue<int[]> next = new PriorityQueue<>((a, b) -> {
                return a[1] != b[1] ? b[1] - a[1] : a[0] - b[0];
            });

            int size = pq.size();

            while (size-- > 0) {
                int[] data = pq.poll();
                int diff = data[0];
                int p = data[1];

                next.add(data);

                if (worker[i] >= diff) {
                    // System.out.println(String.format("worker:%d, data:%s", worker[i], Arrays.toString(data)));
                    ans += p;
                    break;
                }
            }

            pq.addAll(next);
        }

        return ans;
    }
}