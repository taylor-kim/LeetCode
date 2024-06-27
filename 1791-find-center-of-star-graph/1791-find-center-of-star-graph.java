class Solution {
    public int findCenter(int[][] edges) {
        return mySol(edges);
    }

    public int mySol(int[][] edges) {
        int n = edges.length + 1;
        int[] count = new int[n + 1];

        for (int[] edge : edges) {
            if (++count[edge[0]] > 1) {
                return edge[0];
            }

            if (++count[edge[1]] > 1) {
                return edge[1];
            }
        }

        return -1;
    }
}