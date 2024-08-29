class Solution {
    public int removeStones(int[][] stones) {
        return otherSol(stones);
    }

    public int fail(int[][] stones) {
        boolean[] visited = new boolean[stones.length];

        int land = 0;
        int ans = 0;

        for (int i = 0; i < stones.length; i++) {
            if (!visited[i]) {
                ans += dfs(stones, i, visited);
                land++;
            }
        }

        // System.out.println(Arrays.toString(visited));

        // System.out.println(String.format("land : %d", land));
        // System.out.println(String.format("ans : %d", ans));

        /***
        
        1, 1, 0, 0, 0
        1, 1, 0, 0, 0
        0, 1, 1, 0, 0
        0, 0, 1, 1, 1
        0, 0, 0, 1, 1
        
         */

         /**
         
        2, 2, 0, 0, 0
        1, 2, 0, 0, 0
        0, 2, 2, 0, 0
        0, 0, 2, 2, 2
        0, 0, 0, 1, 2

          */

          /***
          1, 0, 1
          1, 0, 0
           */

        return stones.length - land;
        // return ans;
    }

    private int dfs(int[][] stones, int i, boolean[] visited) {
        if (visited[i]) return 0;

        visited[i] = true;

        int recur = 0;
        int self = 0;

        for (int j = 0; j < stones.length; j++) {
            if (visited[j]) continue;

            if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {

                // System.out.println(String.format("i:%d => j:%d", i, j));
                self = 1;
                
                recur += dfs(stones, j, visited);
            }
        }

        return self + recur;
    }

    public int otherSol(int[][] stones) {
        boolean[] visited = new boolean[stones.length];

        int land = 0;

        for (int i = 0; i < stones.length; i++) {
            if (!visited[i]) {
                otherDfs(stones, i, visited);
                land++;
            }
        }

        return stones.length - land;
    }

    private void otherDfs(int[][] stones, int i, boolean[] visited) {
        if (visited[i]) return;

        visited[i] = true;

        for (int j = 0; j < stones.length; j++) {
            if (visited[j]) continue;

            if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                otherDfs(stones, j, visited);
            }
        }
    }
}