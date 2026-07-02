class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        return mySol(grid);
    }

    int[][] dirs = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1},
    };

    public int mySol(List<List<Integer>> grid) {
        Queue<Integer> queue = new LinkedList();
        Map<Integer, Integer> points = new HashMap();

        int n = grid.size();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    int index = i * n + j;
                    queue.add(index);
                    points.put(index, 0);
                }
            }
        }

        while (!queue.isEmpty()) {
            int index = queue.poll();
            int y = index / n;
            int x = index % n;
            int p = points.get(index);

            for (int[] dir : dirs) {
                int ny = y + dir[0];
                int nx = x + dir[1];
                int nIndex = ny * n + nx;

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !points.containsKey(nIndex)) {
                    queue.add(nIndex);
                    points.put(nIndex, p + 1);
                }
            }
        }

        // System.out.println(points);

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[1] - a[1];
        });

        pq.add(new int[] {0, points.remove(0)});

        int finish = (n * n) - 1;

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int index = data[0];
            int p = data[1];

            int y = index / n;
            int x = index % n;

            // System.out.println("index:%d, y:%d, x:%d, p:%d".formatted(index, y, x, p));

            if (index == finish) {
                return p;
            }

            for (int[] dir : dirs) {
                int ny = y + dir[0];
                int nx = x + dir[1];
                int nIndex = ny * n + nx;

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && points.containsKey(nIndex)) {
                    pq.add(new int[] {nIndex, Math.min(p, points.remove(nIndex))});
                }
            }
        }

        /**
        [3,2,1,0]
        [2,3,2,1]
        [1,2,3,2]
        [0,1,2,3]
         */

        return -1;
    }
}