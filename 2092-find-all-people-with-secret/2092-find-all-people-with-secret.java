class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        return official_bfs(n, meetings, firstPerson);
    }
    
    public List<Integer> official_bfs(int n, int[][] meetings, int firstPerson) {
        // For every person, we store the meeting time and label of the person met.
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] meeting : meetings) {
            int x = meeting[0], y = meeting[1], t = meeting[2];
            graph.computeIfAbsent(x, k -> new ArrayList<>()).add(new int[]{t, y});
            graph.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[]{t, x});
        }

        // Earliest time at which a person learned the secret 
        // as per current state of knowledge. If due to some new information, 
        // the earliest time of knowing the secret changes, we will update it
        // and again process all the people whom he/she meets after the time
        // at which he/she learned the secret.
        int[] earliest = new int[n];
        Arrays.fill(earliest, Integer.MAX_VALUE);
        earliest[0] = 0;
        earliest[firstPerson] = 0;
        
        // Queue for BFS. It will store (person, time of knowing the secret)
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        q.offer(new int[]{firstPerson, 0});

        // Do BFS
        while (!q.isEmpty()) {
            int[] personTime = q.poll();
            int person = personTime[0], time = personTime[1];
            for (int[] nextPersonTime : graph.getOrDefault(person, new ArrayList<>())) {
                int t = nextPersonTime[0], nextPerson = nextPersonTime[1];
                if (t >= time && earliest[nextPerson] > t) {
                    earliest[nextPerson] = t;
                    q.offer(new int[]{nextPerson, t});
                }
            }
        }
        
        // Since we visited only those people who know the secret,
        // we need to return indices of all visited people.
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (earliest[i] != Integer.MAX_VALUE) {
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