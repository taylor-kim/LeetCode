class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        return try_official_bfs(n, meetings, firstPerson);
    }
    
    public List<Integer> try_official_bfs(int n, int[][] meetings, int first) {
        Map<Integer, List<int[]>> edges = new HashMap();
        
        for (int[] m : meetings) {
            edges.computeIfAbsent(m[0], k -> new ArrayList()).add(new int[] {m[1], m[2]});
            edges.computeIfAbsent(m[1], k -> new ArrayList()).add(new int[] {m[0], m[2]});
        }
        
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[] {0, 0});
        queue.add(new int[] {first, 0});
        
        int[] earliest = new int[n];
        Arrays.fill(earliest, Integer.MAX_VALUE);
        earliest[0] = 0;
        earliest[first] = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            while (size-- > 0) {
                int a = queue.peek()[0];
                int t = queue.poll()[1];
                
                for (int[] next : edges.getOrDefault(a, Collections.emptyList())) {
                    int b = next[0];
                    int nextT = next[1];
                    
                    if (nextT >= t && earliest[b] > nextT) {
                        earliest[next[0]] = next[1];
                        queue.add(next);
                    }
                }
            }
        }
        
        List<Integer> ans = new ArrayList();
        
        for (int i = 0; i < n; i++) {
            if (earliest[i] != Integer.MAX_VALUE) {
                ans.add(i);
            }
        }
        
        return ans;
    }
    
    public List<Integer> mySol3_fail(int n, int[][] meetings, int first) {
        Map<Integer, Set<int[]>> map = new TreeMap();
        
        UnionFind uf = new UnionFind(n);
        uf.merge(0, first);
        
        // uf.print();
        
        for (int[] m : meetings) {
            map.computeIfAbsent(m[2], k -> new HashSet());
            map.get(m[2]).add(new int[] {m[0], m[1]});
            uf.merge(m[0], m[1]);
        }
        
        for (Set<int[]> parts : map.values()) {
            for (int[] each : parts) {
                if (uf.find(each[0]) == 0) {
                    uf.merge(0, each[1]);
                } else if (uf.find(each[1]) == 0) {
                    uf.merge(0, each[0]);
                }
            }
        }
        
        List<Integer> ans = new ArrayList();
        
        for (int i = 0; i < n; i++) {
            if (uf.find(i) == 0) {
                ans.add(i);
            }
        }
        
        return ans;
    }
    
    public List<Integer> mySol2_fail(int n, int[][] meetings, int first) {
        
        UnionFind uf = new UnionFind(n);
        uf.merge(0, first);
        
        Map<Integer, Set<int[]>> map = new TreeMap();
        
        for (int[] m : meetings) {
            map.computeIfAbsent(m[2], k -> new HashSet());
            map.get(m[2]).add(new int[] {m[0], m[1]});
        }
        
        for (Set<int[]> values : map.values()) {
            Map<Integer, List<Integer>> edges = new HashMap();
            
            for (int[] two : values) {
                edges.computeIfAbsent(two[0], k -> new ArrayList());
                edges.computeIfAbsent(two[1], k -> new ArrayList());
                
                edges.get(two[0]).add(two[1]);
                edges.get(two[1]).add(two[0]);
            }
            
            Set<Integer> visit = new HashSet();
            
            for (int start : edges.keySet()) {
                if (uf.find(start) != 0) continue;
                
                dfs(start, edges, uf, visit);
                
                visit.clear();
            }
        }
        
        List<Integer> ans = new ArrayList();
        int[] values = uf.values();
        
        for (int i = 0; i < n; i++) {
            if (values[i] == 0) {
                ans.add(i);
            }
        }
        
        return ans;
    }
    
    private void dfs(int node, Map<Integer, List<Integer>> edges, UnionFind uf, Set<Integer> visit) {
        if (!visit.add(node)) return;
        
        if (uf.find(node) == 0) {
            for (int next : edges.get(node)) {
                uf.merge(node, next);
                dfs(next, edges, uf, visit);
            }
        }
    }
    
    class UnionFind {
        private int[] parents;
        
        public UnionFind(int n) {
            parents = new int[n];
            
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }
        
        public void merge(int a, int b) {
            a = find(a);
            b = find(b);
            
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            
            parents[b] = a;
        }
        
        public int find(int a) {
            if (a != parents[a]) {
                parents[a] = find(parents[a]);
            }
            
            return parents[a];
        }
        
        public void print() {
            System.out.println(Arrays.toString(parents));
        }
        
        public int[] values() {
            return parents;
        }
    }
    
    public List<Integer> mySol_fail(int n, int[][] meetings, int first) {
        Set<Integer> set = new HashSet();
        set.add(0);
        set.add(first);
        
        Map<Integer, Set<int[]>> map = new TreeMap();
        
        for (int[] m : meetings) {
            map.computeIfAbsent(m[2], k -> new HashSet());
            map.get(m[2]).add(new int[] {m[0], m[1]});
        }
        
        for (Set<int[]> parts : map.values()) {
            for (int[] part : parts) {
                if (set.contains(part[0])) {
                    set.add(part[1]);
                } else if (set.contains(part[1])) {
                    set.add(part[0]);
                }
            }
        }
        
        return new ArrayList(set);
    }
}