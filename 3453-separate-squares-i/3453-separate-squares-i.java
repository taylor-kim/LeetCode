class Solution {
    public double separateSquares(int[][] squares) {
        return mySol(squares);
    }

    public double mySol(int[][] squares) {
        Arrays.sort(squares, (a, b) -> {
            return a[1] - b[1];
        });

        double max = Double.MIN_VALUE;

        for (int[] square : squares) {
            max = Math.max(max, square[1] + square[2]);
        }

        double lo = 0;
        double hi = max + 1;
        double ans = Double.MAX_VALUE;

        while (lo < hi) {
            double mid = lo + (hi - lo) / 2;

            double[] sumOfTopAndBot = getSumOfTopAndBot(squares, mid);

            // System.out.println("lo:%f, hi:%f, mid:%f, top:%f, bot:%f"
            //     .formatted(lo, hi, mid, sumOfTopAndBot[0], sumOfTopAndBot[1]));

            if (sumOfTopAndBot[0] <= sumOfTopAndBot[1]) {
                hi = mid - 0.000001d;;
                // ans = mid;
            } else {
                lo = mid + 0.000001d;
            }
        }

        return lo;
    }

    private double[] getSumOfTopAndBot(int[][] squares, double y) {
        double sumOfTop = 0d;
        double sumOfBot = 0d;

        for (int[] square : squares) {
            double top = (double)square[1] + square[2];;
            double bot = (double)square[1];

            if (y <= bot) {
                sumOfTop += (top - bot) * square[2];
            } else if (top <= y) {
                sumOfBot += (top - bot) * square[2];
            } else {
                sumOfTop += (top - y) * square[2];
                sumOfBot += (y - bot) * square[2];
            }
        }

        return new double[] {sumOfTop, sumOfBot};
    }
}