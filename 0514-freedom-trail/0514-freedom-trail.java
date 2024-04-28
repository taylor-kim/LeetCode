class Solution {
    public int findRotateSteps(String ring, String key) {
        return official_topdown(ring, key);
    }

    public int official_topdown(String ring, String key) {
        return official_topdown(ring, key, 0, 0, new Integer[ring.length()][key.length()]);
    }

    public int official_topdown(String ring, String key, int index, int k, Integer[][] memo) {
        if (k == key.length()) return 0;

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < ring.length(); i++) {
            if (ring.charAt(i) == key.charAt(k)) {
                int sub = countSteps(index, i, ring.length()) + 1 + official_topdown(ring, key, i, k + 1, memo);

                ans = Math.min(ans, sub);
            }
        }

        return ans;
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