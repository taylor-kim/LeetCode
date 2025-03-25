class Solution {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        return official_linesweep(n, rectangles);
    }

    public boolean official_linesweep(int n, int[][] rect) {
        return checkCuts(rect, 0) || checkCuts(rect, 1);
    }

    private boolean checkCuts(int[][] rect, int dim) {
        Arrays.sort(rect, (a, b) -> {
            return a[dim] - b[dim];
        });

        int prevEnd = rect[0][dim + 2];

        int line = 0;

        for (int i = 1; i < rect.length; i++) {
            int[] current = rect[i];

            if (prevEnd <= current[dim]) {
                line++;
            }

            prevEnd = Math.max(prevEnd, current[dim + 2]);

            if (line == 2) return true;
        }

        return false;
    }

    public boolean mySol(int n, int[][] rect) {
        int[][] datas = new int[rect.length][2];

        // make datas horizontally
        for (int i = 0; i < rect.length; i++) {
            datas[i][0] = rect[i][1];
            datas[i][1] = rect[i][3];
        }

        if (check(datas)) return true;

        // make datas vertically
        for (int i = 0; i < rect.length; i++) {
            datas[i][0] = rect[i][0];
            datas[i][1] = rect[i][2];
        }

        return check(datas);
    }

    private boolean check(int[][] datas) {
        Arrays.sort(datas, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        int prevEnd = datas[0][1];

        int line = 0;
        int n = datas.length;

        for (int i = 1; i < n; i++) {
            int[] current = datas[i];

            if (prevEnd <= current[0]) {
                line++;
            }

            prevEnd = Math.max(prevEnd, current[1]);

            if (line == 2) return true;
        }

        return false;
    }
}