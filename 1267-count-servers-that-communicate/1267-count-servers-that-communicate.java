class DSU {
    List<Integer> parent = new ArrayList<>();
    List<Integer> rank = new ArrayList<>();
    DSU(int n){ 
        for(int i = 0;i <= n;i++){
            rank.add(0);
            parent.add(i);
        }
    }
    public int findUPar(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        parent.set(node,findUPar(parent.get(node)));
        return parent.get(node);
    }
    public void unionByRank(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (rank.get(ulp_u) < rank.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
        } else if (rank.get(ulp_v) < rank.get(ulp_u)) {
            parent.set(ulp_v, ulp_u);
        } else {
            parent.set(ulp_v, ulp_u);
            int rankU = rank.get(ulp_u);
            rank.set(ulp_u, rankU + 1);
        }
    }
}
class Solution {
    public int countServers(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        DSU ds = new DSU(n + m);
        for(int i=0;i < n;i++) {
            for(int j = 0;j < m;j++) {
                if(grid[i][j] == 1){
                int u = i;
                int v = j + n;
                ds.unionByRank(u,v);
                }
            }
        }
        int size[] = new int[n+m];
        int cntservers = 0;
        for(int i = 0;i < n;i++){
           for(int j = 0;j < m;j++){
              if(grid[i][j] == 1)
              {
                int par = ds.findUPar(i);
                size[par]++;
              }
           }
        }
        for(int i = 0;i < (n + m);i++){
            if(size[i] > 1){
                cntservers += size[i];
            }
        }
        

        return cntservers;
    }
}