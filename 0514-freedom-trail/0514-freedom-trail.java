class Solution {
    public int findRotateSteps(String ring, String key) {
        return official_dijkstra(ring, key);
    }

    public int official_dijkstra(String ring, String key) {        
        Map<Character, List<Integer>> graph = new HashMap();

        for (int i = 0; i < ring.length(); i++) {
            char c = ring.charAt(i);

            graph.computeIfAbsent(c, k -> new ArrayList()).add(i);
        }

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        queue.add(new int[] {0, 0, 0});

        boolean[][] visit = new boolean[ring.length()][key.length()];

        int steps = 0;

        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            steps = data[0];
            int current = data[1];
            int k = data[2];

            if (k == key.length()) {
                break;
            }

            if (visit[current][k]) {
                continue;
            }

            visit[current][k] = true;

            for (int next : graph.get(key.charAt(k))) {
                queue.add(new int[] {
                    steps + countSteps(current, next, ring.length())
                    , next
                    , k + 1
                });
            }
        }

        return steps + key.length();
    }

    public int try_bottomup_spaceopt(String ring, String key) {
        int n = ring.length();

        int[] dp = new int[n];

        for (int k = key.length() - 1; k >= 0; k--) {
            int[] currentDp = new int[n];

            for (int start = 0; start < n; start++) {
                int min = Integer.MAX_VALUE;
                for (int next = 0; next < n; next++) {
                    if (ring.charAt(next) == key.charAt(k)) {
                        int sub = countSteps(start, next, n) + 1 + dp[next];
                        min = Math.min(min, sub);
                    }
                }
                currentDp[start] = min;
            }

            dp = currentDp;
        }

        return dp[0];
    }

    public int try_bottomup(String ring, String key) {
        int n = ring.length();

        int[][] dp = new int[n][key.length() + 1];

        for (int k = key.length() - 1; k >= 0; k--) {
            for (int start = 0; start < n; start++) {
                int min = Integer.MAX_VALUE;
                for (int next = 0; next < n; next++) {
                    if (ring.charAt(next) == key.charAt(k)) {
                        int sub = countSteps(start, next, n) + 1 + dp[next][k + 1];
                        min = Math.min(min, sub);
                    }
                }
                // dp[start][k] = min == Integer.MAX_VALUE ? 0 : min;
                dp[start][k] = min;
            }
        }

        return dp[0][0];
    }

    public int official_topdown(String ring, String key) {
        return official_topdown(ring, key, 0, 0, new Integer[ring.length()][key.length()]);
    }

    public int official_topdown(String ring, String key, int index, int k, Integer[][] memo) {
        if (k == key.length()) return 0;

        if (memo[index][k] != null) {
            return memo[index][k];
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < ring.length(); i++) {
            if (ring.charAt(i) == key.charAt(k)) {
                int sub = countSteps(index, i, ring.length()) + 1 + official_topdown(ring, key, i, k + 1, memo);

                ans = Math.min(ans, sub);
            }
        }

        return memo[index][k] = ans;
    }

    private int countSteps(int i, int j, int length) {
        int between = Math.abs(i - j);
        int around = length - between;

        return Math.min(between, around);
    }

    public int try_topdown(String ring, String key) {
        return try_topdown(ring, key, 0, 0, new Integer[ring.length()][key.length()]);
    }

    public int try_topdown(String ring, String key, int i, int k, Integer[][] memo) {
        if (k == key.length()) return 0;

        if (memo[i][k] != null) {
            return memo[i][k];
        }

        char c = ring.charAt(i);
        char m = key.charAt(k);

        if (c == m) {
            return memo[i][k] = 1 + try_topdown(ring, key, i, k + 1, memo);
        } else {

            int[] next = findNext(ring, i, m);

            return memo[i][k] = next[1] + try_topdown(ring, key, next[0], k, memo);
        }
    }

    private int[] findNext(String s, int start, char target) {
        int left = start;
        int right = start;
        int move = 0;

        do {
            if (s.charAt(left) == target) {
                return new int[] {left, move};
            } else if (s.charAt(right) == target) {
                return new int[] {right, move};
            }

            left--;
            right++;
            move++;

            if (left < 0) {
                left = s.length() - 1;
            }

            if (right >= s.length()) {
                right = 0;
            }
        } while (left != start && right != start);

        return null;
    }

    public int tryAgain_fail(String ring, String key) {
        int n = ring.length();
        int k = key.length();

        Queue<Integer> queue = new LinkedList();
        queue.add(0);

        int ans = 0;
        int matched = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int i = queue.poll();
                char c = ring.charAt(i);
                char m = key.charAt(matched);

                if (c == m) {
                    ans++;
                    matched++;

                    if (matched == k) {
                        return ans;
                    }

                    queue.clear();
                    queue.add(i);
                } else {
                    int prev = i - 1 >= 0 ? i - 1 : n - 1;
                    int next = i + 1 < n ? i + 1 : 0;
                    
                    queue.add(prev);
                    queue.add(next);
                }
            }

            ans++;
        }

        return -1;
    }
}