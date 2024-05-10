class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        return official_pq(arr, k);
    }

    public int[] official_pq(int[] arr, int k) {
        Queue<double[]> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(b[0], a[0]);
        });

        for (int i = 0; i < arr.length; i++) {
            pq.add(new double[] {
                -1.0 * arr[i] / arr[arr.length - 1]
                , i
                , arr.length - 1
            });
        }

        while (--k > 0) {
            double[] data = pq.poll();

            int numeratorIdx = (int) data[1];
            int denominatorIdx = (int) data[2] - 1;

            if (denominatorIdx > numeratorIdx) {
                pq.add(new double[] {
                    -1.0 * arr[numeratorIdx] / arr[denominatorIdx]
                    , numeratorIdx
                    , denominatorIdx
                });
            }
        }

        double[] result = pq.poll();

        return new int[] {arr[(int)result[1]], arr[(int)result[2]]};
    }

    public int[] official_bs(int[] arr, int k) {
        int n = arr.length;
        double left = 0.0;
        double right = 1.0;

        while (left <= right) {
            double mid = left + (right - left) / 2;

            double max = 0.0;
            int count = 0;
            int numeratorIdx = 0;
            int denominatorIdx = 0;
            int j = 1;

            for (int i = 0; i < n - 1; i++) {
                if (i > j) throw new RuntimeException(String.format("i:%d, j:%d", i, j));
                while (j < n && arr[i] >= mid * arr[j]) {
                    j++;
                }

                count += (n - j);

                if (j == n) break;

                double fraction = (double) arr[i] / arr[j];

                if (fraction > max) {
                    numeratorIdx = i;
                    denominatorIdx = j;
                    max = fraction;
                }
            }

            if (count == k) {
                return new int[] {arr[numeratorIdx], arr[denominatorIdx]};
            } else if (count > k) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return new int[] {};
    }

    public int[] mySol(int[] arr, int k) {
        int n = arr.length;

        Queue<Pair<Double, int[]>> pq = new PriorityQueue<>((a, b) -> {
            return a.getKey() < b.getKey() ? -1 : a.getKey() == b.getKey() ? 0 : 1;
        });

        for (int i = 0; i < n; i++) {
            double a = (double)arr[i];
            for (int j = i + 1; j < n; j++) {
                double b = (double)arr[j];

                int num = arr[i] / arr[j];

                double fraction = (a / b) - num;

                pq.add(new Pair(fraction, new int[] {arr[i], arr[j]}));
            }
        }

        while (k-- > 1) {
            pq.poll();
        }

        return pq.poll().getValue();
    }
}