class Solution {
    public int leastInterval(char[] tasks, int n) {
        return official_pq(tasks, n);
    }

    public int official_pq(char[] tasks, int n) {
        int[] freq = new int[26];
        
        for (char c : tasks) {
            freq[c - 'A']++;
        }

        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int count : freq) {
            if (count > 0) {
                pq.add(count);
            }
        }

        int interval = 0;

        while (!pq.isEmpty()) {
            int size = pq.size();
            int cycle = n + 1;
            int processedCount = 0;

            List<Integer> list = new ArrayList();

            while (cycle-- > 0 && size-- > 0) {
                int count = pq.poll();
                if (--count > 0) {
                    list.add(count);
                }
                processedCount++;
            }

            pq.addAll(list);

            interval += pq.isEmpty() ? processedCount : n + 1;
        }

        return interval;
    }

    public int mySol(char[] tasks, int n) {
        int[] freq = new int[26];
        
        for (char c : tasks) {
            freq[c - 'A']++;
        }

        int initTime = 0;

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            // return a[0] != b[0] ? a[0] - b[0] : b[1] - a[1];
            return a[1] != b[1] ? b[1] - a[1] : a[0] - b[0];
        });

        for (int count : freq) {
            if (count > 0) {
                pq.add(new int[] {initTime++, count});
            }
        }

        int interval = 0;

        while (!pq.isEmpty()) {
            int start = pq.peek()[0];
            int count = pq.poll()[1];

            // if (start < interval) throw new RuntimeException();

            interval = Math.max(start, interval);

            if (--count > 0) {
                pq.add(new int[] {interval + n + 1, count});
            }

            interval++;
        }

        return interval;
    }
}