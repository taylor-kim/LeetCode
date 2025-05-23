class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        return others_greedy(tops, bottoms);
    }

    private int others_greedy(int[] tops, int[] bottoms) {
        int ans = greedy(tops, bottoms, tops[0]);

        if (tops[0] != bottoms[0]) {
            ans = Math.min(ans, greedy(tops, bottoms, bottoms[0]));
        }

        return ans == tops.length ? -1 : ans;
    }

    private int greedy(int[] tops, int[] bottoms, int target) {
        int n = tops.length;

        int rotateTop = 0;
        int rotateBottom = 0;

        for (int i = 0; i < n; i++) {
            if (tops[i] != target && bottoms[i] != target) {
                return n;
            }

            if (tops[i] != target) rotateTop++;
            if (bottoms[i] != target) rotateBottom++;
        }

        return Math.min(rotateTop, rotateBottom);
    }

    public int mySol3(int[] tops, int[] bottoms) {
        int top = mySol2(tops, bottoms);
        int bot = mySol2(bottoms, tops);

        int ans = Math.min(top, bot);

        return ans == tops.length ? -1 : ans;
    }

    public int mySol2(int[] tops, int[] bottoms) {
        int n = tops.length;

        int ans = n;

        for (int num = 1; num <= 6; num++) {
            int swap = 0;
            boolean success = true;

            for (int i = 0; i < n; i++) {
                if (tops[i] == num) continue;

                if (bottoms[i] == num) {
                    swap++;
                } else {
                    success = false;
                    break;
                }
            }

            if (success) {
                ans = Math.min(ans, swap);
            }
        }

        return ans;
    }

    public int mySol_fail(int[] tops, int[] bottoms) {
        int n = tops.length;
        Map<Integer, Integer> topCount = new HashMap();
        Map<Integer, Integer> bottomCount = new HashMap();

        for (int i = 0; i < n; i++) {
            topCount.put(tops[i], topCount.getOrDefault(tops[i], 0) + 1);
            bottomCount.put(bottoms[i], bottomCount.getOrDefault(bottoms[i], 0) + 1);
        }

        int ans = n;

        for (int number = 1; number <= 6; number++) {
            int tc = topCount.getOrDefault(number, 0);
            int bc = bottomCount.getOrDefault(number, 0);

            System.out.println(String.format("num:%d, tc:%d, bc:%d", number, tc, bc));

            if (tc + bc == n) {
                ans = Math.min(tc, bc);
            }
        }

        return ans == n ? -1 : ans;
    }
}