class Solution {
    public long minCost(int m, int n, int[][] waitCost) {
        return hint(m, n, waitCost);
    }

    public long hint(int m, int n, int[][] waitCost) {
        return dp(0, 0, 1, m, n, waitCost, new Long[m][n]);
    }

    private long dp(int y, int x, int seconds, int m, int n, int[][] waitCost, Long[][] memo) {
        if (y >= m || x >= n) return Long.MAX_VALUE - (long)1e5;

        long entryCost = (y + 1l) * (x + 1);

        if (y == m - 1 && x == n - 1) return entryCost;

        // System.out.println(String.format("y:%d, x:%d, sec:%d", y, x, seconds));

        // if (memo[y][x][seconds] != null) return memo[y][x][seconds];
        if (memo[y][x] != null) return memo[y][x];

        int nextSeconds = (seconds + 1) % 2;

        long ans = 0;

        if (seconds == 1) {
            long right = dp(y, x + 1, nextSeconds, m, n, waitCost, memo);
            long down = dp(y + 1, x, nextSeconds, m, n, waitCost, memo);

            ans = entryCost + Math.min(right, down);
        } else {
            ans = waitCost[y][x] + dp(y, x, nextSeconds, m, n, waitCost, memo);
        }

        // return memo[y][x][seconds] = ans;
        return memo[y][x] = ans;
    }

    public long mySol2_fail(int m, int n, int[][] waitCost) {
        return topdown(m, n, waitCost, 0, 0, 1);
    }

    public long topdown(int m, int n, int[][] waitCost, int y, int x, int seconds) {
        if (y >= m || x >= n) return -1;

        long cost = (y + 1) * (x + 1);
        
        if (y + 1 == m && x + 1 == n) return cost;

        long ans = Long.MAX_VALUE;

        if (seconds % 2 == 1) {
            long right = topdown(m, n, waitCost, y, x + 1, seconds + 1);
            long down = topdown(m, n, waitCost, y + 1, x, seconds + 1);

            if (right != -1) {
                ans = Math.min(ans, right);
            }

            if (down != -1) {
                ans = Math.min(ans, down);
            }
        } else {
            long wait = topdown(m, n, waitCost, y, x, seconds + 1);

            if (wait != -1) {
                ans = waitCost[y][x] + wait;
            }
        }

        return ans != Long.MAX_VALUE ? ans + cost : -1;
    }

    public long mySol_tle(int m, int n, int[][] waitCost) {
        Queue<Data> pq = new PriorityQueue<>((a, b) -> {
            return Long.compare(a.cost, b.cost);
        });

        pq.add(new Data(1, 0, 0, 1));

        int[][] dirs = {
            {0, 1},
            {1, 0}
        };

        while (!pq.isEmpty()) {
            Data d = pq.poll();
    
            for (int[] dir : dirs) {
                int ny = d.y + dir[0];
                int nx = d.x + dir[1];

                if (ny >= m || nx >= n) continue;

                long nextCost = d.cost + (ny + 1) * (nx + 1);

                int wait = (d.seconds + 1) % 2 == 1 ? 0 : waitCost[ny][nx];

                if (ny + 1 == m && nx + 1 == n) return nextCost + wait;

                pq.add(new Data(wait + nextCost, ny, nx, d.seconds + 1));
            }
        }

        return -1;
    }

    public class Data {
        long cost;
        int y;
        int x;
        int seconds;

        public Data(long cost, int y, int x, int seconds) {
            this.cost = cost;
            this.y = y;
            this.x = x;
            this.seconds = seconds;
        }
    }
}