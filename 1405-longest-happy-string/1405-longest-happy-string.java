class Solution {
    public String longestDiverseString(int a, int b, int c) {
        return mySol(a, b, c);
    }

    public String mySol(int a, int b, int c) {
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o2[0] - o1[0];
        });

        pq.add(new int[] {a, (int)'a'});
        pq.add(new int[] {b, (int)'b'});
        pq.add(new int[] {c, (int)'c'});

        StringBuilder sb = new StringBuilder();

        char lc = '1';
        int ls = 0;

        while (pq.size() > 0 && pq.peek()[0] > 0) {
            int[] data = pq.poll();

            int limit = 2;

            if (lc == (char)data[1]) {
                limit = 2 - ls;
            }

            if (limit == 0) continue;

            append(data, sb, limit);

            lc = (char)data[1];
            ls = limit;

            if (pq.size() > 0 && pq.peek()[0] > 0) {
                int[] next = pq.poll();

                append(next, sb, 1);

                lc = (char)next[1];
                ls = 1;

                if (next[0] > 0) pq.add(next);
            }

            if (data[0] > 0) pq.add(data);
        }

        return sb.toString();
    }

    private void append(int[] data, StringBuilder sb, int limit) {
        int count = Math.min(limit, data[0]);

        sb.append(String.valueOf((char)data[1]).repeat(count));

        data[0] -= count;
    }
}