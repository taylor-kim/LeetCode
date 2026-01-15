class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        return mySol(n, m, hBars, vBars);
    }

    public int mySol(int n, int m, int[] hBars, int[] vBars) {
        int h = getMax(n + 2, hBars);
        int w = getMax(m + 2, vBars);

        int min = Math.min(h, w);

        return min * min;
    }

    private int getMax(int limit, int[] bars) {
        Arrays.sort(bars);

        int max = 0;
        int count = 1;

        for (int i = 0; i < bars.length - 1; i++) {
            if (bars[i] + 1 == bars[i + 1]) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 1;
            }
        }

        max = Math.max(max, count);

        return max + 1;
    }
}