class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        return official_bs(arr, k);
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
                // while (j < n && arr[i] >= mid * arr[j]) {
                //     j++;
                // }
                j = i + 1;
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