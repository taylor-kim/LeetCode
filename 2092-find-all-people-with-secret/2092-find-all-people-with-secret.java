class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        return mySol_fail_improved(n, meetings, firstPerson);
    }

    public List<Integer> mySol_fail_improved(int n, int[][] meetings, int firstPerson) {
        Set<Integer> ans = new HashSet();

        ans.add(0);
        ans.add(firstPerson);

        Map<Integer, List<int[]>> map = new TreeMap();

        for (int[] meeting : meetings) {
            map.computeIfAbsent(meeting[2], k -> new ArrayList()).add(meeting);
        }

        for (int key : map.keySet()) {
            List<int[]> meetingsAt = map.get(key);

            Queue<Integer> queue = new LinkedList();
            Set<Integer> visit = new HashSet();
            Map<Integer, Set<Integer>> graph = new HashMap();

            for (int[] meetingAt : meetingsAt) {
                int a = meetingAt[0];
                int b = meetingAt[1];

                if (ans.contains(a) && ans.contains(b)) continue;

                graph.computeIfAbsent(a, k -> new HashSet()).add(b);
                graph.computeIfAbsent(b, k -> new HashSet()).add(a);
                
                if (ans.contains(a) && visit.add(a)) {
                    queue.add(a);
                }

                if (ans.contains(b) && visit.add(b)) {
                    queue.add(b);
                }
            }

            while (!queue.isEmpty()) {
                int person = queue.poll();

                for (int next : graph.getOrDefault(person, new HashSet<>())) {
                    if (ans.add(next) && visit.add(next)) {
                        queue.add(next);
                    }
                }
            }
        }

        return new ArrayList(ans);
    }

    public List<Integer> mySol3_by_read_past_sol(int n, int[][] meetings, int firstPerson) {
        Map<Integer, List<int[]>> map = new TreeMap();

        for (int[] meeting : meetings) {
            map.computeIfAbsent(meeting[2], k -> new ArrayList()).add(meeting);
        }

        UnionFind uf = new UnionFind(n);
        uf.merge(0, firstPerson);

        for (List<int[]> meetingsAt : map.values()) {
            for (int[] meetingAt : meetingsAt) {
                int a = meetingAt[0];
                int b = meetingAt[1];

                uf.merge(a, b);
            }

            for (int[] meetingAt : meetingsAt) {
                int a = meetingAt[0];
                int b = meetingAt[1];

                if (uf.find(a) != 0) {
                    uf.parents[a] = a;
                    uf.parents[b] = b;
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

    public List<Integer> mySol2_fail(int n, int[][] meetings, int firstPerson) {
        Set<Integer> ans = new HashSet();

        ans.add(0);
        ans.add(firstPerson);

        Map<Integer, List<int[]>> map = new TreeMap();

        for (int[] meeting : meetings) {
            map.computeIfAbsent(meeting[2], k -> new ArrayList()).add(meeting);
        }

        for (List<int[]> meetingsAt : map.values()) {
            UnionFind uf = new UnionFind(n);

            for (int[] meetingAt : meetingsAt) {
                int a = meetingAt[0];
                int b = meetingAt[1];

                uf.merge(a, b);

                if (ans.contains(a) || ans.contains(b)) {
                    uf.merge(0, a);
                    ans.add(a);
                    ans.add(b);
                }
            }
        }

        return new ArrayList(ans);
    }

    class UnionFind {
        int[] parents;

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
                a += b;
                b = a - b;
                a = a - b;
            }

            parents[b] = a;
        }

        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }
    }

    public List<Integer> mySol_fail(int n, int[][] meetings, int firstPerson) {
        Set<Integer> ans = new HashSet();

        ans.add(0);
        ans.add(firstPerson);

        Map<Integer, List<int[]>> map = new TreeMap();

        for (int[] meeting : meetings) {
            map.computeIfAbsent(meeting[2], k -> new ArrayList()).add(meeting);
        }

        for (List<int[]> meetingsAt : map.values()) {
            Queue<Integer> queue = new LinkedList();
            Set<Integer> visit = new HashSet();
            Map<Integer, Set<Integer>> graph = new HashMap();

            for (int[] meetingAt : meetingsAt) {
                int a = meetingAt[0];
                int b = meetingAt[1];

                if (ans.contains(a) && ans.contains(b)) continue;

                int secret = -1;
                int none = -1;

                if (ans.contains(a)) {
                    secret = a;
                    none = b;
                } else {
                    secret = b;
                    none = a;
                }

                graph.computeIfAbsent(secret, k -> new HashSet()).add(none);
                queue.add(secret);
                visit.add(secret);
            }

            while (!queue.isEmpty()) {
                int person = queue.poll();

                for (int next : graph.getOrDefault(person, new HashSet<>())) {
                    if (ans.add(next) && visit.add(next)) {
                        queue.add(next);
                    }
                }
            }
        }

        return new ArrayList(ans);
    }
}