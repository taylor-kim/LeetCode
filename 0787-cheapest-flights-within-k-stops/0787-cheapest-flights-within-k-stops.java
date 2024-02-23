class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        return mySol_bfs_improved(n, flights, src, dst, k);
    }
    
    public int mySol_dfs_fail(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> edges = new HashMap();
        
        for (int[] f : flights) {
            edges.computeIfAbsent(f[0], key -> new ArrayList());
            edges.get(f[0]).add(new int[] {f[1], f[2]});
        }
        
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        
        dfs(k + 1, edges, src, dst, 0, costs);
        
        System.out.println(Arrays.toString(costs));
        
        int ans = costs[dst];
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    public void dfs(int k, Map<Integer, List<int[]>> edges, int current, int dst, int sum, int[] costs) {
        if (k < 0) return;
        
        if (costs[current] <= sum) return;
        
        costs[current] = sum;
        
        if (current == dst) {
            return;
        }
        
        int ans = Integer.MAX_VALUE;
        
        if (edges.get(current) == null) return;
        
        for (int[] next : edges.get(current)) {
            dfs(k - 1, edges, next[0], dst, sum + next[1], costs);
        }
    }
    
    public int mySol_bfs(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> edges = new HashMap();
        
        for (int[] f : flights) {
            edges.computeIfAbsent(f[0], key -> new ArrayList());
            edges.get(f[0]).add(new int[] {f[1], f[2]});
        }
        
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[] {src, 0});
        
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        
        k += 2;
        
        while (!queue.isEmpty() && k-- > 0) {
            int size = queue.size();
            
            while (size-- > 0) {
                int city = queue.peek()[0];
                int cost = queue.poll()[1];
                
                // System.out.println(String.format("city:%d, cost:%d, k:%d", city, cost, k + 1));
                
                if (costs[city] <= cost) {
                    continue;
                }
                
                costs[city] = cost;
                
                if (edges.get(city) == null) continue;
                
                for (int[] next : edges.get(city)) {
                    queue.add(new int[] {next[0], cost + next[1]});
                }
            }
        }
        
        // System.out.println(Arrays.toString(costs));
        
        return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }
    
    public int mySol_bfs_improved(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> edges = new HashMap();
        
        for (int[] f : flights) {
            edges.computeIfAbsent(f[0], key -> new ArrayList());
            edges.get(f[0]).add(new int[] {f[1], f[2]});
        }
        
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[] {src, 0});
        
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        
        k++;
        
        while (!queue.isEmpty() && k-- > 0) {
            int size = queue.size();
            int minCost = Integer.MAX_VALUE;
            
            while (size-- > 0) {
                int city = queue.peek()[0];
                int cost = queue.poll()[1];
                
                // System.out.println(String.format("city:%d, cost:%d, k:%d", city, cost, k + 1));
                
                if (edges.get(city) == null) continue;
                
                for (int[] next : edges.get(city)) {
                    int nextNode = next[0];
                    int nextCost = cost + next[1];
                    
                    if (costs[nextNode] > nextCost) {
                        queue.add(new int[] {nextNode, nextCost});
                        costs[nextNode] = nextCost;
                    }
                }
            }
        }
        
        // System.out.println(Arrays.toString(costs));
        
        return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }
}