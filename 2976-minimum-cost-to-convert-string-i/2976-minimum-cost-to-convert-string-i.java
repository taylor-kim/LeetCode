class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        return mySol(source, target, original, changed, cost);
    }

    public long try_dijkstra(String source, String target, char[] original, char[] changed, int[] cost) {
        long MAX = (long)1e6 * source.length();

        long[][] min = new long[26][26];

        for (long[] arr : min) Arrays.fill(arr, MAX);

        long ans = 0;

        for (int i = 0; i < source.length(); i++) {
            long val = min[source.charAt(i) - 'a'][target.charAt(i) - 'a'];

            if (val == MAX) return -1;

            ans += val;
        }

        return ans;
    }

    public long mySol(String source, String target, char[] original, char[] changed, int[] cost) {
        long[][] min = new long[26][26];

        long MAX = (long)1e6 * source.length();

        for (long[] a : min) {
            Arrays.fill(a, MAX);
        }

        for (int i = 0; i < original.length; i++) {
            min[original[i] - 'a'][changed[i] - 'a'] = Math.min(min[original[i] - 'a'][changed[i] - 'a'], cost[i]);
        }

        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                min[i][i] = 0;
                for (int j = 0; j < 26; j++) {
                    if (i == j) continue;
                    min[i][j] = Math.min(min[i][j], min[i][k] + min[k][j]);
                }
            }
        }

        long ans = 0;

        for (int i = 0; i < source.length(); i++) {
            long val = min[source.charAt(i) - 'a'][target.charAt(i) - 'a'];

            if (val == MAX) return -1;

            ans += val;
        }

        return ans;
    }
}