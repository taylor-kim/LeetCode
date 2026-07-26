class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[0][i], 0, i});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            if (now[1] != n - 1) {
                pq.offer(new int[]{matrix[now[1] + 1][now[2]], now[1] + 1, now[2]});
            }
        }

        return pq.poll()[0];
    }
}
