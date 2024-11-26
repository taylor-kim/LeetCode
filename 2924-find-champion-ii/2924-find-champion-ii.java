class Solution {
    public int findChampion(int n, int[][] edges) {
        return mySol(n, edges);
    }

    public int mySol(int n, int[][] edges) {
        int[] indegrees = new int[n];
        
        for (int[] edge : edges) {
            indegrees[edge[1]]++;
        }

        int ans = -1;

        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) {
                if (ans == -1) {
                    ans = i;
                } else {
                    return -1;
                }
            }
        }

        return ans;
    }
}