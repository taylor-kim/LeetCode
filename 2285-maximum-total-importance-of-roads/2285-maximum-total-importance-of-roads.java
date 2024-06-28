class Solution {
    public long maximumImportance(int n, int[][] roads) {
        return official(n, roads);
    }

    public long official(int n, int[][] roads) {
        int[] freq = new int[n];
        long ans = 0;

        for (int i = 0; i < roads.length; i++) {
            freq[roads[i][0]]++;
            freq[roads[i][1]]++;
        }

        Arrays.sort(freq);

        for (int i = 0; i < n; i++) {
            long count = freq[i];
            int node = i + 1;

            ans += count * node;
        }

        return ans;
    }

    public long mySol(int n, int[][] roads) {
        int[][] iFreq = new int[n][2];

        for (int i = 0; i < roads.length; i++) {
            int[] road = roads[i];
            int a = road[0];
            int b = road[1];

            iFreq[a][0] = a;
            iFreq[a][1]++;

            iFreq[b][0] = b;
            iFreq[b][1]++;
        }

        Arrays.sort(iFreq, (a, b) -> {
            return a[1] - b[1];
        });

        long sum = 0;

        for (int i = 0; i < iFreq.length; i++) {
            iFreq[i][1] = i + 1;
        }

        Map<Integer, Integer> map = new HashMap();

        for (int[] freq : iFreq) {
            // System.out.println(String.format("city:%d, freq:%d", freq[0], freq[1]));

            map.put(freq[0], freq[1]);
        }

        for (int[] road : roads) {
            int r0 = road[0];
            int r1 = road[1];

            // int p0 = iFreq[r0][1];
            // int p1 = iFreq[r1][1];

            int p0 = map.get(r0);
            int p1 = map.get(r1);

            // System.out.println(String.format("road (%d, %d) has an importance of %d + %d = %d", r0, r1, p0, p1, p0 + p1));

            // sum += iFreq[r0][0] + iFreq[r1][0] + 2;
            sum += p0 + p1;
        }

        return sum;
    }
}