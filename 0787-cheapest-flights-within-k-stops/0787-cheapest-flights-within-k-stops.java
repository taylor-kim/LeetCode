class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        return mySol(n, flights, src, dst, k);
    }
    
    public int mySol(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> edges = new HashMap();
        
        for (int[] f : flights) {
            edges.computeIfAbsent(f[0], key -> new ArrayList());
            edges.get(f[0]).add(new int[] {f[1], f[2]});
        }
        
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[] {src, 0});
        boolean[] visit = new boolean[n];
        visit[src] = true;
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        
        int ans = 0;
        
        k += 2;
        
        while (!queue.isEmpty() && k-- > 0) {
            int size = queue.size();
            int minCost = Integer.MAX_VALUE;
            
            while (size-- > 0) {
                int city = queue.peek()[0];
                int cost = queue.poll()[1];
                
                // System.out.println(String.format("city:%d, cost:%d, k:%d", city, cost, k + 1));
                
                if (costs[city] <= cost) {
                    continue;
                }
                
                if (city == dst) {
                    minCost = Math.min(minCost, cost);
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
}