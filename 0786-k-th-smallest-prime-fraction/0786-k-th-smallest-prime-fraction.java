class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        return mySol(arr, k);
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