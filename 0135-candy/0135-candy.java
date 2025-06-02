class Solution {
    public int candy(int[] ratings) {
        return try_linear(ratings);
    }

    public int try_linear(int[] ratings) {
        int n = ratings.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int r : ratings) {
            min = Math.min(min, r);
            max = Math.max(max, r);
        }

        List<Integer>[] data = new List[max - min + 1];

        for (int i = 0; i < n; i++) {
            if (data[ratings[i] - min] == null) {
                data[ratings[i] - min] = new ArrayList();
            }

            data[ratings[i] - min].add(i);
        }

        int r = 0;

        int[] given = new int[n];

        int ans = 0;

        while (r <= max - min) {
            if (data[r] == null) {
                r++;
                continue;
            }

            for (int index : data[r]) {
                int count = 1;

                if (index - 1 >= 0 && ratings[index - 1] < ratings[index]) {
                    count = given[index - 1] + 1;
                }

                if (index + 1 < n && ratings[index] > ratings[index + 1]) {
                    count = Math.max(count, given[index + 1] + 1);
                }

                ans += given[index] = count;
            }

            r++;
        }

        return ans;
    }

    public int mySol(int[] ratings) {
        int n = ratings.length;

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        for (int i = 0; i < n; i++) {
            pq.add(new int[] {ratings[i], i});
        }

        int[] given = new int[n];

        int ans = 0;

        while (!pq.isEmpty()) {
            int[] data = pq.poll();

            int rating = data[0];
            int index = data[1];

            int count = 1;

            if (index - 1 >= 0 && ratings[index - 1] < ratings[index]) {
                count = given[index - 1] + 1;
            }

            if (index + 1 < n && ratings[index] > ratings[index + 1]) {
                count = Math.max(count, given[index + 1] + 1);
            }

            ans += given[index] = count;
        }

        return ans;
    }
}