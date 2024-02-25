class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        return official_uf(n, meetings, firstPerson);
    }
    
    public List<Integer> official_uf(int n, int[][] meetings, int first) {
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        
        Map<Integer, List<int[]>> sameTimeMeetings = new TreeMap();
        
        for (int[] m : meetings) {
            sameTimeMeetings.computeIfAbsent(m[2], k -> new ArrayList()).add(new int[] {m[0], m[1]});
        }
        
        OfficialUF uf = new OfficialUF(n);
        // uf.merge(0, first);
        uf.merge(first, 0);
        
        for (int t : sameTimeMeetings.keySet()) {
            for (int[] m : sameTimeMeetings.get(t)) {
                uf.merge(m[0], m[1]);
            }
            
            for (int[] m : sameTimeMeetings.get(t)) {
                if (!uf.connected(m[0], 0)) {
                    uf.reset(m[0]);
                    uf.reset(m[1]);
                }
            }
        }
        
        List<Integer> ans = new ArrayList();
        
        for (int i = 0; i < n; i++) {
            if (uf.connected(i, 0)) {
                ans.add(i);
            }
        }
        
        return ans;
    }
    
    class OfficialUF {
        private int[] parents;
        private int[] rank;
        
        public OfficialUF(int n) {
            parents = new int[n];
            rank = new int[n];
            
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }
        
        public void merge(int a, int b) {
            a = find(a);
            b = find(b);
            
            if (a != b) {
                if (rank[a] > rank[b]) {
                    parents[b] = a;
                } else if (rank[a] < rank[b]) {
                    parents[a] = b;
                } else {
                    parents[b] = a;
                    rank[a]++;
                }
            }
        }
        
        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }
            
            return parents[a];
        }
        
        public boolean connected(int a, int b) {           
            return find(a) == find(b);
        }
        
        public void reset(int a) {
            parents[a] = a;
            rank[a] = 0;
        }
    }
    
    public List<Integer> try_official_bfs_on_time_scale(int n, int[][] meetings, int first) {
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        
        Map<Integer, List<int[]>> sameTimeMeetings = new TreeMap();
        
        for (int[] meeting : meetings) {
            sameTimeMeetings.computeIfAbsent(meeting[2], k -> new ArrayList()).add(new int[] {meeting[0], meeting[1]});
        }
        
        boolean[] knowsSecret = new boolean[n];
        knowsSecret[0] = true;
        knowsSecret[first] = true;
        
        for (int t : sameTimeMeetings.keySet()) {
            Map<Integer, List<Integer>> meet = new HashMap();
            Set<Integer> start = new HashSet();
            
            for (int[] meeting : sameTimeMeetings.get(t)) {
                int x = meeting[0];
                int y = meeting[1];
                
                if (knowsSecret[x]) {
                    start.add(x);
                }
                
                if (knowsSecret[y]) {
                    start.add(y);
                }
                
                meet.computeIfAbsent(x, k -> new ArrayList()).add(y);
                meet.computeIfAbsent(y, k -> new ArrayList()).add(x);
            }
            
            Queue<Integer> q = new LinkedList(start);
            
            while (!q.isEmpty()) {
                int a = q.poll();
                
                for (int next : meet.getOrDefault(a, Collections.emptyList())) {
                    if (!knowsSecret[next]) {
                        knowsSecret[next] = true;
                        q.add(next);
                    }
                }
            }
        }
        
        List<Integer> ans = new ArrayList();
        
        for (int i = 0; i < n; i++) {
            if (knowsSecret[i]) {
                ans.add(i);
            }
        }
        
        return ans;
    }
    
    public List<Integer> try_official_bfs_pq(int n, int[][] meetings, int first) {
        Map<Integer, List<int[]>> edges = new HashMap();
        
        for (int[] m : meetings) {
            edges.computeIfAbsent(m[0], k -> new ArrayList()).add(new int[] {m[1], m[2]});
            edges.computeIfAbsent(m[1], k -> new ArrayList()).add(new int[] {m[0], m[2]});
        }
        
        int[] secrets = new int[n];
        Arrays.fill(secrets, Integer.MAX_VALUE);
        secrets[0] = 0;
        secrets[first] = 0;
        
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        
        pq.add(new int[] {0, 0});
        pq.add(new int[] {first, 0});
        
        boolean[] visited = new boolean[n];
        
        while (!pq.isEmpty()) {
            int a = pq.peek()[0];
            int t = pq.poll()[1];
            
            if (visited[a]) {
                continue;
            }
            
            visited[a] = true;
            
            for (int[] next : edges.getOrDefault(a, Collections.emptyList())) {
                int b = next[0];
                int currentT = next[1];
                
                if (currentT >= t && secrets[b] > currentT) {
                    secrets[b] = currentT;
                    pq.add(new int[] {b, currentT});
                }
            }
        }
        
        List<Integer> ans = new ArrayList();
        
        for (int i = 0; i < n; i++) {
            if (secrets[i] != Integer.MAX_VALUE) {
                ans.add(i);
            }
        }
        
        return ans;
    }
    
    public List<Integer> try_official_dfs_rec(int n, int[][] meetings, int first) {
        Map<Integer, List<int[]>> edges = new HashMap();
        
        for (int[] m : meetings) {
            edges.computeIfAbsent(m[0], k -> new ArrayList()).add(new int[] {m[1], m[2]});
            edges.computeIfAbsent(m[1], k -> new ArrayList()).add(new int[] {m[0], m[2]});
        }
        
        int[] secrets = new int[n];
        Arrays.fill(secrets, Integer.MAX_VALUE);
        secrets[0] = 0;
        dfs(0, 0, edges, secrets);
        secrets[first] = 0;

        dfs(first, 0, edges, secrets);
        
        List<Integer> ans = new ArrayList();
        
        for (int i = 0; i < n; i++) {
            if (secrets[i] != Integer.MAX_VALUE) {
                ans.add(i);
            }
        }
        
        return ans;
    }
    
    private void dfs(int a, int t, Map<Integer, List<int[]>> edges, int[] secrets) {
        for (int[] next : edges.getOrDefault(a, Collections.emptyList())) {
            int b = next[0];
            int currentT = next[1];
            
            if (currentT >= t && secrets[b] > currentT) {
                secrets[b] = currentT;
                dfs(b, currentT, edges, secrets);
            }
        }
    }
    
    public List<Integer> try_official_dfs_stack(int n, int[][] meetings, int first) {
        Map<Integer, List<int[]>> edges = new HashMap();
        
        for (int[] m : meetings) {
            edges.computeIfAbsent(m[0], k -> new ArrayList()).add(new int[] {m[1], m[2]});
            edges.computeIfAbsent(m[1], k -> new ArrayList()).add(new int[] {m[0], m[2]});
        }
        
        int[] secrets = new int[n];
        Arrays.fill(secrets, Integer.MAX_VALUE);
        secrets[0] = 0;
        secrets[first] = 0;
        
        Stack<int[]> stack = new Stack();
        stack.push(new int[] {0, 0});
        stack.push(new int[] {first, 0});
        
        while (!stack.isEmpty()) {
            int a = stack.peek()[0];
            int secretT = stack.pop()[1];
            
            for (int[] next : edges.getOrDefault(a, Collections.emptyList())) {
                int b = next[0];
                int currentT = next[1];
                
                if (currentT >= secretT && secrets[b] > currentT) {
                    secrets[b] = currentT;
                    stack.push(new int[] {b, currentT});
                }
            }
        }
        
        List<Integer> ans = new ArrayList();
        
        for (int i = 0; i < n; i++) {
            if (secrets[i] != Integer.MAX_VALUE) {
                ans.add(i);
            }
        }
        
        return ans;
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
        
        int[] secrets = new int[n];
        Arrays.fill(secrets, Integer.MAX_VALUE);
        secrets[0] = 0;
        secrets[first] = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            while (size-- > 0) {
                int a = queue.peek()[0];
                int secretT = queue.poll()[1];
                
                for (int[] next : edges.getOrDefault(a, Collections.emptyList())) {
                    int b = next[0];
                    int currentT = next[1];
                    
                    if (currentT >= secretT && secrets[b] > currentT) {
                        secrets[b] = currentT;
                        queue.add(next);
                    }
                }
            }
        }
        
        List<Integer> ans = new ArrayList();
        
        for (int i = 0; i < n; i++) {
            if (secrets[i] != Integer.MAX_VALUE) {
                ans.add(i);
            }
        }
        
        return ans;
    }
    
    public List<Integer> mySol3_fail_retry(int n, int[][] meetings, int first) {
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        
        Map<Integer, Set<int[]>> map = new TreeMap();
        
        UnionFind uf = new UnionFind(n);
        uf.merge(0, first);
        
        for (int[] m : meetings) {
            map.computeIfAbsent(m[2], k -> new HashSet()).add(new int[] {m[0], m[1]});
        }
        
        for (Set<int[]> parts : map.values()) {
            for (int[] each : parts) {
                uf.merge(each[0], each[1]);
            }
            
            for (int[] each : parts) {
                if (uf.find(each[0]) != 0) {
                    uf.reset(each[0]);
                    uf.reset(each[1]);
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
        
        public void reset(int a) {
            parents[a] = a;
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