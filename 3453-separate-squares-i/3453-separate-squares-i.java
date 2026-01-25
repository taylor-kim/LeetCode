class Solution {
    public double separateSquares(int[][] squares) {
        return editorial_line_sweep(squares);
    }

    public double editorial_line_sweep(int[][] squares) {
        List<int[]> lines = new ArrayList();

        long totalArea = 0;

        for (int[] square : squares) {
            int y = square[1];
            int l = square[2];

            lines.add(new int[] {y, l, 1});
            lines.add(new int[] {y + l, l, -1});

            totalArea += 1l * l * l;
        }

        Collections.sort(lines, (a, b) -> {
            return a[0] - b[0];
        });

        double areaSoFar = 0.0;
        double prevY = 0;
        double sumOfWidthSoFar = 0;

        for (int[] line : lines) {
            int y = line[0];
            int l = line[1];

            int hight = y - (int)prevY;

            double area = sumOfWidthSoFar * hight;

            if (2l * (areaSoFar + area) >= totalArea) {
                return (totalArea / 2.0d - areaSoFar) / sumOfWidthSoFar + prevY;
            }

            sumOfWidthSoFar += l * line[2];
            areaSoFar += area;
            prevY = y;
        }

        return 0.0;
    }

    /**
    area + x == t / 2;
    x = width + delta
    delta = t / 2 - erea - width
     */

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
                hi = mid - 0.000001d;
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